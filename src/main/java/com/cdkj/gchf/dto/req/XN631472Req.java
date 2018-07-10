package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改发放薪资可延迟天数（监管端）
 * @author: silver 
 * @since: 2018年7月9日 下午5:19:12 
 * @history:
 */
public class XN631472Req {
    // 编号
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    // 薪资发放可延迟天数
    @NotBlank(message = "薪资发放可延迟天数不能为空")
    private String salaryDelayDays;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getSalaryDelayDays() {
        return salaryDelayDays;
    }

    public void setSalaryDelayDays(String salaryDelayDays) {
        this.salaryDelayDays = salaryDelayDays;
    }

}
