package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.domain.SalaryLog;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631439Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EMessageStatus;
import com.cdkj.gchf.enums.ESalaryLogType;
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

    @Override
    public String addMessage(Message data) {
        messageBO.saveMessage(data);
        return null;
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
        String sendName = null;
        String handleName = null;
        for (Message message : list) {
            sendName = getName(message.getSender());
            handleName = getName(message.getHandler());
            message.setSendName(sendName);
            message.setHandleName(handleName);
        }
        return list;
    }

    @Override
    public Message getMessage(String code) {
        Message data = messageBO.getMessage(code);
        String sendName = null;
        String handleName = null;
        sendName = getName(data.getSender());
        handleName = getName(data.getHandler());
        data.setSendName(sendName);
        data.setHandleName(handleName);
        return data;
    }

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
            if (ESalaryStatus.TO_Send.getCode().equals(salary.getStatus())) {
                throw new BizException("xn00000", "存在未审核的工资条");
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
        String status = ESalaryStatus.Payed.getCode();
        boolean flag = false;
        String messageCode = OrderNoGenerater
            .generate(EGeneratePrefix.Message.getCode());

        for (XN631439Req req : list) {
            Salary salary = salaryBO.getSalary(req.getCode());
            // 防止重复发放
            if (ESalaryStatus.Payed.getCode().equals(salary.getStatus())) {
                throw new BizException("xn00000",
                    "员工[" + salary.getStaffName() + "]的工资已全部发放");
            }
            if (salary.getFactAmount() == StringValidater
                .toLong(req.getPayAmount())) {
                flag = true;

                // 添加工资日志
                SalaryLog salaryLog = new SalaryLog();
                String salaryLogCode = OrderNoGenerater
                    .generate(EGeneratePrefix.SalaryLog.getCode());
                salaryLog.setCode(salaryLogCode);
                salaryLog.setSalaryCode(salary.getCode());
                salaryLog.setType(ESalaryLogType.Abnormal.getCode());
                salaryLog.setStaffCode(salary.getStaffCode());
                salaryLog.setProjectCode(salary.getProjectCode());
                salaryLog.setProjectName(salary.getProjectName());
                salaryLogBO.saveSalaryLog(salaryLog);
                status = ESalaryStatus.Pay_Portion.getCode();
            }
            if (ESalaryStatus.Pay_Portion.getCode()
                .equals(salary.getStatus())) {
                status = ESalaryStatus.Pay_Again.getCode();
            }

            Long supplyAmount = salary.getFactAmount()
                    - StringValidater.toLong(req.getPayAmount());
            salary.setPayAmount(StringValidater.toLong(req.getPayAmount()));
            salary.setSupplyAmount(supplyAmount);
            salary.setStatus(status);
            salary.setLatePayDatetime(DateUtil.strToDate(
                req.getLatePayDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
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

        Report report = reportBO.getReportByProject(data.getProjectCode());
        report.setLastMonthSalary(lastMonthSalary);
        reportBO.refreshLastMonthSalary(report);
        // 是否有异常工资
        if (flag) {
            Date date = new Date();
            Message message = new Message();
            message.setCode(messageCode);
            message.setProjectCode(data.getCode());
            message.setBankCode(data.getBankCode());
            message.setBankName(data.getBankName());
            message.setSubbranch(data.getSubbranch());

            message.setBankcardNumber(data.getBankcardNumber());
            message.setCreateDatetime(date);
            message.setDownload(0);
            message.setStatus(EMessageStatus.TO_Send.getCode());
            messageBO.saveMessage(message);
        }
        messageBO.approveMessage(data, handler, handleNote);
    }

    @Override
    public Message downLoad(String code, String download, String backDownload) {
        Message data = messageBO.getMessage(code);
        data.setDownload(StringValidater.toInteger(download));
        data.setBackDownload(StringValidater.toInteger(backDownload));
        if (EMessageStatus.TO_Deal.getCode().equals(data.getStatus())) {
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
}
