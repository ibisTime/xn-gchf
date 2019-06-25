package com.cdkj.gchf.dto.req;

import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author : old3
 * @since : 2019-06-21 1:44
 */
public class XN631851Req {

    private String userId;

    @NotEmpty(message = "编号不能为空")
    private List<String> codeList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}

    
    