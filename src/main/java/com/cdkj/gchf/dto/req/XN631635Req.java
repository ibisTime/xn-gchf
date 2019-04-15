package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class XN631635Req {
    @NotBlank
    // 参建单位编码
    private String code;

    @NotBlank
    // 项目编码
    private String projectCode;

    @NotBlank
    // 统一社会信用代码
    private String corpCode;

    @NotBlank
    // 企业名称
    private String corpName;

    @NotBlank
    // 企业类型
    private String corpType;

    // 进场时间
    private String entryTime;

    // 退场时间
    private String exitTime;

    // 项目经理名称
    private String pmName;

    // 项目经理证件类型
    private String pmIDCardType;

    // 项目经理证件号码
    private String pmIDCardNumber;

    // 项目经理手机号
    private String pmPhone;

    // 发放工资的银行
    private List<XN631635ReqBankInfos> bankInfos;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmIDCardType() {
        return pmIDCardType;
    }

    public void setPmIDCardType(String pmIDCardType) {
        this.pmIDCardType = pmIDCardType;
    }

    public String getPmIDCardNumber() {
        return pmIDCardNumber;
    }

    public void setPmIDCardNumber(String pmIDCardNumber) {
        this.pmIDCardNumber = pmIDCardNumber;
    }

    public String getPmPhone() {
        return pmPhone;
    }

    public void setPmPhone(String pmPhone) {
        this.pmPhone = pmPhone;
    }

    public List<XN631635ReqBankInfos> getBankInfos() {
        return bankInfos;
    }

    public void setBankInfos(List<XN631635ReqBankInfos> bankInfos) {
        this.bankInfos = bankInfos;
    }

}
