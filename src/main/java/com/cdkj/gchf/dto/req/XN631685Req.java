package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631685Req extends APageReq {

    /**   
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -684934372637549134L;

    // 项目编码
    private String projectCode;

    // 企业唯一信用编码
    private String corpCode;

    // 合同类型
    private String contractPeriodType;

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

    public String getContractPeriodType() {
        return contractPeriodType;
    }

    public void setContractPeriodType(String contractPeriodType) {
        this.contractPeriodType = contractPeriodType;
    }

}
