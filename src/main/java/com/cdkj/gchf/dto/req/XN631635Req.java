package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class XN631635Req {
    @NotBlank
    private String userId;

    @NotBlank
    // 参建单位编码
    private String code;

    // 发放工资的银行
    private List<XN631635ReqBankInfos> bankInfos;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<XN631635ReqBankInfos> getBankInfos() {
        return bankInfos;
    }

    public void setBankInfos(List<XN631635ReqBankInfos> bankInfos) {
        this.bankInfos = bankInfos;
    }

}
