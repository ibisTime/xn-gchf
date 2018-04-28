package com.cdkj.gchf.dto.req;

public class XN631093Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -4578161783507127781L;

    // 银行别称 （选填）
    private String bankCode;

    // 银行名称 （选填）
    private String bankName;

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

}
