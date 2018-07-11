package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加工资卡
 * @author: silver 
 * @since: 2018年7月11日 上午10:37:02 
 * @history:
 */
public class XN631420Req {

    // 公司编号
    @NotBlank(message = "公司编号不能为空")
    private String companyCode;

    // 员工编号
    @NotBlank(message = "员工编号不能为空")
    private String staffCode;

    // 员工姓名
    @NotBlank(message = "员工姓名不能为空")
    private String staffName;

    // （必填）银行别称
    @NotBlank(message = "银行别称不能为空")
    private String bankCode;

    // （必填）银行名称
    @NotBlank(message = "银行名称不能为空")
    private String bankName;

    // （必填）开户支行
    @NotBlank(message = "开户支行不能为空")
    private String subbranch;

    // （必填）银行卡号
    @NotBlank(message = "银行卡号不能为空")
    private String bankcardNumber;

    // （选填）备注
    private String remark;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
