package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* 人员考勤
* @author: xiongk 
* @since: 2019-03-16 11:13:01
* @history:
*/
public class WorkerAttendance extends ABaseDO {

    private static final long serialVersionUID = -35842659625792834L;

    // 编号
    private String code;

    // 项目编码
    private String projectCode;

    // 项目名称
    private String projectName;

    // 班组编号
    private String teamSysNo;

    // 班组名称
    private String teamName;

    // 员工编号
    private String workerCode;

    // 员工名称
    private String workerName;

    // 证件类型
    private String idCardType;

    // 证件号码
    private String idCardNumber;

    // 出工状态（正常，迟到，早退）
    private String status;

    // 考勤时间
    private Date date;

    // 进出方向
    private String direction;

    // 刷卡近照
    private String image;

    // 通道
    private String channel;

    // 通行方式
    private String attendType;

    // 经度
    private BigDecimal lng;

    // 纬度
    private BigDecimal lat;

    // 生成时间
    private Date createDatetime;

    // 来源
    private String source;

    // 上班时间
    private Date startDatetime;

    // 下班时间
    private Date endDatetime;

    // 结算时间
    private Date settleDatetime;

    // 人脸识别准确率
    private Long sim;

    // 考勤机编号
    private String terminalCode;

    // 备注
    private String remark;

    // 上传状态
    private String uploadStatus;

    /****DB Properties****/
    // 所在企业
    private String corpName;

    private String corpCode;

    // 删除状态
    private String deleteStatus;

    private String userId;

    private List<String> codeList;

    /****DB Properties****/
    private Date dateStartDatetime;

    private Date dateEndDatetime;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeamSysNo() {
        return teamSysNo;
    }

    public void setTeamSysNo(String teamSysNo) {
        this.teamSysNo = teamSysNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAttendType() {
        return attendType;
    }

    public void setAttendType(String attendType) {
        this.attendType = attendType;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Date getSettleDatetime() {
        return settleDatetime;
    }

    public void setSettleDatetime(Date settleDatetime) {
        this.settleDatetime = settleDatetime;
    }

    public Long getSim() {
        return sim;
    }

    public void setSim(Long sim) {
        this.sim = sim;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getDateStartDatetime() {
        return dateStartDatetime;
    }

    public void setDateStartDatetime(Date dateStartDatetime) {
        this.dateStartDatetime = dateStartDatetime;
    }

    public Date getDateEndDatetime() {
        return dateEndDatetime;
    }

    public void setDateEndDatetime(Date dateEndDatetime) {
        this.dateEndDatetime = dateEndDatetime;
    }
}
