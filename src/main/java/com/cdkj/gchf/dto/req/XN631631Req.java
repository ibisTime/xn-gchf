package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class XN631631Req {
    @NotEmpty
    private List<String> codeList;

    @NotEmpty
    private String userId;

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

}
