package com.cdkj.gchf.ao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Attendance;

@Component
public interface IAttendanceAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 上班考勤手动打卡
    public void startWorkManualClockIn(List<String> codeList,
            Date attendanceStartDatetime, Date attendanceEndDatetime);

    // 下班考勤手动打卡
    public void endWorkManualClockIn(List<String> codeList,
            Date attendanceStartDatetime, Date attendanceEndDatetime);

    // 闸机考勤系统调用
    public String manchineClockIn(String sim, String projectCode,
            String staffCode, String attendTime, String terminalCode);

    // 定时器每天凌晨形成考勤记录
    public void createAttendanceDaily();

    public Paginable<Attendance> queryAttendancePage(int start, int limit,
            Attendance condition);

    public List<Attendance> queryAttendanceList(Attendance condition);

    public Attendance getAttendance(String code);

}
