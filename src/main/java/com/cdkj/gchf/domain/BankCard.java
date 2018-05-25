package com.cdkj.gchf.domain;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 工资卡
* @author: nyc 
* @since: 2018-04-29 20:42:12
* @history:
*/
public class BankCard extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 员工编号
    private String staffCode;

    // 员工姓名
    private String staffName;

    // 银行行别
    private String bankCode;

    // 银行名称
    private String bankName;

    // 开户支行
    private String subbranch;

    // 银行卡号
    private String bankcardNumber;

    // 创建时间
    private Date createDatetime;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // *******************db***************
    private String keyword;

    private String updateName;

    private String kind;

    private List<Employ> list;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
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

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Employ> getList() {
        return list;
    }

    public void setList(List<Employ> list) {
        this.list = list;
    }

}
