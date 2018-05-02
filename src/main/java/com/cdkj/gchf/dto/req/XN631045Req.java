package com.cdkj.gchf.dto.req;

/**
 * 分页查询角色
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:49:45 
 * @history:
 */
public class XN631045Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 角色名称(选填)
    private String name;

    // 更新人(选填)
    private String updater;

    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
