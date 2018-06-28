package com.cdkj.gchf.dto.res;

/**
 * 累计薪资查询
 * @author: silver 
 * @since: 2018年6月28日 下午5:53:42 
 * @history:
 */
public class XN631448Res {
    // 项目编号
    private String projectCode;

    // 月份
    private String month;

    // 累计发薪金额
    private String totalSalary;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }

}
