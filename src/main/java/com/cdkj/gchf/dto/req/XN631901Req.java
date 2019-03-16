package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.cdkj.gchf.gov.APIRequestBase;

/**
 * 查询企业信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631901Req extends APIRequestBase {

    private static final long serialVersionUID = -960637709706535621L;

    @NotBlank
    private String projectCode;

    // 统一社会信用代码
    @NotBlank
    private String corpCode;

    // 企业名称
    private String corpName;

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
