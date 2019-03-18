package com.cdkj.gchf.dto.req;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上传人员进退场
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631914ReqWorker {

    // 证件类型
    @NotBlank
    private String idCardType;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 时间
    private Date date;

    // 类型
    @Min(0)
    private Integer type;

    // 凭证扫描件
    @NotBlank
    private String voucherUrl;

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
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
