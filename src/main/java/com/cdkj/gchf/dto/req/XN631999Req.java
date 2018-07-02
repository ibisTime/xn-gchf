/**
 * @Title XNlh5010Req.java 
 * @Package com.xnjr.moom.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:00:54 
 * @version V1.0   
 */
package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 工资条测试接口
 * @author: silver 
 * @since: 2018年6月26日 上午12:09:37 
 * @history:
 */
public class XN631999Req {
    // 密码
    @NotBlank
    private String password;

    // 方法
    @NotBlank
    private String method;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
