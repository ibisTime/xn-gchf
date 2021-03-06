package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午3:58:52 
 * @history:
 */
public class XN631070Req {

    // 角色编号（选填）
    private String roleCode;

    // 登录名（必填）
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    // 登录密码（必填）
    @NotBlank(message = "登录密码不能为空")
    private String loginPwd;

    // 真实姓名（必填）
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    // 用户类型（必填）
    @NotBlank(message = "用户类型不能为空")
    private String type;

    // 手机号（选填）
    private String mobile;

    // （选填）项目编号
    private String projectCode;

    // (选填) 银行名称
    private String bankName;

    // (选填) 银行编号
    private String bankCode;

    // （选填） 所属支行
    private String subbranch;

    // （选填） 市
    private String city;

    // （选填） 区
    private String area;

    // （选填） 省
    private String province;

    // 推荐人（选填）
    private String userRefree;

    // 备注（选填）
    private String remark;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserRefree() {
        return userRefree;
    }

    public void setUserRefree(String userRefree) {
        this.userRefree = userRefree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
