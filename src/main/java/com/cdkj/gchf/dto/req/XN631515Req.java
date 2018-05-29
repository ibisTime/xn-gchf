package com.cdkj.gchf.dto.req;

/**
 * 分页查询事件通知人
 * @author: nyc 
 * @since: 2018年5月28日 下午7:48:10 
 * @history:
 */
public class XN631515Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -7688557109750376437L;

    // 关键字
    private String keyword;

    // 更新人
    private String updater;

    // 类型
    private String type;

    public String getKeyword() {
        return keyword;
    }

    public String getUpdater() {
        return updater;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
