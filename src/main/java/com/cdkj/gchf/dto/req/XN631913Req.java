package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.cdkj.gchf.gov.APIRequestBase;

/**
 * 查询项目人员
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631913Req extends APIRequestBase {

    private static final long serialVersionUID = 8006164993445757938L;

    // 项目编码
    @NotBlank
    private String projectCode;

    // 班组所在企业统一社会信用代码
    private String corpCode;

    // 班组所在企业名称
    private String corpName;

    // 班组编号
    private Integer teamSysNo;

    // 班组名称
    private String teamName;

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

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public XN631913Req(String projectCode, String corpCode,
            String idCardNumber) {
        super();
        this.projectCode = projectCode;
        this.corpCode = corpCode;
        this.idCardNumber = idCardNumber;
    }

}
