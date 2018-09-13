package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
 * 项目
 * @author: silver 
 * @since: 2018年8月5日 下午2:49:02 
 * @history:
 */
public class Project extends ABaseDO {

    private static final long serialVersionUID = 205424217452030142L;

    // 编号
    private String code;

    // 项目名称
    private String name;

    // 承建单位
    private String companyName;

    // 负责人
    private String chargeUser;

    // 负责人手机号
    private String chargeMobile;

    // 上班时间（09：30）
    private String attendanceStarttime;

    // 下班时间（17：30）
    private String attendanceEndtime;

    // 项目开始时间
    private Date startDatetime;

    // 项目结束时间
    private Date endDatetime;

    // 工资条形成时间（每个月的几号）
    private String salaryCreateDatetime;

    // 经度
    private String longitude;

    // 纬度
    private String latitude;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 地址
    private String address;

    // 薪资发放时间（每个月的几号）
    private String salaryDatetime;

    // 薪资发放可延迟天数（监管端填写）
    private Integer salaryDelayDays;

    // 监管单位编号
    private String superviseCode;

    // 状态(0待编辑/1待开工/2在建/3停工/4结束)
    private String status;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 审核备注
    private String approveNote;

    // 修改人
    private String updater;

    // 修改时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ************************DB Properties*****************

    // 关键字(项目名称/负责人手机号)
    private String keyword;

    // 项目账户
    private ProjectCard projectCard;

    // 统计报表
    private Report report;

    // 审核人姓名
    private String approveName;

    // 更新人姓名
    private String updateName;

    // 查询用户的类型
    private String kind;

    // 项目编号列表
    private List<String> projectCodeList;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(String chargeUser) {
        this.chargeUser = chargeUser;
    }

    public String getChargeMobile() {
        return chargeMobile;
    }

    public void setChargeMobile(String chargeMobile) {
        this.chargeMobile = chargeMobile;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getAttendanceStarttime() {
        return attendanceStarttime;
    }

    public void setAttendanceStarttime(String attendanceStarttime) {
        this.attendanceStarttime = attendanceStarttime;
    }

    public String getAttendanceEndtime() {
        return attendanceEndtime;
    }

    public void setAttendanceEndtime(String attendanceEndtime) {
        this.attendanceEndtime = attendanceEndtime;
    }

    public String getSalaryCreateDatetime() {
        return salaryCreateDatetime;
    }

    public void setSalaryCreateDatetime(String salaryCreateDatetime) {
        this.salaryCreateDatetime = salaryCreateDatetime;
    }

    public String getSalaryDatetime() {
        return salaryDatetime;
    }

    public void setSalaryDatetime(String salaryDatetime) {
        this.salaryDatetime = salaryDatetime;
    }

    public Integer getSalaryDelayDays() {
        return salaryDelayDays;
    }

    public void setSalaryDelayDays(Integer salaryDelayDays) {
        this.salaryDelayDays = salaryDelayDays;
    }

    public ProjectCard getProjectCard() {
        return projectCard;
    }

    public void setProjectCard(ProjectCard companyCard) {
        this.projectCard = companyCard;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSuperviseCode() {
        return superviseCode;
    }

    public void setSuperviseCode(String superviseCode) {
        this.superviseCode = superviseCode;
    }

}
