package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 承包商合同修改
 * @author: CYL 
 * @since: 2018年4月28日 下午4:49:43 
 * @history:
 */
public class XN631372Req {

    // 合同编号
    @NotBlank(message = "合同编号不能为空")
    private String code;

    // 承包商名称
    @NotBlank(message = "承包商名称不能为空")
    private String bname;

    // 承包商手机
    @NotBlank(message = "承包商手机不能为空")
    private String bmobile;

    // 免冠照片
    @NotBlank(message = "免冠照片不能为空")
    private String pict1;

    // 手持身份证照片
    @NotBlank(message = "手持身份证照片不能为空")
    private String pict2;

    // 身份证正反照+签名
    @NotBlank(message = "身份证正反照+签名不能为空")
    private String pict3;

    // 合同照片
    @NotBlank(message = "合同照片不能为空")
    private String contentPic;

    // 签约时间
    @NotBlank(message = "签约时间不能为空")
    private String contractDatetime;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
