package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 录入考勤照片
 *
 * @author: silver
 * @since: May 5, 2019 4:58:23 PM
 * @history:
 */
public class XN631794Req {
    // 编号
    private String code;

    @NotBlank
    private String attendancePicture;

    // 用户id
    @NotBlank
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAttendancePicture() {
        return attendancePicture;
    }

    public void setAttendancePicture(String attendancePicture) {
        this.attendancePicture = attendancePicture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
