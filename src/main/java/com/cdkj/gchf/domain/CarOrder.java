package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

public class CarOrder extends ABaseDO {

    private String code; // 编号

    private String userId; // 申请人编号

    private String userMobile; // 申请人手机号

    private String brandCode; // 品牌编号

    private String brandName; // 品牌名称

    private String seriesCode; // 车系编号

    private String seriesName; // 车系名称

    private String carCode; // 车型编号

    private String carName; // 车型名称

    private Long price; // 车辆总价

    private double sfRate; // 首付比例

    private Long sfAmount; // 首付金额

    private Integer periods; // 分期期数

    private Date createDatetime; // 申请时间

    private String saleDesc; // 计算器信息

    private String status; // 状态

    private String handler; // 处理人

    private Date handleDatetime; // 处理时间

    private String remark; // 备注

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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public double getSfRate() {
        return sfRate;
    }

    public void setSfRate(double sfRate) {
        this.sfRate = sfRate;
    }

    public Long getSfAmount() {
        return sfAmount;
    }

    public void setSfAmount(Long sfAmount) {
        this.sfAmount = sfAmount;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Date getHandleDatetime() {
        return handleDatetime;
    }

    public void setHandleDatetime(Date handleDatetime) {
        this.handleDatetime = handleDatetime;
    }

    public String getSaleDesc() {
        return saleDesc;
    }

    public void setSaleDesc(String saleDesc) {
        this.saleDesc = saleDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
