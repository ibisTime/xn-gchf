package com.cdkj.gchf.dto.req;

public class XN631357Req extends APageReq {

    private static final long serialVersionUID = 5138736221155343722L;

    // 公司编号 （选填）
    private String companyCode;

    private String approver;// 审核人

    private String status;// 状态

    private String updater;// 更新人

    private String keyword;// 关键字

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

}
