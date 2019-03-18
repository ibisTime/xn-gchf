package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;
import com.cdkj.gchf.dto.req.XN631914ReqWorker;

/**
* 人员进退场
* @author: xiongk 
* @since: 2019-03-16 10:58:20
* @history:
*/
public class ProjectWorkerEntryExitHistory extends ABaseDO {

    private static final long serialVersionUID = 5322175115302808562L;

    // 编号
    private String code;

    // 本地项目编号
    private String localProjectCode;

    // 项目编码
    private String projectCode;

    // 班组所在企业统一社会信用代码
    private String corpCode;

    // 班组所在企业名称
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

    // 离职时间
    private Date leavingDatetime;

    // 证件类型
    private String idcardType;

    // 证件号码
    private String idcardNumber;

    // 时间
    private Date date;

    // 类型
    private Integer type;

    // 凭证扫描件
    private String voucherUrl;

    /****DB Properties****/
    private List<XN631914ReqWorker> workerList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocalProjectCode() {
        return localProjectCode;
    }

    public void setLocalProjectCode(String localProjectCode) {
        this.localProjectCode = localProjectCode;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<XN631914ReqWorker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<XN631914ReqWorker> workerList) {
        this.workerList = workerList;
    }

}
