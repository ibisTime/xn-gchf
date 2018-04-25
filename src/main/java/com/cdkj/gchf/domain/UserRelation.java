/**
 * @Title UserRelation.java 
 * @Package com.std.user.domain 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 上午9:37:08 
 * @version V1.0   
 */
package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 上午9:37:08 
 * @history:
 */
public class UserRelation extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 关系人编号(下家，关注人)
    private String toUser;

    // 状态(1 正常 0 假删除定时器删除 预留)
    private String status;

    // @see EUserReleationType
    private String type;

    // 更新时间
    private Date updateDatetime;

    // 系统编号
    private String companyCode;

    // 系统编号
    private String systemCode;

    // 用户
    private User fromUserInfo;

    // 用户
    private User toUserInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public User getFromUserInfo() {
        return fromUserInfo;
    }

    public void setFromUserInfo(User fromUserInfo) {
        this.fromUserInfo = fromUserInfo;
    }

    public User getToUserInfo() {
        return toUserInfo;
    }

    public void setToUserInfo(User toUserInfo) {
        this.toUserInfo = toUserInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
