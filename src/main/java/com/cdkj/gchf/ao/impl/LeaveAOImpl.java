package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.ILeaveAO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.ILeaveBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Leave;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.dto.req.XN631461Req;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.exception.BizException;

/**
 * 请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午2:05:01 
 * @history:
 */
@Service
public class LeaveAOImpl implements ILeaveAO {
    @Autowired
    private ILeaveBO leaveBO;

    @Autowired
    IEmployBO employBO;

    @Autowired
    IReportBO reportBO;

    @Override
    @Transactional
    public String addLeave(XN631461Req req) {
        Employ employ = employBO.getEmployByStaff(req.getStaffCode(),
            req.getProjectCode());
        if (EEmployStatus.Leave.getCode().equals(employ.getStatus())) {
            throw new BizException("xn0000", "该员工已离职");
        }
        if (EEmployStatus.Hoilday.getCode().equals(employ.getStatus())) {
            throw new BizException("xn0000", "该员工已在请假中");
        }

        Date startDatetime = DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        if (startDatetime == null) {
            throw new BizException("xn0000", "时间格式不正确");
        }

        // 更新统计请假人数
        Report report = reportBO.getReportByProject(employ.getProjectCode());
        report.setLeavingDays(report.getLeavingDays() + 1);
        reportBO.refreshLeavingDays(report);

        // 更新雇佣状态
        employBO.toHoliday(req.getStaffCode(), req.getProjectCode(),
            startDatetime, StringValidater.toInteger(req.getLeaveDays()));

        // 添加请假记录
        return leaveBO.saveLeave(req.getStaffCode(), req.getProjectCode(),
            startDatetime, StringValidater.toInteger(req.getLeaveDays()),
            req.getUpdater(), req.getRemark());
    }

    @Override
    public Paginable<Leave> queryLeavePage(int start, int limit,
            Leave condition) {
        return leaveBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Leave> queryLeaveList(Leave condition) {
        return leaveBO.queryLeaveList(condition);
    }

    @Override
    public Leave getLeave(String code) {
        return leaveBO.getLeave(code);
    }
}
