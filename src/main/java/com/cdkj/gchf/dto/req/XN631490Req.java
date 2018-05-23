package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加查询记录
 * @author: nyc 
 * @since: 2018年5月21日 下午4:29:35 
 * @history:
 */
public class XN631490Req {
    // 用户Id
    @NotBlank(message = "用户Id不能为空")
    private String userId;

    // 员工编号
    @NotBlank(message = "员工编号不能为空")
    private String idNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

}
