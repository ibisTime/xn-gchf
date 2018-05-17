package com.cdkj.gchf.dto.req;

import java.util.List;

public class XN631375Req extends APageReq {
    private static final long serialVersionUID = 5138736221155343722L;

    // 公司编号
    private String companyCode;

    // 项目编号
    private String projectCode;

    // 更新人
    private String updater;

    // 关键字
    private String keyword;

    // 项目编号List
    private List<String> projectCodeList;

    // 用户类型
    private String kind;

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

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
