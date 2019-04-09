package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改角色
 * @author: Gejin 
 * @since: 2016年4月16日 下午5:24:01 
 * @history:
 */
public class XN631042Req {

    // 角色编号(必填)
    @NotBlank(message = "角色编号不能为空")
    private String code;

    // 角色名称(必填)
    @NotBlank(message = "角色名称不能为空")
    private String name;

    // 更新人(必填)
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注(选填)
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
