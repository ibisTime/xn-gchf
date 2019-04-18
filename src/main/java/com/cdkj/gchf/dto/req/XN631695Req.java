package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class XN631695Req {
    @NotEmpty
    private List<String> codeList;

    @NotBlank
    private String uesrId;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public String getUesrId() {
        return uesrId;
    }

    public void setUesrId(String uesrId) {
        this.uesrId = uesrId;
    }

}
