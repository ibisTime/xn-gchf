package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class XN631713ReqData {
    // 企业统一社会信用代码
    @NotBlank
    private String corpCode;

    // 企业名称
    @NotBlank
    private String corpName;

    // 班组名称
    @NotBlank
    private String teamName;

    // 证件类型
    @NotBlank
    private String idcardType;

    // 证件号码
    @NotBlank
    private String idcardNumber;

    // 刷卡时间
    @NotBlank
    private Date date;

    // 刷卡进出方向
    @NotBlank
    private String direction;

    // 通行方向
    private String attendType;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAttendType() {
        return attendType;
    }

    public void setAttendType(String attendType) {
        this.attendType = attendType;
    }

}
