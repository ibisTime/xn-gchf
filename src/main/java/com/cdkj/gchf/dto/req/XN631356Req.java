package com.cdkj.gchf.dto.req;

import java.util.List;

public class XN631356Req extends APageReq {

    private static final long serialVersionUID = 5138736221155343722L;

    // 公司编号 （选填）
    private String companyCode;

    // 公司名称 （选填）
    private String companyName;

    // 审核人
    private String approver;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 关键字
    private String keyword;

    // 用户类型
    private String kind;

    // 公司编号 （选填）
    private List<String> companyCodeList;

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getCompanyCodeList() {
        return companyCodeList;
    }

    public void setCompanyCodeList(List<String> companyCodeList) {
        this.companyCodeList = companyCodeList;
    }

}
