package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631665Req extends APageReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    private String projectCode;

    private String corpCode;

    // 编号
    private String code;

    // id
    @NotBlank
    private String userId;

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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

}
