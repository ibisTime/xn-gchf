package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.dao.IAttendanceDAO;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.exception.BizException;

@Component
public class AttendanceBOImpl extends PaginableBOImpl<Attendance>
        implements IAttendanceBO {

    @Autowired
    private IAttendanceDAO attendanceDAO;

    public void saveAttendance(Attendance data) {
        attendanceDAO.insert(data);
    }

    @Override
    public void startWorkMachineClockIn(Attendance data) {
        attendanceDAO.updateStartMachineClockIn(data);
    }

    @Override
    public void endWorkMachineClockIn(Attendance data) {
        attendanceDAO.updateEndMachineClockIn(data);
    }

    @Override
    public void updateSettleStatus(String code, String status,
            Date settleDatetime) {
        Attendance data = new Attendance();
        data.setCode(code);
        data.setStatus(status);
        data.setSettleDatetime(settleDatetime);
        attendanceDAO.updateSettleStatus(data);
    }

    @Override
    public void updateStaffMobile(String staffCode, String mobile) {
        Attendance data = new Attendance();
        data.setStaffCode(staffCode);
        data.setStaffMobile(mobile);
        attendanceDAO.updateStaffMobile(data);
    }

    @Override
    public void updateStatus(String code, String status) {
        Attendance data = new Attendance();
        data.setCode(code);
        data.setStatus(status);
        attendanceDAO.updateStatus(data);
    }

    @Override
    public void startWorkManualClockIn(String code, String status,
            Date startDatetime) {
        Attendance data = new Attendance();
        data.setCode(code);
        data.setStatus(status);
        data.setStartDatetime(startDatetime);
        attendanceDAO.updateStartManualClockIn(data);
    }

    @Override
    public void endWorkManualClockIn(String code, String status,
            Date endDatetime) {
        Attendance data = new Attendance();
        data.setCode(code);
        data.setStatus(status);
        data.setEndDatetime(endDatetime);
        attendanceDAO.updateEndManualClockIn(data);
    }

    @Override
    public Attendance getAttendance(String code) {
        Attendance data = null;
        if (StringUtils.isNotBlank(code)) {
            Attendance condition = new Attendance();
            condition.setCode(code);
            data = attendanceDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "考勤不存在");
            }
        }
        return data;
    }

    @Override
    public Attendance getAttendanceByProject(String projectCode,
            String staffCode, Date attendTime) {
        Attendance condition = new Attendance();
        condition.setProjectCode(projectCode);
        condition.setStaffCode(staffCode);
        condition.setCreateDatetimeStart(DateUtil.getStartDatetime(
            DateUtil.dateToStr(attendTime, DateUtil.FRONT_DATE_FORMAT_STRING)));
        condition.setCreateDatetimeEnd(DateUtil.getEndDatetime(
            DateUtil.dateToStr(attendTime, DateUtil.FRONT_DATE_FORMAT_STRING)));
        List<Attendance> list = attendanceDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Attendance> queryAttendanceListByStaff(String staffCode,
            String projectCode, Date startDatetime, Date endDatetime,
            List<String> statusList) {
        Attendance condition = new Attendance();
        condition.setStaffCode(staffCode);
        condition.setProjectCode(projectCode);
        condition.setCreateDatetimeStart(startDatetime);
        condition.setCreateDatetimeEnd(endDatetime);
        condition.setStatusList(statusList);
        return queryAttendanceList(condition);
    }

    @Override
    public List<Attendance> queryAttendanceList(Attendance condition) {
        return attendanceDAO.selectList(condition);
    }
}
