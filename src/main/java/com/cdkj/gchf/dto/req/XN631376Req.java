package com.cdkj.gchf.dto.req;

public class XN631376Req extends APageReq {
    private static final long serialVersionUID = 5138736221155343722L;

    private String companyCode;// 公司编号

    private String projectCode;// 项目编号

    private String updater;// 更新人

    private String keyword;// 关键字

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
