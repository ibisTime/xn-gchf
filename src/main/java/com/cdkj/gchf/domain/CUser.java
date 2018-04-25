package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

public class CUser extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6495303240604513334L;

    // 编号
    private String userId;

    // 登录名
    private String loginName;

    // 手机号
    private String mobile;

    // 头像
    private String photo;

    // 昵称
    private String nickname;

    // 登录密码
    private String loginPwd;

    // 登录密码强度
    private String loginPwdStrength;

    // 推荐人编号
    private String userReferee;

    // 推荐人手机号
    private String refereeMobile;

    // 支付密码
    private String tradePwd;

    // 支付密码强度
    private String tradePwdStrength;

    // 证件类型
    private String idKind;

    // 证件号
    private Long idNo;

    // 真实姓名
    private String realName;

    // 积分余额
    private Long jfAmount;

    // 账户余额
    private Long amount;

    // 信用分
    private Long creditscore;

    // 状态
    private String status;

    // 注册时间
    private Date createDatetime;

    // 备注
    private String remark;

    // *****************db******************
    private Date createDatetimeStart;

    private Date createDatetimeEnd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId2) {
        this.userId = userId2;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getRefereeMobile() {
        return refereeMobile;
    }

    public void setRefereeMobile(String refereeMobile) {
        this.refereeMobile = refereeMobile;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getTradePwdStrength() {
        return tradePwdStrength;
    }

    public void setTradePwdStrength(String tradePwdStrength) {
        this.tradePwdStrength = tradePwdStrength;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getIdNo() {
        return idNo;
    }

    public void setIdNo(Long idNo) {
        this.idNo = idNo;
    }

    public Long getJfAmount() {
        return jfAmount;
    }

    public void setJfAmount(Long jfAmount) {
        this.jfAmount = jfAmount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCreditscore() {
        return creditscore;
    }

    public void setCreditscore(Long creditscore) {
        this.creditscore = creditscore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

}
