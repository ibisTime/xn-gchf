package com.cdkj.gchf.dto.req;

import com.cdkj.gchf.common.StringUtil;
import org.hibernate.validator.constraints.NotBlank;

public class XN631791Req {
    // 用户id
    @NotBlank
    private String userId;

    // 编号
    @NotBlank
    private String code;

    // 手持身份证照片
    @NotBlank
    private String handIdCardImageUrl;

    // 正面照 URL
    @NotBlank
    private String positiveIdCardImageUrl;

    // 反面照 URL
    @NotBlank
    private String negativeIdCardImageUrl;

    //头像
    private String headImageUrl;

    // 考勤照片
    private String attendancePicture;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPositiveIdCardImageUrl() {
        return positiveIdCardImageUrl;
    }

    public void setPositiveIdCardImageUrl(String positiveIdCardImageUrl) {
        this.positiveIdCardImageUrl = positiveIdCardImageUrl;
    }

    public String getNegativeIdCardImageUrl() {
        return negativeIdCardImageUrl;
    }

    public void setNegativeIdCardImageUrl(String negativeIdCardImageUrl) {
        this.negativeIdCardImageUrl = negativeIdCardImageUrl;
    }

    public String getHandIdCardImageUrl() {
        return handIdCardImageUrl;
    }

    public void setHandIdCardImageUrl(String handIdCardImageUrl) {
        this.handIdCardImageUrl = handIdCardImageUrl;
    }

    public String getAttendancePicture() {
        return attendancePicture;
    }

    public void setAttendancePicture(String attendancePicture) {
        this.attendancePicture = attendancePicture;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
}
