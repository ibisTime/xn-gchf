package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IEventRemindBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectCardBO;
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
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.EventRemind;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectCard;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631439Req;
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
    IProjectCardBO projectCardBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IProjectBO projectBO;

    @Override
    @Transactional
    public void sendMessage(String code, String title, String sender,
            String sendNote) {

        Message data = messageBO.getMessage(code);
        data.setTitle(title);
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

            // 判断该员工在该工程下是否有银行卡
            bankCard = bankCardBO.getBankCard(salary.getStaffCode(),
                data.getProjectCode());
            if (bankCard == null) {
                throw new BizException("xn00000",
                    "员工：" + salary.getStaffName() + "，未录入银行卡信息，请补全信息后再发送");
            } else if (StringUtils.isBlank(bankCard.getBankcardNumber())
                    || StringUtils.isBlank(bankCard.getBankName())) {
                throw new BizException("xn00000",
                    "员工：" + salary.getStaffName() + "，请补全银行卡信息后再发送");
            }
        }

        // 向银行端发送通知短信
        User userCondition = new User();
        userCondition.setBankName(data.getBankName());
        userCondition.setSubbranch(data.getSubbranch());
        List<User> userList = userBO.queryUserList(userCondition);// 代发工资的银行用户

        if (CollectionUtils.isNotEmpty(userList)) {
            for (User user : userList) {
                List<EventRemind> eventRemindsList = eventRemindBO
                    .queryEventRemindList(user.getOrganizationCode());// 银行用户的短信通知人
                if (CollectionUtils.isNotEmpty(eventRemindsList)) {
                    for (EventRemind eventRemind : eventRemindsList) {
                        smsOutBO.sendSmsOut(eventRemind.getMobile(),
                            "尊敬的" + PhoneUtil
                                .hideMobile(eventRemind.getMobile()) + "，"
                                    + data.getProjectName()
                                    + "已发送工资代发请求，请您及时处理.",
                            "804080");
                    }
                }
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
        Boolean existAbnormal = false;// 是否存在工资条异常记录
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
                existAbnormal = true;
            } else if (isLess && !isDelay) {
                status = ESalaryStatus.Pay_Portion.getCode();
                existAbnormal = true;
            } else if (isDelay && !isLess) {
                status = ESalaryStatus.Pay_Delay.getCode();
                existAbnormal = true;
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
        }

        // 如果工资异常，向监管端发送提示短信
        if (existAbnormal) {
            Project project = projectBO.getProject(data.getProjectCode());

            User userCondition = new User();
            userCondition.setProvince(project.getProvince());
            userCondition.setCity(project.getCity());
            userCondition.setArea(project.getArea());
            List<User> userList = userBO.queryUserList(userCondition);// 项目所在区域的监管用户

            if (CollectionUtils.isNotEmpty(userList)) {
                for (User user : userList) {
                    List<EventRemind> erList = eventRemindBO
                        .queryEventRemindList(user.getOrganizationCode());
                    if (CollectionUtils.isNotEmpty(erList)) {
                        for (EventRemind eventRemind : erList) {
                            smsOutBO.sendSmsOut(eventRemind.getMobile(),
                                "尊敬的" + PhoneUtil
                                    .hideMobile(eventRemind.getMobile()) + "，"
                                        + project.getName() + "出现工资异常，请您及时处理.",
                                "804080");
                        }
                    }
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
            initMessage(message);
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
            initMessage(message);
        }
        return list;
    }

    @Override
    public Message getMessage(String code) {
        Message data = messageBO.getMessage(code);
        initMessage(data);
        return data;
    }

    private void initMessage(Message message) {
        message.setSendName(getName(message.getSender()));
        message.setHandleName(getName(message.getHandler()));

        Project project = projectBO.getProject(message.getProjectCode());
        if (null != project) {
            message.setProjectChargeUser(project.getChargeUser());
            message.setProjectChargeUserMobile(project.getChargeMobile());

            ProjectCard companyCard = projectCardBO
                .getProjectCardByProject(project.getCode());
            if (null != companyCard) {
                message.setAccount(companyCard.getAccountName());
            }
        }
    }
}
