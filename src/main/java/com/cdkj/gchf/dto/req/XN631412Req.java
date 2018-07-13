package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改务工人员信息
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631412Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）联系方式
    @NotBlank(message = "联系方式不能为空")
    private String mobile;

    // （必填）免冠照片
    @NotBlank(message = "免冠照片不能为空")
    private String pict1;

    // （必填）身份证正面照
    @NotBlank(message = "身份证正面照不能为空")
    private String pict2;

    // （必填）身份证反面照
    @NotBlank(message = "身份证反面照不能为空")
    private String pict3;

    // （必填）手持身份证照
    @NotBlank(message = "手持身份证照不能为空")
    private String pict4;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （选填）备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPict1() {
        return pict1;
    }

    public void setPict1(String pict1) {
        this.pict1 = pict1;
    }

    public String getPict2() {
        return pict2;
    }

    public void setPict2(String pict2) {
        this.pict2 = pict2;
    }

    public String getPict3() {
        return pict3;
    }

    public void setPict3(String pict3) {
        this.pict3 = pict3;
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

    public String getPict4() {
        return pict4;
    }

    public void setPict4(String pict4) {
        this.pict4 = pict4;
    }

}
