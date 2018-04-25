package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 平台用户
* @author: chenshan 
* @since: 2018-04-24 10:03:59
* @history:
*/
public class User extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 用户Id
    private String userId;

    // 类型
    private String type;

    // 头像
    private String photo;

    // 登录名
    private String loginName;

    // 手机号
    private String mobile;

    // 登录密码
    private String loginPwd;

    // 登录密码强度
    private String loginPwdStrength;

    // 推荐人
    private String userRefree;

    // 注册时间
    private Date createDatetme;

    // 角色编号
    private String roleCode;

    // 部门编号
    private String departmentCode;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 状态
    private String status;

    // 备注
    private String remark;

    // **************db**********
    private Date createDatetimeStart;

    private Date createDatetimeEnd;

    // 登录名等模糊查询
    private String keyword;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setCreateDatetme(Date createDatetme) {
        this.createDatetme = createDatetme;
    }

    public Date getCreateDatetme() {
        return createDatetme;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
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

}
