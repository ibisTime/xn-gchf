package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 合同录入
 * @author: nyc 
 * @since: 2018年4月29日 下午10:21:06 
 * @history:
 */
public class XN631400Req {

    // （必填）项目编号
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    // （必填）工人编号
    @NotBlank(message = "工人编号不能为空")
    private String staffCode;

    // （必填）合同照片
    @NotBlank(message = "合同照片不能为空")
    private String contentPic;

    // （必填）签约时间
    @NotBlank(message = "签约时间不能为空")
    private String contractDatetime;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
