package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上传人员进退场
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631914ReqWorker {

    private static final long serialVersionUID = 8006164993445757938L;

    // 证件类型
    @NotBlank
    private String idcardType;

    // 证件号码
    @NotBlank
    private String idcardNumber;

    // 时间
    @NotBlank
    private Date date;

    // 类型
    @NotBlank
    private Integer type;

    // 凭证扫描件
    @NotBlank
    private String voucherUrl;

    public String getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(String idcardType) {
        this.idcardType = idcardType;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
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

    public String getVoucherUrl() {
        return voucherUrl;
    }

    public void setVoucherUrl(String voucherUrl) {
        this.voucherUrl = voucherUrl;
    }

}
