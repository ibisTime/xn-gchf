package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.cdkj.gchf.gov.APIRequestBase;

/**
 * 查询人员合同
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631917Req extends APIRequestBase {

    private static final long serialVersionUID = 5105455320838321749L;

    // 项目编码
    @NotBlank
    private String projectCode;

    // 所属企业统一社会信用代码
    private String corpCode;

    // 所属企业名称
    private String corpName;

    // 证件类型
    private String idCardType;

    // 证件号码
    private String idCardNumber;

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

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

}
