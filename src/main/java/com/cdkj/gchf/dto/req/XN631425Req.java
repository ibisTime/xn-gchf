package com.cdkj.gchf.dto.req;

/**
 * 分页查工资卡
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631425Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -2799896855768659683L;

    // 项目编号
    private String projectCode;

    // （选填）银行别称
    private String bankCode;

    // （选填）关键字
    private String keyword;

    // （选填）员工编号
    private String staffCode;

    // （选填）状态
    private String status;

    // （选填）更新人
    private String updater;

    // 银行卡号状态（0未录入/1已录入）
    private String numberStatus;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getNumberStatus() {
        return numberStatus;
    }

    public void setNumberStatus(String numberStatus) {
        this.numberStatus = numberStatus;
    }

}
