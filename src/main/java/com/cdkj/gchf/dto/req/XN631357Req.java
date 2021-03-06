package com.cdkj.gchf.dto.req;

import java.util.List;

public class XN631357Req extends APageReq {

    private static final long serialVersionUID = 5138736221155343722L;

    // 审核人
    private String approver;

    // 负责人
    private String chargeUser;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 关键字
    private String keyword;

    // 用户类型
    private String kind;

    // 项目编号
    private List<String> projectCodeList;

    // 项目名称
    private String projectCode;

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

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(String chargeUser) {
        this.chargeUser = chargeUser;
    }

}
