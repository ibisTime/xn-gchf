package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 手动上班打卡
 * @author: silver 
 * @since: 2018年6月25日 下午1:54:33 
 * @history:
 */
public class XN631390Req {
    // 考勤编号
    @NotBlank
    private String code;

    // 上班考勤时间
    @NotBlank
    private String startDatetime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

}
