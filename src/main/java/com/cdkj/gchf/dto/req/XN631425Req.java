package com.cdkj.gchf.dto.req;

import java.util.Date;

public class XN631425Req extends APageReq {

    private static final long serialVersionUID = 5138736221155343722L;

    private Date startDatetime;// 项目开始时间

    private Date endDatetime;// 项目结束时间

    private String province;// 省

    private String city;// 市

    private String area;// 区

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

}
