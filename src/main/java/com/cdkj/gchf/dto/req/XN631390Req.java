package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 考勤打卡
 * @author: nyc 
 * @since: 2018年4月30日 下午9:11:05 
 * @history:
 */
public class XN631390Req {

    // （必填）项目编号
    @NotBlank(message = "项目编号不能为空")
    private String projectCode;

    // （必填）员工编号
    @NotBlank(message = "员工编号不能为空")
    private String staffCode;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

}
