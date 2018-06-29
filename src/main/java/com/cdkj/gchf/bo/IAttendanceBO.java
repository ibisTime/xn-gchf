package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Attendance;

public interface IAttendanceBO extends IPaginableBO<Attendance> {

    public void saveAttendance(Attendance data);

    // 考勤系统上班打卡
    public void toStart(Attendance data);

    // 考勤系统下班打卡
    public void toEnd(Attendance data);

    // 手动上班打卡
    public void startClockIn(String code, String status, Date startDatetime);

    // 手动下班打卡
    public void endClockIn(String code, String status, Date endDatetime);

    public void updateStatus(Attendance data);

    // 更新员工手机号
    public void updateStaffMobile(String staffCode, String mobile);

    public List<Attendance> queryAttendanceList(Attendance condition);

    public Attendance getAttendance(String code);

    public Attendance getAttendanceByProject(String projectCode,
            String staffCode);
}
