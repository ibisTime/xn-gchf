package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

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

    // 公司编号
    private String companyCode;

    // 公司名称
    private String companyName;

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

    // 特征值
    private String feat;

    // ***************db***********
    // 关键字模糊查询
    private String keyword;

    // 工资卡
    private BankCard bankCard;

    // 公司编号List
    private List<String> projectCodeList;

    // 更新人名字
    private String updateName;

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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getFeat() {
        return feat;
    }

    public void setFeat(String feat) {
        this.feat = feat;
    }

}
