package com.cdkj.gchf.dto.req;

public class XN630435Req extends APageReq {

    private String brandCode; // 品牌编号

    private String brandName; // 品牌名称

    private String seriesCode; // 车系编号

    private String seriesName; // 车系名称

    private String carCode; // 车型编号

    private String carName; // 车型名称

    private String price; // 车辆总价

    private String sfRate; // 首付比例

    private String sfAmount; // 首付金额

    private String periods; // 分期期数

    private String status; // 状态

    private String handler; // 处理人

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSfRate() {
        return sfRate;
    }

    public void setSfRate(String sfRate) {
        this.sfRate = sfRate;
    }

    public String getSfAmount() {
        return sfAmount;
    }

    public void setSfAmount(String sfAmount) {
        this.sfAmount = sfAmount;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
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

}
