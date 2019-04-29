package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改项目参建单位信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631906Req {
    // 参建单位编号
    private String code;

    // 用户id
    private String userId;

    // 项目编码
    @NotBlank
    private String projectCode;

    // 统一社会信用代码
    @NotBlank
    private String corpCode;

    // 企业名称
    @NotBlank
    private String corpName;

    // 企业登记注册类型
    @NotBlank
    private String corpType;

    // 进场时间
    private Date entryTime;

    // 退场时间
    private Date exitTime;

    // 项目经理名称
    private String pmName;

    // 项目经理证件类型
    private String pmIdcardType;

    // 项目经理证件号码
    private String pmIdcardNumber;

    // 项目经理电话
    private String pmPhone;

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

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    public String getCorpType() {
        return corpType;
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

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmIdcardType(String pmIdcardType) {
        this.pmIdcardType = pmIdcardType;
    }

    public String getPmIdcardType() {
        return pmIdcardType;
    }

    public void setPmIdcardNumber(String pmIdcardNumber) {
        this.pmIdcardNumber = pmIdcardNumber;
    }

    public String getPmIdcardNumber() {
        return pmIdcardNumber;
    }

    public void setPmPhone(String pmPhone) {
        this.pmPhone = pmPhone;
    }

    public String getPmPhone() {
        return pmPhone;
    }

}
