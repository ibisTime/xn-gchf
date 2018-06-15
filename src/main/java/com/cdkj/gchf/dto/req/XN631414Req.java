package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改/设置部门
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631414Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填） 手持身份张照片
    @NotBlank(message = "手持身份张照片不能为空")
    private String pict2;

    // （选填）身份证正面照
    @NotBlank(message = "身份证正面照不能为空")
    private String pict3;

    // （必填）身份证反面照
    @NotBlank(message = "身份证反面照不能为空")
    private String pict4;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    public String getCode() {
        return code;
    }

    public String getPict2() {
        return pict2;
    }

    public String getPict3() {
        return pict3;
    }

    public String getPict4() {
        return pict4;
    }

    public String getUpdater() {
        return updater;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPict2(String pict2) {
        this.pict2 = pict2;
    }

    public void setPict3(String pict3) {
        this.pict3 = pict3;
    }

    public void setPict4(String pict4) {
        this.pict4 = pict4;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
