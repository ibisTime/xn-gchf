package com.cdkj.gchf.dto.req;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName:  XN631630Req   
 * @Description:TODO
 * @author: Old3
 * @date:   2019年3月19日 下午5:42:52     
 * @Copyright:
 */
public class XN631630Req {
    // 用户id
    @NotBlank
    private String userId;

    // 项目编码
    @NotBlank
    private String projectCode;

    // 项目名称
    private String projectName;

    // 统一社会信用代码
    @NotBlank
    private String corpCode;

    // 参建类型
    @NotBlank
    private String corpType;

    // 进场时间。格式 yyyy-MM-dd HH:mm:ss
    private Date entryTime;

    // 退场时间。格式 yyyy-MM-dd HH:mm:ss
    private Date exitTime;

    // 项目经理名称
    private String pmName;

    // 项目经理证件类型
    private String pmIDCardType;

    // 项目经理证件号码
    private String pmIDCardNumber;

    // 项目经理电话
    private String pmPhone;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmPhone() {
        return pmPhone;
    }

    public void setPmPhone(String pmPhone) {
        this.pmPhone = pmPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPmIDCardType() {
        return pmIDCardType;
    }

    public void setPmIDCardType(String pmIDCardType) {
        this.pmIDCardType = pmIDCardType;
    }

    public String getPmIDCardNumber() {
        return pmIDCardNumber;
    }

    public void setPmIDCardNumber(String pmIDCardNumber) {
        this.pmIDCardNumber = pmIDCardNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
