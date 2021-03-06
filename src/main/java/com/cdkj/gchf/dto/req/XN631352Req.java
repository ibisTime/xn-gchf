package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改项目
 * @author: silver 
 * @since: 2018年7月17日 上午11:19:45 
 * @history:
 */
public class XN631352Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 承建单位
    @NotBlank(message = "承建单位不能为空")
    private String companyName;

    // 负责人编号
    @NotBlank(message = "负责人编号不能为空")
    private String chargeUser;

    // 负责人电话
    @NotBlank(message = "负责人电话不能为空")
    private String chargeMobile;

    // 经度
    @NotBlank(message = "经度不能为空")
    private String longitude;

    // 纬度
    @NotBlank(message = "纬度不能为空")
    private String latitude;

    // 省
    @NotBlank(message = "省不能为空")
    private String province;

    // 市
    @NotBlank(message = "市不能为空")
    private String city;

    // 区
    @NotBlank(message = "区不能为空")
    private String area;

    // 项目开始时间
    @NotBlank(message = "项目开始时间不能为空")
    private String startDatetime;

    // 工资条行程时间
    @NotBlank(message = "工资条行程时间不能为空")
    private String salaryCreateDatetime;

    // 薪资发放时间
    @NotBlank(message = "薪资发放时间不能为空")
    private String salaryDatetime;

    // 上班时间
    @NotBlank(message = "上班时间不能为空")
    private String attendanceStarttime;

    // 下班时间
    @NotBlank(message = "下班时间不能为空")
    private String attendanceEndtime;

    // 银行行别
    @NotBlank
    private String bankCode;

    // 银行名称
    @NotBlank
    private String bankName;

    // 开户行
    @NotBlank
    private String subbranch;

    // 户名
    @NotBlank
    private String accountName;

    // 银行卡号
    @NotBlank
    private String bankcardNumber;

    // 修改人
    @NotBlank(message = "修改人不能为空")
    private String updater;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(String chargeUser) {
        this.chargeUser = chargeUser;
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

    public String getChargeMobile() {
        return chargeMobile;
    }

    public void setChargeMobile(String chargeMobile) {
        this.chargeMobile = chargeMobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSalaryDatetime() {
        return salaryDatetime;
    }

    public void setSalaryDatetime(String salaryDatetime) {
        this.salaryDatetime = salaryDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalaryCreateDatetime() {
        return salaryCreateDatetime;
    }

    public void setSalaryCreateDatetime(String salaryCreateDatetime) {
        this.salaryCreateDatetime = salaryCreateDatetime;
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
