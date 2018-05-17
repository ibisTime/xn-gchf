package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Salary;
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
    private IMessageBO messageBO;

    @Autowired
    private ISalaryBO salaryBO;

    @Autowired
    private ISalaryLogBO salaryLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IReportBO reportBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private ICompanyCardBO companyCardBO;

    @Override
    public String addMessage(Message data) {
        messageBO.saveMessage(data);
        return null;
    }

    @Override
    public void editMessage(Message data) {
    }

    @Override
    public void dropMessage(String code) {
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
    public void sendMessage(String code, String title, String content,
            String sender, String sendNote) {
        Message data = messageBO.getMessage(code);
        data.setTitle(title);
        data.setContent(content);
        data.setSender(sender);
        data.setSendDatetime(new Date());
        data.setSendNote(sendNote);

        data.setStatus(EMessageStatus.TO_Deal.getCode());
        messageBO.sendMessage(data);
    }

    @Override
    @Transactional
    public void approveMessage(String code, String handler, String handleNote,
            List<XN631439Req> list) {
        Message data = messageBO.getMessage(code);

        Project project = projectBO.getProject(data.getProjectCode());
        Long lastMonthSalary = 0L;
        String mCode = null;
        String type = ESalaryLogType.Normal.getCode();
        String status = ESalaryStatus.Payed.getCode();
        for (XN631439Req req : list) {
            Salary salary = salaryBO.getSalary(req.getCode());
            // 防止重复发放
            if (ESalaryStatus.Payed.getCode().equals(salary.getStatus())) {
                throw new BizException("xn0000",
                    "编号为[" + salary.getStaffCode() + "]员工的工资已全部发放");
            }

            if (salary.getFactAmount() > StringValidater
                .toLong(req.getPayAmount())) {
                type = ESalaryLogType.Abnormal.getCode();
                // 生成新代发消息
                Message message = new Message();
                mCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Message.getCode());

                message.setCode(mCode);
                message.setProjectCode(project.getCode());
                message.setProjectName(project.getName());

                CompanyCard card = companyCardBO
                    .getCompanyCardByProject(project.getCode());
                message.setBankCode(card.getBankCode());
                message.setBankName(card.getBankName());
                message.setSubbranch(card.getSubbranch());
                message.setBankcardNumber(card.getBankcardNumber());
                message.setCreateDatetime(new Date());

                message.setStatus(EMessageStatus.TO_Send.getCode());
                messageBO.saveMessage(message);
                status = ESalaryStatus.Pay_Portion.getCode();
                salaryBO.saveNewSalay(salary, mCode,
                    StringValidater.toLong(req.getPayAmount()));
            }

            if (ESalaryStatus.Pay_Portion.getCode()
                .equals(salary.getStatus())) {
                status = ESalaryStatus.Pay_Again.getCode();
            }
            // 修改工资条信息
            salaryBO.payAmount(salary,
                StringValidater.toLong(req.getPayAmount()), status,
                req.getLatePayDatetime());
            // 添加工资日志
            salaryLogBO.saveSalaryLog(salary, type);
            lastMonthSalary = lastMonthSalary
                    + StringValidater.toLong(req.getPayAmount());
        }
        Report report = reportBO.getReportByProject(data.getProjectCode());
        report.setLastMonthSalary(lastMonthSalary);
        reportBO.refreshLastMonthSalary(report);
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
        System.out.println("download:" + data.getDownload() + ",backDownload:"
                + data.getBackDownload());

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
