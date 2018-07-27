package com.cdkj.gchf.dto.req;

/**
 * 工程进度分页查询
 * @author: CYL 
 * @since: 2018年4月28日 下午4:47:18 
 * @history:
 */
public class XN631385Req extends APageReq {
    private static final long serialVersionUID = 5138736221155343722L;

    private String projectCode;// 工程编号

    private String updater;// 更新人

    private String keyword;// 关键字

    // 用户类型
    private String kind;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
