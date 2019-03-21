package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 上传企业信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:26 PM 
 * @history:
 */
public class XN631253Req {

    // 编号
    @NotEmpty
    private List<String> codeList;

    // 用户编号
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
