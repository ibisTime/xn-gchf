package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IAttendanceDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Attendance;

@Repository("attendanceDAOImpl")
public class AttendanceDAOImpl extends AMybatisTemplate
        implements IAttendanceDAO {

    @Override
    public int insert(Attendance data) {
        return super.insert(NAMESPACE.concat("insert_attendance"), data);
    }

    @Override
    public int delete(Attendance data) {
        return 0;
    }

    @Override
    public Attendance select(Attendance condition) {
        return super.select(NAMESPACE.concat("select_attendance"), condition,
            Attendance.class);
    }

    @Override
    public long selectTotalCount(Attendance condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_attendance_count"), condition);
    }

    @Override
    public List<Attendance> selectList(Attendance condition) {
        return super.selectList(NAMESPACE.concat("select_attendance"),
            condition, Attendance.class);
    }

    @Override
    public List<Attendance> selectList(Attendance condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_attendance"), start,
            count, condition, Attendance.class);
    }

    @Override
    public void updateStartMachineClockIn(Attendance data) {
        super.update(NAMESPACE.concat("update_startMachineClockIn"), data);
    }

    @Override
    public void updateEndMachineClockIn(Attendance data) {
        super.update(NAMESPACE.concat("update_endMachineClockIn"), data);
    }

    @Override
    public void updateStatus(Attendance data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateStartManualClockIn(Attendance data) {
        return super.update(NAMESPACE.concat("update_startManualClockIn"),
            data);
    }

    @Override
    public int updateEndManualClockIn(Attendance data) {
        return super.update(NAMESPACE.concat("update_endManualClockIn"), data);
    }

    @Override
    public int updateStaffMobile(Attendance data) {
        return super.update(NAMESPACE.concat("attendance_updateStaffMobile"),
            data);
    }

}
