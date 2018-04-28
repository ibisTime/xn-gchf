package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 平台用户
* @author: chenshan 
* @since: 2018-04-27 18:26:24
* @history:
*/
public class CompanyCard extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 项目编号
    private String projectCode;

    // 项目名称
    private String projectName;

    // 公司编号
    private String companyCode;

    // 公司名称
    private String companyName;

    // 银行行别
    private String bankCode;

    // 银行名称
    private String bankName;

    // 开户行
    private String subbranch;

    // 银行卡号
    private String bankcardNumber;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

}
