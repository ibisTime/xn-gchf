package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 建档
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631410Req {

    // （必填）姓名
    @NotBlank(message = "姓名不能为空")
    private String realName;

    // （必填）性别
    @NotBlank(message = "性别不能为空")
    private String sex;

    // （必填）民族
    @NotBlank(message = "民族不能为空")
    private String idNation;

    // （必填）身份证号
    @NotBlank(message = "身份证号不能为空")
    private String idNo;

    // （必填）身份证上头像
    @NotBlank(message = "身份证上头像不能为空")
    private String idPic;

    // （必填）出生年月日
    @NotBlank(message = "出生年月日不能为空")
    private String birthday;

    // （必填）籍贯
    @NotBlank(message = "籍贯不能为空")
    private String idAddress;

    // （必填）签发机关
    @NotBlank(message = "签发机关不能为空")
    private String idPolice;

    // （必填）证件有效结束时间
    @NotBlank(message = "证件有效结束时间不能为空")
    private String idEndDate;

    // （必填）证件有效开始时间
    @NotBlank(message = "证件有效开始时间不能为空")
    private String idStartDate;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    public String getRealName() {
        return realName;
    }

    public String getSex() {
        return sex;
    }

    public String getIdNation() {
        return idNation;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getIdPic() {
        return idPic;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public String getIdPolice() {
        return idPolice;
    }

    public String getIdEndDate() {
        return idEndDate;
    }

    public String getIdStartDate() {
        return idStartDate;
    }

    public String getUpdater() {
        return updater;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setIdNation(String idNation) {
        this.idNation = idNation;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setIdPic(String idPic) {
        this.idPic = idPic;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public void setIdPolice(String idPolice) {
        this.idPolice = idPolice;
    }

    public void setIdEndDate(String idEndDate) {
        this.idEndDate = idEndDate;
    }

    public void setIdStartDate(String idStartDate) {
        this.idStartDate = idStartDate;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
