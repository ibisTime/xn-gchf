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
    public int removeAttendance(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Attendance data = new Attendance();
            data.setCode(code);
            count = attendanceDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshAttendance(Attendance data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
        }
        return count;
    }

    @Override
    public List<Attendance> queryAttendanceList(Attendance condition) {
        return attendanceDAO.selectList(condition);
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
            String staffCode) {
        Attendance condition = new Attendance();
        condition.setProjectCode(projectCode);
        condition.setStaffCode(staffCode);
        condition.setCreateDatetimeStart(DateUtil.getTodayStart());
        condition.setCreateDatetimeEnd(DateUtil.getTodayEnd());
        List<Attendance> list = attendanceDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn0000", "该项目未生成考勤表");
        }
        return list.get(0);
    }

    @Override
    public void toStart(Attendance data, String status) {
        data.setStartDatetime(DateUtil.getNow());
        data.setStatus(status);
        attendanceDAO.toStart(data);
    }

    @Override
    public void toEnd(Attendance data, String status) {
        data.setEndDatetime(DateUtil.getNow());
        data.setStatus(status);
        attendanceDAO.toEnd(data);
    }

    @Override
    public void updateStatus(Attendance data) {
        data.setSettleDatetime(new Date());
        attendanceDAO.updateStatus(data);
    }
}
