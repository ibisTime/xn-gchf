package com.cdkj.gchf.dto.req;

/**
 * 列表查询角色
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:51:10 
 * @history:
 */
public class XN631046Req {

    // 角色名称(选填)
    private String name;


    // 更新人(选填)
    private String updater;

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
}
