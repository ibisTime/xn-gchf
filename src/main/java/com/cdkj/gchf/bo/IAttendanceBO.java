package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Attendance;

public interface IAttendanceBO extends IPaginableBO<Attendance> {

    public void saveAttendance(Attendance data);

    public int removeAttendance(String code);

    public int refreshAttendance(Attendance data);

    public List<Attendance> queryAttendanceList(Attendance condition);

    public Attendance getAttendance(String code);

    public Attendance getAttendanceByProject(String projectCode,
            String staffCode);

}
