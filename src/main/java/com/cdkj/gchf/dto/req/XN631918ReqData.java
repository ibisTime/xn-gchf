package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上传人员考勤
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631918ReqData {

    // 证件类型
    @NotBlank
    private String idCardType;

    // 证件号码
    @NotBlank
    private String idCardNumber;

    // 考勤时间
    @NotBlank
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
    private String lng;

    // 纬度
    private String lat;

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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

}
