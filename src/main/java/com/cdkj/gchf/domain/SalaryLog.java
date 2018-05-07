package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
 * 
 * @author: nyc 
 * @since: 2018年5月1日 上午11:45:10 
 * @history:
 */
public class SalaryLog extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 员工编号
    private String staffCode;

    // 公司编号
    private String companyCode;

    // 公司名称
    private String companyName;

    // 项目编号
    private String projectCode;

    // 项目名称
    private String projectName;

    // 工资条编号
    private String salaryCode;

    // 类型
    private String type;

    // 操作人
    private String handler;

    // 操作时间
    private Date handleDatetime;

    // 操作描述
    private String handleNote;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setSalaryCode(String salaryCode) {
        this.salaryCode = salaryCode;
    }

    public String getSalaryCode() {
        return salaryCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Date getHandleDatetime() {
        return handleDatetime;
    }

    public void setHandleDatetime(Date handleDatetime) {
        this.handleDatetime = handleDatetime;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getHandleNote() {
        return handleNote;
    }

}
