package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 增加角色
 * @author: xieyj 
 * @since: 2016年5月16日 下午10:53:50 
 * @history:
 */
public class XN630000Req {

    // 角色名称(必填)
    @NotBlank(message = "角色名称不能为空")
    private String name;

    // 角色等级(必填)
    @NotBlank(message = "角色等级不能为空")
    private String level;

    // 更新人(必填)
    @NotBlank(message = "更新人不能为空")
    private String updater;

    // 备注(选填)
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
