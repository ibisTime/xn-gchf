package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XN631732Req {
    // 用户id
    @NotBlank
    private String userId;

    // 编号
    @NotBlank
    private String code;

    // 项目编码
    private String projectCode;

    // 工人所属企业统一社会信用代码
    private String corpCode;

    // 工人所属企业名称
    private String corpName;

    // 班组编号
    private Integer teamSysNo;

    // 员工编号
    private String workerCode;

    // 员工姓名
    private String workerName;

    // 职位
    private String position;

    // 入职时间
    private Date joinDatetime;

    // 入职时间
    private Date leavingDatetime;

    // 证件类型
    private String idcardType;

    // 证件号码
    private String idcardNumber;

    // 时间
    @NotBlank
    private String date;

    // 类型
    private Integer type;

    // 凭证扫描件
    private String voucherUrl;

    // 状态
    private String upload_status;

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

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getJoinDatetime() {
        return joinDatetime;
    }

    public void setJoinDatetime(Date joinDatetime) {
        this.joinDatetime = joinDatetime;
    }

    public Date getLeavingDatetime() {
        return leavingDatetime;
    }

    public void setLeavingDatetime(Date leavingDatetime) {
        this.leavingDatetime = leavingDatetime;
    }

    public String getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(String idcardType) {
        this.idcardType = idcardType;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVoucherUrl() {
        return voucherUrl;
    }

    public void setVoucherUrl(String voucherUrl) {
        this.voucherUrl = voucherUrl;
    }

    public String getUpload_status() {
        return upload_status;
    }

    public void setUpload_status(String upload_status) {
        this.upload_status = upload_status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
