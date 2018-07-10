package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IEventRemindBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.EventRemind;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631439Req;
import com.cdkj.gchf.enums.EAbnormalType;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EMessageStatus;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;

@Service
public class MessageAOImpl implements IMessageAO {

    @Autowired
    IMessageBO messageBO;

    @Autowired
    ISalaryBO salaryBO;

    @Autowired
    ISalaryLogBO salaryLogBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IReportBO reportBO;

    @Autowired
    IStaffBO staffBO;

    @Autowired
    IEmployBO employBO;

    @Autowired
    IBankCardBO bankCardBO;

    @Autowired
    IEventRemindBO eventRemindBO;

    @Autowired
    ICompanyBO companyBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IProjectBO projectBO;

    @Override
    @Transactional
    public void sendMessage(String code, String title, String content,
            String sender, String sendNote) {

        Message data = messageBO.getMessage(code);
        data.setTitle(title);
        data.setContent(content);
        data.setSender(sender);
        data.setSendDatetime(new Date());
        data.setSendNote(sendNote);
        data.setStatus(EMessageStatus.TO_Deal.getCode());

        // 判断工资条是否已审核
        Salary condition = new Salary();
        condition.setMessageCode(data.getCode());
        List<Salary> list = salaryBO.querySalaryList(condition);
        BankCard bankCard = null;

        for (Salary salary : list) {
            if (ESalaryStatus.To_Approve.getCode().equals(salary.getStatus())) {
                throw new BizException("xn00000", "存在未审核的工资条，请核对后再发送");
            }
            salary.setStatus(ESalaryStatus.TO_Pay.getCode());
            salaryBO.refreshStatus(salary);
            bankCard = bankCardBO.getBankCardByStaff(salary.getStaffCode());
            if (bankCard == null) {
                throw new BizException("xn00000",
                    "员工：" + salary.getStaffName() + "，未录入银行卡信息，请补全信息后再发送");
            }
        }
        messageBO.sendMessage(data);
    }

    @Override
    @Transactional
    public void approveMessage(String code, String handler, String handleNote,
            List<XN631439Req> list) {
        Message data = messageBO.getMessage(code);
        Long lastMonthSalary = 0L;
        String projectCode = null;
        String messageCode = OrderNoGenerater
            .generate(EGeneratePrefix.Message.getCode());

        for (XN631439Req req : list) {
            String status = ESalaryStatus.Payed.getCode();
            Salary salary = salaryBO.getSalary(req.getCode());
            if (!salary.getProjectCode().equals(projectCode)) {
                projectCode = salary.getProjectCode();
            }
            Project project = projectBO.getProject(projectCode);

            // 防止重复发放
            if (ESalaryStatus.Payed.getCode().equals(salary.getStatus())) {
                throw new BizException("xn00000",
                    "员工[" + salary.getStaffName() + "]的工资已全部发放");
            }

            // 判断工资是否少发和迟发
            Date latePayDatetime = DateUtil.strToDate(req.getLatePayDatetime(),
                DateUtil.FRONT_DATE_FORMAT_STRING);// 实际发薪时间
            Long payAmount = StringValidater.toLong(req.getPayAmount());// 实际发薪金额
            String[] salaryDate = salary.getMonth().split("/");
            Integer salaryYeay = StringValidater.toInteger(salaryDate[0]);
            Integer salaryMonth = StringValidater.toInteger(salaryDate[1]);
            Integer salaryDay = StringValidater.toInteger(
                project.getSalaryDatetime()) + project.getSalaryDelayDays();

            Calendar latestSalaryDate = Calendar.getInstance();
            latestSalaryDate.set(salaryYeay, salaryMonth, salaryDay);// 最迟发薪时间
            Boolean isLess = false;// 是否少发
            Boolean isDelay = false;// 是否迟发

            if (payAmount < salary.getFactAmount()) {
                isLess = true;
            }
            if (latePayDatetime.getTime() > latestSalaryDate.getTime()
                .getTime()) {
                isDelay = true;
            }

            if (isLess && isDelay) {
                status = ESalaryStatus.Pay_Delay_Portion.getCode();
            } else if (isLess && !isDelay) {
                status = ESalaryStatus.Pay_Portion.getCode();
            } else if (isDelay && !isLess) {
                status = ESalaryStatus.Pay_Delay.getCode();
            }

            Long supplyAmount = salary.getFactAmount()
                    - StringValidater.toLong(req.getPayAmount());
            salary.setPayAmount(StringValidater.toLong(req.getPayAmount()));
            salary.setSupplyAmount(supplyAmount);
            salary.setStatus(status);
            salary.setLatePayDatetime(latePayDatetime);
            salary.setMessageCode(messageCode);
            salaryBO.payAmount(salary);

            // 修改员工工资发放状态
            Employ employ = employBO.getEmployByStaff(salary.getStaffCode(),
                salary.getProjectCode());
            employ.setSalaryStatus(status);
            employBO.updateSalaryStatus(employ);
            lastMonthSalary = lastMonthSalary
                    + StringValidater.toLong(req.getPayAmount());

            // 改变员工薪资状态
            Staff staff = staffBO.getStaff(salary.getStaffCode());

            // 发送提示短信
            List<EventRemind> erList = eventRemindBO
                .getEventRemindByType(EAbnormalType.Salary_Abnormal.getCode());
            if (CollectionUtils.isNotEmpty(erList)) {
                Project employProject = null;
                Company company = null;

                for (EventRemind eventRemind : erList) {
                    employProject = projectBO
                        .getProject(employ.getProjectCode());
                    company = companyBO
                        .getCompany(employProject.getCompanyCode());
                    smsOutBO
                        .sendSmsOut(eventRemind.getMobile(),
                            "尊敬的" + PhoneUtil
                                .hideMobile(eventRemind.getMobile()) + "，"
                                    + company.getName() + "的"
                                    + employProject.getName() + "出现工资拖欠，员工"
                                    + staff.getName() + "," + salary.getMonth()
                                    + "的工资为" + (salary.getFactAmount() / 1000)
                                    + "发放薪资为"
                                    + (StringValidater
                                        .toLong(req.getPayAmount()) / 1000)
                                    + "，请您及时处理",
                            "805061");

                }
            }
        }

        messageBO.approveMessage(data, handler, handleNote);

        // 统计薪资发放
        Report report = reportBO.getReportByProject(data.getProjectCode());
        report.setLastMonthSalary(lastMonthSalary);
        report.setTotalSalary(report.getTotalSalary() + lastMonthSalary);
        reportBO.refreshLastMonthSalary(report);
    }

    @Override
    public Message downLoad(String userId, String code, String download,
            String backDownload) {
        Message data = messageBO.getMessage(code);
        User user = userBO.getUser(userId);
        data.setDownload(StringValidater.toInteger(download));
        data.setBackDownload(StringValidater.toInteger(backDownload));
        if (EMessageStatus.TO_Deal.getCode().equals(data.getStatus())
                && EUserKind.Bank.getCode().equals(user.getType())) {
            data.setStatus(EMessageStatus.TO_Feedback.getCode());
        }

        messageBO.downLoad(data);
        return data;
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;
    }

    @Override
    public Paginable<Message> queryMessagePage(int start, int limit,
            Message condition) {
        Paginable<Message> page = new Page<Message>();
        List<Message> list = new ArrayList<Message>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }

        }
        page = messageBO.getPaginable(start, limit, condition);
        for (Message message : page.getList()) {
            message.setSendName(getName(message.getSender()));
            message.setHandleName(getName(message.getHandler()));
        }
        return page;
    }

    @Override
    public List<Message> queryMessageList(Message condition) {
        List<Message> list = new ArrayList<Message>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }

        list = messageBO.queryMessageList(condition);
        for (Message message : list) {
            message.setSendName(getName(message.getSender()));
            message.setHandleName(getName(message.getHandler()));
        }
        return list;
    }

    @Override
    public Message getMessage(String code) {
        Message data = messageBO.getMessage(code);
        data.setSendName(getName(data.getSender()));
        data.setHandleName(getName(data.getHandler()));
        return data;
    }

}
