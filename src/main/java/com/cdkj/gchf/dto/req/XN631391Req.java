package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 手动下班打卡
 * @author: silver 
 * @since: 2018年6月25日 下午1:54:33 
 * @history:
 */
public class XN631391Req {
    // 考勤编号
    @NotBlank
    private String code;

    // 上班考勤时间
    @NotBlank
    private String endDatetime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

}
