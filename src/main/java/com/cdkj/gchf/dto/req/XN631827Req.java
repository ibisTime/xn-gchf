package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631827Req extends AListReq {
    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1105368290783428192L;

    // 项目编号
    private String projectCode;

    // 设备状态
    private String state;

    // 设备名称
    private String name;

    // 用户id
    @NotBlank
    private String userId;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
