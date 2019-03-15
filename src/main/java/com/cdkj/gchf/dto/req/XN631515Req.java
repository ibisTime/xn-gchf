package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

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

    // 组织编号（银行端：银行编号/监管端：监管区域编号）
    @NotBlank
    private String organizationCode;

    // 系统编号(银行端：B/监管端：S)
    private String systemCode;

    // 关键字
    private String keyword;

    // 更新人
    private String updater;

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

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
