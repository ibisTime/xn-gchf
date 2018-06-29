package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Attendance;

//dao层 
public interface IAttendanceDAO extends IBaseDAO<Attendance> {
    String NAMESPACE = IAttendanceDAO.class.getName().concat(".");

    // 考勤系统上班打卡
    void toStart(Attendance data);

    // 考勤系统下班打卡
    void toEnd(Attendance data);

    // 手动上班打卡
    public int updateStartClockIn(Attendance data);

    // 手动下班打卡
    public int updateEndClockIn(Attendance data);

    void updateStatus(Attendance data);

    // 更新员工手机号
    public int updateStaffMobile(Attendance data);

}
