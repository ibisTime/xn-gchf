package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631635Req {
    @NotBlank
    private String userId;

    @NotEmpty
    // 参建单位编码
    private List<String> codeList;

    // 发放工资的银行
    private List<XN631635ReqBankInfos> bankInfos;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
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
