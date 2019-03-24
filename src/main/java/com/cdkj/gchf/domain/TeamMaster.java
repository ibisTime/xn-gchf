package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 班组
* @author: xiongk 
* @since: 2019-03-15 16:45:07
* @history:
*/
public class TeamMaster extends ABaseDO {

    private static final long serialVersionUID = 7065973191239470963L;

    // 编号
    private String code;

    // 班组编号
    private Integer teamSysNo;

    // 项目编码
    private String projectCode;

    // 班组所在企业统一社会信用代码
    private String corpCode;

    // 班组所在企业名称
    private String corpName;

    // 班组名称
    private String teamName;

    // 班组长姓名
    private String teamLeaderName;

    // 班组长联系电话
    private String teamLeaderPhone;

    // 班组长证件类型
    private String teamLeaderIdcardType;

    // 班组长证件号码
    private String teamLeaderIdNumber;

    // 责任人姓名
    private String responsiblePersonName;

    // 责任人联系电话
    private String responsiblePersonPhone;

    // 责任人证件类型
    private String responsiblePersonIdcardType;

    // 责任人证件号码
    private String responsiblePersonIdNumber;

    // 备注
    private String remark;

    // 进场日期
    private Date entryTime;

    // 退场日期
    private Date exitTime;

    // 上级部门编号
    private String parentCode;

    /****DB Properties****/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(Integer teamSysNo) {
        this.teamSysNo = teamSysNo;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLeaderName() {
        return teamLeaderName;
    }

    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
    }

    public String getTeamLeaderPhone() {
        return teamLeaderPhone;
    }

    public void setTeamLeaderPhone(String teamLeaderPhone) {
        this.teamLeaderPhone = teamLeaderPhone;
    }

    public String getTeamLeaderIdcardType() {
        return teamLeaderIdcardType;
    }

    public void setTeamLeaderIdcardType(String teamLeaderIdcardType) {
        this.teamLeaderIdcardType = teamLeaderIdcardType;
    }

    public String getTeamLeaderIdNumber() {
        return teamLeaderIdNumber;
    }

    public void setTeamLeaderIdNumber(String teamLeaderIdNumber) {
        this.teamLeaderIdNumber = teamLeaderIdNumber;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public String getResponsiblePersonPhone() {
        return responsiblePersonPhone;
    }

    public void setResponsiblePersonPhone(String responsiblePersonPhone) {
        this.responsiblePersonPhone = responsiblePersonPhone;
    }

    public String getResponsiblePersonIdcardType() {
        return responsiblePersonIdcardType;
    }

    public void setResponsiblePersonIdcardType(
            String responsiblePersonIdcardType) {
        this.responsiblePersonIdcardType = responsiblePersonIdcardType;
    }

    public String getResponsiblePersonIdNumber() {
        return responsiblePersonIdNumber;
    }

    public void setResponsiblePersonIdNumber(String responsiblePersonIdNumber) {
        this.responsiblePersonIdNumber = responsiblePersonIdNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}
