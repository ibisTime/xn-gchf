package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 菜单角色-查询菜单列表Req
 * @author: xieyj 
 * @since: 2016年5月16日 下午10:45:20 
 * @history:
 */
public class XN631056Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -4329352415706904462L;

    // 角色编号（必填）
    @NotBlank(message = "角色编号不能为空")
    private String roleCode;

    // 类型(选填)
    private String type;

    // 父菜单编号(选填)
    private String parentCode;

    // 更新人(选填)
    private String updater;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
