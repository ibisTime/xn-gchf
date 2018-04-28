package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631370Req {

    @NotBlank
    private String projectCode;// 项目编号

    @NotBlank
    private String bname;// 承包商名称

    @NotBlank
    private String bmobile;// 承包商手机

    @NotBlank
    private String contentPic;// 合同照片

    @NotBlank
    private String contractDatetime;// 签约时间

    @NotBlank
    private String updater;// 更新人

    private String remark;// 备注

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBmobile() {
        return bmobile;
    }

    public void setBmobile(String bmobile) {
        this.bmobile = bmobile;
    }

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

    public String getContractDatetime() {
        return contractDatetime;
    }

    public void setContractDatetime(String contractDatetime) {
        this.contractDatetime = contractDatetime;
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
