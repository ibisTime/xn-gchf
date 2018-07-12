package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工工资卡是否存在
 * @author: silver 
 * @since: 2018年7月12日 上午10:40:57 
 * @history:
 */
public class XN631428Req {

    // （必填）员工编号
    @NotBlank(message = "员工编号不能为空")
    private String staffCode;

    // （必填）公司编号
    @NotBlank(message = "公司编号不能为空")
    private String companyCode;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
