package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XN631633ReqList {

    // 统一社会信用代码
    @NotBlank
    private String corpCode;

    // 企业名称
    private String corpName;

    // 企业登记注册类型
    private String corpType;

    // 进场时间
    private Date entryTime;

    // 退场时间
    private Date exitTime;

    // 项目经理名称
    private String pmName;

    // 项目经理证件类型
    private String pmIDCardType;

    // 项目经理证件号码
    private String pmIDCardNumber;

    // 项目经理电话
    private String pmPhone;

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

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
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

}
