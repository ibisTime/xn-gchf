package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Attendance;

//dao层 
public interface IAttendanceDAO extends IBaseDAO<Attendance> {
    String NAMESPACE = IAttendanceDAO.class.getName().concat(".");

    // 考勤系统上班打卡
    void updateStartMachineClockIn(Attendance data);

    // 考勤系统下班打卡
    void updateEndMachineClockIn(Attendance data);

    // 手动上班打卡
    public int updateStartManualClockIn(Attendance data);

    // 手动下班打卡
    public int updateEndManualClockIn(Attendance data);

    // 更新结算状态
    void updateSettleStatus(Attendance data);

    // 更新状态字段
    public int updateStatus(Attendance data);
}
