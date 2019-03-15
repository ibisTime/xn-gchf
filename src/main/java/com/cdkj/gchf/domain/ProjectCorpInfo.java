package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 参建单位
* @author: xiongk 
* @since: 2019-03-15 14:34:32
* @history:
*/
public class ProjectCorpInfo extends ABaseDO {

    private static final long serialVersionUID = -7557784826164456147L;

    // 编号
    private String code;

    // 本地项目编号
    private String localProjectCode;

    // 项目编码
    private String projectCode;

    // 项目名称
    private String projectName;

    // 统一社会信用代码
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
    private String pmIdcardType;

    // 项目经理证件号码
    private String pmIdcardNumber;

    // 项目经理电话
    private String pmPhone;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setLocalProjectCode(String localProjectCode) {
        this.localProjectCode = localProjectCode;
    }

    public String getLocalProjectCode() {
        return localProjectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
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
