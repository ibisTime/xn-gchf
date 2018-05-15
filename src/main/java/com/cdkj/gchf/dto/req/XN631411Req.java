package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 录入身份证和特征值
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631411Req {

    // （必填）姓名
    @NotBlank(message = "姓名不能为空")
    private String realName;

    // （必填）性别
    @NotBlank(message = "性别不能为空")
    private String sex;

    // （必填）民族
    @NotBlank(message = "民族不能为空")
    private String idNation;

    // （必填）出生年月日
    @NotBlank(message = "出生年月日不能为空")
    private String birthday;

    // （必填）证件类型
    @NotBlank(message = "证件类型不能为空")
    private String idKind;

    // （必填）身份证号
    @NotBlank(message = "身份证号不能为空")
    private String idNo;

    // （必填）身份证上籍贯
    @NotBlank(message = "籍贯不能为空")
    private String idAddress;

    // （必填）身份证上头像
    @NotBlank(message = "身份证上头像不能为空")
    private String idPic;

    // （必填）签发机关
    @NotBlank(message = "签发机关不能为空")
    private String idPolice;

    // （必填）证件有效结束时间
    @NotBlank(message = "证件有效结束时间不能为空")
    private String idEndDate;

    // （必填）证件有效开始时间
    @NotBlank(message = "证件有效开始时间不能为空")
    private String idStartDate;

    // （必填）特征值
    @NotBlank(message = "特征值不能为空")
    private String feat;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFeat() {
        return feat;
    }

    public void setFeat(String feat) {
        this.feat = feat;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getIdEndDate() {
        return idEndDate;
    }

    public void setIdEndDate(String idEndDate) {
        this.idEndDate = idEndDate;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdPic() {
        return idPic;
    }

    public void setIdPic(String idPic) {
        this.idPic = idPic;
    }

    public String getIdPolice() {
        return idPolice;
    }

    public void setIdPolice(String idPolice) {
        this.idPolice = idPolice;
    }

    public String getIdStartDate() {
        return idStartDate;
    }

    public void setIdStartDate(String idStartDate) {
        this.idStartDate = idStartDate;
    }

    public String getIdNation() {
        return idNation;
    }

    public void setIdNation(String idNation) {
        this.idNation = idNation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
