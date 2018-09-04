package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 手动下班打卡
 * @author: silver 
 * @since: 2018年6月25日 下午1:54:33 
 * @history:
 */
public class XN631391Req {
    // 考勤编号
    @NotEmpty
    private List<String> codeList;

    // 考勤开始时间
    @NotEmpty
    private String attendanceStartDatetime;

    // 考勤结束时间
    @NotEmpty
    private String attendanceEndDatetime;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public String getAttendanceStartDatetime() {
        return attendanceStartDatetime;
    }

    public void setAttendanceStartDatetime(String attendanceStartDatetime) {
        this.attendanceStartDatetime = attendanceStartDatetime;
    }

    public String getAttendanceEndDatetime() {
        return attendanceEndDatetime;
    }

    public void setAttendanceEndDatetime(String attendanceEndDatetime) {
        this.attendanceEndDatetime = attendanceEndDatetime;
    }
}
