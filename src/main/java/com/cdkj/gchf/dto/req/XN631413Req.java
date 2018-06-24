package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.cdkj.gchf.domain.Skill;

public class XN631413Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）联系方式
    // @NotBlank(message = "联系方式不能为空")
    private String mobile;

    // （必填）紧急联系人
    // @NotBlank(message = "紧急联系人不能为空")
    private String contacts;

    // （必填）紧急联系人电话
    // @NotBlank(message = "紧急联系人电话不能为空")
    private String contactsMobile;

    // 职位
    private String position;

    // 薪资
    private String salary;

    // 迟到/早退扣款金额
    private String cutAmount;

    // （必填）更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // （必填）银行别称
    @NotBlank(message = "银行别称不能为空")
    private String bankCode;

    // （必填）银行名称
    @NotBlank(message = "银行名称不能为空")
    private String bankName;

    // （必填）银行卡号
    @NotBlank(message = "银行卡号不能为空")
    private String bankcardNumber;

    // （必填）开户行
    @NotBlank(message = "开户行不能为空")
    private String subbranch;

    // 技能
    private List<Skill> skillList;

    // （选填）备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public String getPosition() {
        return position;
    }

    public String getSalary() {
        return salary;
    }

    public String getCutAmount() {
        return cutAmount;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setCutAmount(String cutAmount) {
        this.cutAmount = cutAmount;
    }

}
