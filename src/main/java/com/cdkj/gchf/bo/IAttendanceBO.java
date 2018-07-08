package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Attendance;

public interface IAttendanceBO extends IPaginableBO<Attendance> {

    public void saveAttendance(Attendance data);

    // 考勤系统上班打卡
    public void startWorkMachineClockIn(Attendance data);

    // 考勤系统下班打卡
    public void endWorkMachineClockIn(Attendance data);

    // 手动上班打卡
    public void startWorkManualClockIn(String code, String status,
            Date startDatetime);

    // 手动下班打卡
    public void endWorkManualClockIn(String code, String status,
            Date endDatetime);

    // 更新薪资结算状态
    public void updateSettleStatus(String code, String status,
            Date settleDatetime);

    // 更新员工手机号
    public void updateStaffMobile(String staffCode, String mobile);

    public List<Attendance> queryAttendanceList(Attendance condition);

    public List<Attendance> queryAttendanceListByStaff(String staffCode,
            String projectCode, Date startDatetime, Date endDatetime,
            String status);

    public Attendance getAttendance(String code);

    public Attendance getAttendanceByProject(String projectCode,
            String staffCode, Date attendTime);
}
