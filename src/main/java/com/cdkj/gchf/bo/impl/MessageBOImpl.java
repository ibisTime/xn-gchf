package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectCardBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IMessageDAO;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectCard;
import com.cdkj.gchf.enums.EMessageStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class MessageBOImpl extends PaginableBOImpl<Message>
        implements IMessageBO {
    @Autowired
    private IMessageDAO messageDAO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IProjectCardBO projectCardBO;

    @Override
    public void saveMessage(String code, String projectCode, String month,
            Long totalAmount, Long totalCutAmount, Integer number) {
        Project project = projectBO.getProject(projectCode);
        Message message = new Message();
        message.setCode(code);
        message.setProjectCode(project.getCode());
        message.setProjectName(project.getName());
        message.setMonth(month);
        ProjectCard card = projectCardBO
            .getProjectCardByProject(project.getCode());

        message.setBankCode(card.getBankCode());
        message.setBankName(card.getBankName());
        message.setSubbranch(card.getSubbranch());
        message.setBankcardNumber(card.getBankcardNumber());
        message.setCreateDatetime(new Date());

        message.setTotalAmount(totalAmount);
        message.setTotalCutAmount(totalCutAmount);
        message.setNumber(number);
        message.setDownload(0);
        message.setStatus(EMessageStatus.TO_Send.getCode());
        messageDAO.insert(message);
    }

    @Override
    public void sendMessage(Message data) {
        messageDAO.sendMessage(data);
    }

    @Override
    public void refreshMessage(Message data) {
        messageDAO.update(data);
    }

    @Override
    public void refreshMessage4DropSalary(String code, Long totalAmount,
            Long totalCutAmount, Long totalTax) {
        Message message = getMessage(code);
        message.setTotalAmount(message.getTotalAmount() - totalAmount);
        message.setTotalCutAmount(message.getTotalCutAmount() - totalCutAmount);
        message.setTotalTax(message.getTotalTax() - totalTax);
        message.setNumber(message.getNumber() - 1);
        messageDAO.update4DropSalary(message);
    }

    @Override
    public void dropNotExistSalary(String code) {
        Message message = new Message();
        message.setCode(code);
        messageDAO.deleteNotExistSalary(message);
    }

    @Override
    public void approveMessage(Message data, String handler,
            String handleNote) {
        data.setHandler(handler);
        data.setHandleDatetime(new Date());
        data.setHandleNote(handleNote);
        data.setStatus(EMessageStatus.Approved.getCode());
        messageDAO.approveMessage(data);
    }

    @Override
    public void downLoad(Message data) {
        messageDAO.downLoad(data);
    }

    @Override
    public Message getMessage(String code) {
        Message data = null;
        if (StringUtils.isNotBlank(code)) {
            Message condition = new Message();
            condition.setCode(code);
            data = messageDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "代发消息不存在");
            }
            ProjectCard card = projectCardBO
                .getProjectCardByProject(data.getProjectCode());
            data.setCompanyCard(card);
        }
        return data;
    }

    @Override
    public List<Message> queryMessageList(Message condition) {
        return messageDAO.selectList(condition);
    }
}
