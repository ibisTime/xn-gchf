package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631745Req extends APageReq {
    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -7933187523227424529L;

    // 项目编码
    private String projectCode;

    // 企业统一社会信用代码
    private String corpCode;

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

    @NotBlank
    private String userId;

}
