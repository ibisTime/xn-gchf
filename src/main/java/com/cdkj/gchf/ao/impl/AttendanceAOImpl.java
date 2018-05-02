package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IAttendanceAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.exception.BizException;

@Service
public class AttendanceAOImpl implements IAttendanceAO {

    @Autowired
    private IAttendanceBO attendanceBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IStaffBO staffBO;

    @Override
    public void addAttendance(String projectCode, String staffCode) {

    }

    @Override
    public void editAttendance(String projectCode, String staffCode) {
        Project project = projectBO.getProject(projectCode);
        Staff staff = staffBO.getStaff(staffCode);

        Attendance data = attendanceBO.getAttendanceByProject(projectCode);
        data.setProjectCode(project.getCode());
        data.setProjectName(project.getName());
        data.setStaffCode(staff.getCode());
        data.setProjectName(staff.getName());
        data.setStartDatetime(DateUtil.getNow());
        data.setEndDatetime(DateUtil.getNow());

    }

    @Override
    public void dropAttendance(String code) {
    }

    @Override
    public Paginable<Attendance> queryAttendancePage(int start, int limit,
            Attendance condition) {
        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeStart()
                    .after(condition.getCreateDatetimeEnd())) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }

        Paginable<Attendance> page = attendanceBO.getPaginable(start, limit,
            condition);
        List<Attendance> list = page.getList();
        for (Attendance attendance : list) {
            Staff staff = staffBO.getStaff(attendance.getStaffCode());
            attendance.setStaffName(staff.getName());
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<Attendance> queryAttendanceList(Attendance condition) {
        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeStart()
                    .after(condition.getCreateDatetimeEnd())) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }

        List<Attendance> list = attendanceBO.queryAttendanceList(condition);
        for (Attendance attendance : list) {
            Staff staff = staffBO.getStaff(attendance.getStaffCode());
            attendance.setStaffName(staff.getName());
        }
        return list;
    }

    @Override
    public Attendance getAttendance(String code) {
        return attendanceBO.getAttendance(code);
    }
}
