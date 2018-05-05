package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IMessageAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.ISalaryLogBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.dto.req.XN631439Req;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.ESalaryLogType;

@Service
public class MessageAOImpl implements IMessageAO {

    @Autowired
    private IMessageBO messageBO;

    @Autowired
    private ISalaryBO salaryBO;

    @Autowired
    private ISalaryLogBO salaryLogBO;

    @Autowired
    private IAttendanceBO attendanceBO;

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
        return messageBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Message> queryMessageList(Message condition) {
        return messageBO.queryMessageList(condition);
    }

    @Override
    public Message getMessage(String code) {
        return messageBO.getMessage(code);
    }

    @Override
    public void sendMessage(String code, String sender, String sendNote) {
        Message data = messageBO.getMessage(code);
        messageBO.sendMessage(data, sender, sendNote);
    }

    @Override
    public void approveMessage(String code, String handler, String handleNote,
            List<XN631439Req> list) {
        Message data = messageBO.getMessage(code);
        String type = ESalaryLogType.Normal.getCode();
        for (XN631439Req req : list) {
            Salary salary = salaryBO.getSalary(req.getCode());
            if (salary.getShouldAmount() != StringValidater
                .toLong(req.getPayAmount())) {
                type = ESalaryLogType.Abnormal.getCode();
            }
            // 修改工资条信息
            salaryBO.payAmount(salary, req.getPayAmount(),
                req.getLatePayDatetime());
            // 添加工资日志
            salaryLogBO.saveSalaryLog(salary, type, handler, handleNote);

        }
        // 修改考勤记录状态
        Attendance condition = new Attendance();
        condition.setProjectCode(data.getProjectCode());
        condition.setStatus(EAttendanceStatus.Unpaied.getCode());
        List<Attendance> aList = attendanceBO.queryAttendanceList(condition);
        for (Attendance attendance : aList) {
            attendance.setStatus(EAttendanceStatus.Paied.getCode());
            attendanceBO.updateStatus(attendance);
        }

        messageBO.approveMessage(data, handler, handleNote);
    }

    @Override
    public void downLoad(String code) {
        Message data = messageBO.getMessage(code);
        if (data.getDownload() == null) {
            data.setDownload(1);
        } else {
            data.setDownload(data.getDownload() + 1);
        }
        messageBO.downLoad(data);
    }
}
