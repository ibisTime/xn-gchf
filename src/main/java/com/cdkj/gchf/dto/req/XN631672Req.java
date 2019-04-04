package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN631672Req {
    // userId
    @NotBlank
    private String userId;

    // 编号
    @NotBlank
    private String code;

    // 开始时间
    private String startDate;

    // 结束时间
    private String endDate;

    // 计量单位
    private String unit;

    private String unitPrice;

    private String contentPic;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

}
