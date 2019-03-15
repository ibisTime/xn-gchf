package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 手工生成工资条
 * @author: silver 
 * @since: 2018年7月5日 下午2:03:19 
 * @history:
 */
public class XN631440Req {
    // （必填）编号
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    // (必填)生成工资月份
    @NotBlank(message = "生成工资月份不能为空")
    private String month;

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

}
