package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IAttendanceAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EStaffStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class AttendanceAOImpl implements IAttendanceAO {

    @Autowired
    private IAttendanceBO attendanceBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IStaffBO staffBO;

    @Autowired
    private IEmployBO employBO;

    @Override
    public void addAttendance(String projectCode, String staffCode) {

    }

    @Override
    public void editAttendance(String projectCode, String staffCode) {
        Project project = projectBO.getProject(projectCode);
        Staff staff = staffBO.getStaff(staffCode);
        Employ employ = employBO.getEmployStaff(staff.getCode());
        Attendance data = attendanceBO.getAttendanceByProject(projectCode,
            staffCode);

        data.setProjectCode(project.getCode());
        data.setProjectName(project.getName());
        data.setStaffCode(staff.getCode());
        data.setProjectName(staff.getName());
        String status = EAttendanceStatus.TO_End.getCode();

        Date now = null;
        Date attendanceDatetime = null;

        if (!EStaffStatus.Leave.getCode().equals(employ.getCode())) {
            // 上班打卡
            if (EAttendanceStatus.TO_Start.getCode().equals(data.getStatus())) {
                data.setStartDatetime(DateUtil.getNow());
                now = DateUtil.strToDate(DateUtil.getNow(),
                    DateUtil.DATA_TIME_PATTERN_7);

                attendanceDatetime = DateUtil.strToDate(
                    project.getAttendanceStarttime(),
                    DateUtil.DATA_TIME_PATTERN_7);
                // 是否迟到
                if (now.after(attendanceDatetime)) {
                    status = EAttendanceStatus.Later.getCode();
                }
                // 下班打卡
            } else if (EAttendanceStatus.TO_End.getCode()
                .equals(data.getStatus())) {
                data.setEndDatetime(DateUtil.getNow());
                now = DateUtil.strToDate(DateUtil.getNow(),
                    DateUtil.DATA_TIME_PATTERN_7);

                attendanceDatetime = DateUtil.strToDate(
                    project.getAttendanceEndtime(),
                    DateUtil.DATA_TIME_PATTERN_7);
                // 早退且迟到
                if (EAttendanceStatus.Later.getCode().equals(data.getStatus())
                        && now.before(attendanceDatetime)) {
                    status = EAttendanceStatus.All.getCode();
                } else if (now.before(attendanceDatetime)) {
                    status = EAttendanceStatus.EarLy.getCode();
                }
            }
            data.setStatus(status);
        } else {
            data.setRemark(EStaffStatus.Leave.getValue());
        }

        attendanceBO.saveAttendance(data);
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

    public static void main(String[] args) {

    }
}
