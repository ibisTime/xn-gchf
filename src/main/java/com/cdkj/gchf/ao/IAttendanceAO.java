package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Attendance;

@Component
public interface IAttendanceAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void dropAttendance(String code);

    public void editAttendance(String projectCode, String staffCode);

    public Paginable<Attendance> queryAttendancePage(int start, int limit,
            Attendance condition);

    public List<Attendance> queryAttendanceList(Attendance condition);

    public Attendance getAttendance(String code);

    public void addAttendance(String projectCode, String staffCode);

}
