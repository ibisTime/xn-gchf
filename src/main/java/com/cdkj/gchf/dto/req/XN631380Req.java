package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631380Req {

    // 项目编号
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    // 进度时间
    @NotBlank(message = "进度时间不能为空")
    private String datetime;

    // 工程进度描述
    @NotBlank(message = "工程进度描述不能为空")
    private String description;

    // 工程进度图片
    @NotBlank(message = "工程进度图片不能为空")
    private String picture;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注
    private String remark;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
