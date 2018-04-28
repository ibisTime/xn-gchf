package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改账户
 * @author: nyc 
 * @since: 2018年4月27日 下午9:40:33 
 * @history:
 */
public class XN631362Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）账户号
    @NotBlank(message = "账户号不能为空")
    private String bankCardNumber;

    // （必填）银行代号
    @NotBlank(message = "银行代号不能为空")
    private String bankCode;

    // （必填）银行名称
    @NotBlank(message = "银行名称不能为空")
    private String bankName;

    // （必填）开户行
    @NotBlank(message = "开户行不能为空")
    private String subbranch;

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

}
