package com.cdkj.gchf.dto.req;

/**
 * 列表查银行
 * @author: nyc 
 * @since: 2018年5月17日 下午3:07:11 
 * @history:
 */
public class XN631116Req extends APageReq {

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
