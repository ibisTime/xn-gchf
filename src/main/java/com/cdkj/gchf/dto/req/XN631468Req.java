package com.cdkj.gchf.dto.req;

import java.util.List;

/**
 * 分页查询请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午4:31:04 
 * @history:
 */
public class XN631468Req extends APageReq {

    private static final long serialVersionUID = -9190494112316250359L;

    // 员工编号
    private String staffCode;

    // 项目编号
    private String projectCode;

    // 雇佣编号
    private String employCode;

    // 项目编号列表
    private List<String> projectCodeList;

    // 部门
    private String departmentCode;

    // 职位
    private String position;

    // 雇佣状态
    private String employStatus;

    // 员工姓名
    private String staffName;

    public List<String> getProjectCodeList() {
        return projectCodeList;
    }

    public void setProjectCodeList(List<String> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployStatus() {
        return employStatus;
    }

    public void setEmployStatus(String employStatus) {
        this.employStatus = employStatus;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

}
