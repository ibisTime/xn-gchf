package com.cdkj.gchf.dto.req;

import java.util.List;

public class XN631386Req extends APageReq {
    private static final long serialVersionUID = 5138736221155343722L;

    private String companyCode;// 公司编号

    private String projectCode;// 工程编号

    private String updater;// 更新人

    private String keyword;// 关键字

    private String kind;

    private List<String> companyCodeList;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getCompanyCodeList() {
        return companyCodeList;
    }

    public void setCompanyCodeList(List<String> companyCodeList) {
        this.companyCodeList = companyCodeList;
    }

}
