package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询技能
 * @author: nyc 
 * @since: 2018年5月23日 下午4:57:36 
 * @history:
 */
public class XN631506Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -9213907109437910760L;

    // （必填）员工编号
    @NotBlank(message = "编号不能为空")
    private String staffCode;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

}
