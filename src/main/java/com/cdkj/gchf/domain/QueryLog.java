package com.cdkj.gchf.domain;

import java.util.List;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
 * 查询历史
 * @author: nyc 
 * @since: 2018年5月21日 下午3:46:48 
 * @history:
 */
public class QueryLog extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 7662586236271410426L;

    // 编号
    private String code;

    // 用户ID
    private String userId;

    // 员工编号
    private String staffCode;

    // 员工姓名
    private String staffName;

    // 证件号
    private String idNo;

    // 身份证上头像
    private String pic1;

    // ***********db*************

    // 关键字
    private String keyword;

    // 项目编号
    private List<Employ> employList;

    // 职位
    private String position;

    // 工资发放状态
    private String salaryStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Employ> getEmployList() {
        return employList;
    }

    public void setEmployList(List<Employ> employList) {
        this.employList = employList;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalaryStatus() {
        return salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

}
