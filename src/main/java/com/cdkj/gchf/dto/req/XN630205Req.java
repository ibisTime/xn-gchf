package com.cdkj.gchf.dto.req;

public class XN630205Req extends APageReq {

    private String loginName; // 登录名（选填）

    private String mobile; // 手机号（选填）

    private String userReferee; // 推荐人编号（选填）

    private String refereeMobile; // 推荐人手机号（选填）

    private String idKind; // 证件类型（选填）

    private String idNo; // 证件号（选填）

    private String realName; // 真实姓名（选填）

    private String status; // 状态（选填）

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

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
