package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631727Req extends AListReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -4399740571913291554L;

    private String projectCode;

    private String corpCode;

    // userId
    @NotBlank
    private String userId;

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
