package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
 * 员工
 * @author: nyc 
 * @since: 2018年4月29日 下午8:48:09 
 * @history:
 */
public class Staff extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 姓名
    private String name;

    // 手机号
    private String mobile;

    // 证件类型
    private String idType;

    // 证件号
    private String idNo;

    // 籍贯
    private String place;

    // 免冠照片
    private String pict1;

    // 手持身份张照片
    private String pict2;

    // 身份证正反面照片+签名
    private String pict3;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ***************db***********
    private String keyword;

    private BankCard bankCard;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPict1(String pict1) {
        this.pict1 = pict1;
    }

    public String getPict1() {
        return pict1;
    }

    public void setPict2(String pict2) {
        this.pict2 = pict2;
    }

    public String getPict2() {
        return pict2;
    }

    public void setPict3(String pict3) {
        this.pict3 = pict3;
    }

    public String getPict3() {
        return pict3;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

}
