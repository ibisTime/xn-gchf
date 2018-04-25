/**
 * @Title XNlh5033Req.java 
 * @Package com.xnjr.moom.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午10:43:57 
 * @version V1.0   
 */
package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询部门
 * @author: nyc 
 * @since: 2018年4月25日 下午9:00:02 
 * @history:
 */
public class XN631037Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
