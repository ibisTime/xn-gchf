package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ILeaveBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ILeaveDAO;
import com.cdkj.gchf.domain.Leave;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

/**
 * 请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午2:01:29 
 * @history:
 */
@Component
public class LeaveBOImpl extends PaginableBOImpl<Leave> implements ILeaveBO {
    @Autowired
    private ILeaveDAO leaveDAO;

    @Autowired
    private IStaffBO staffBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String saveLeave(String staffCode, String projectCode,
            Date startDatetime, Date endDatetime, Integer leaveDays,
            String updater, String remark) {
        Leave data = new Leave();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Leave.getCode());
        Staff staff = staffBO.getStaff(staffCode);
        Project project = projectBO.getProject(projectCode);

        data.setCode(code);
        data.setStaffCode(staffCode);
        data.setProjectCode(projectCode);
        data.setStaffName(staff.getName());
        data.setProjectName(project.getName());

        data.setStartDatetime(startDatetime);
        data.setEndDatetime(endDatetime);
        data.setLeaveDays(leaveDays);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);

        leaveDAO.insert(data);
        return code;
    }

    @Override
    public Integer getMonthLeaveDays(String staffCode, String projectCode,
            Date startDatetime, Date endDatetime) {
        Leave condition = new Leave();
        condition.setStaffCode(staffCode);
        condition.setProjectCode(projectCode);
        condition.setStartDatetime(startDatetime);
        condition.setEndDatetime(endDatetime);
        return leaveDAO.selectMonthLeaveDays(condition);
    }

    @Override
    public List<Leave> queryLeaveList(Leave condition) {
        return leaveDAO.selectList(condition);
    }

    @Override
    public Leave getLeave(String code) {
        Leave data = null;
        if (StringUtils.isNotBlank(code)) {
            Leave condition = new Leave();
            condition.setCode(code);
            data = leaveDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "请假记录不存在！");
            }
        }
        return data;
    }
}
