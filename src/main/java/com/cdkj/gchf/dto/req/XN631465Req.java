package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 分页查雇佣员工
 * @author: nyc 
 * @since: 2018年4月29日 下午7:32:21 
 * @history:
 */
public class XN631465Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -9190494112316250359L;

    // （选填）关键字
    private String keyword;

    // 部门编号
    private String departmentCode;

    // （选填）项目编号
    private String projectCode;

    // （选填）状态
    private String status;

    // （选填）类型
    private String type;

    // （选填）更新人
    private String updater;

    // （选填）
    private String staffCode;

    // 状态列表
    private List<String> statusList;

    // 项目列表（监管端查询通讯录）
    private List<String> projectCodeList;

    // 免冠照状态
    private String pict1Status;

    // 特征值状态
    private String featStatus;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getPict1Status() {
        return pict1Status;
    }

    public void setPict1Status(String pict1Status) {
        this.pict1Status = pict1Status;
    }

    public String getFeatStatus() {
        return featStatus;
    }

    public void setFeatStatus(String featStatus) {
        this.featStatus = featStatus;
    }

}
