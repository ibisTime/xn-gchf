package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XN631730Req {
    // 用户id
    @NotBlank
    private String userId;

    // 员工编号
    @NotBlank
    private String projectWorkerCode;

    // 日期
    private Date date;

    // 类型
    private Integer type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectWorkerCode() {
        return projectWorkerCode;
    }

    public void setProjectWorkerCode(String projectWorkerCode) {
        this.projectWorkerCode = projectWorkerCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}