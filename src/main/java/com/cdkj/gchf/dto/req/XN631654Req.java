package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 批量上传班组
 * @author: silver 
 * @since: Mar 25, 2019 5:31:45 PM 
 * @history:
 */
public class XN631654Req {
    // 编号
    @NotEmpty
    private List<String> codeList;

    // id
    @NotBlank
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
