package com.cdkj.gchf.dto.req;

/**
 * 分页查询考勤记录
 * @author: nyc 
 * @since: 2018年4月30日 下午9:11:05 
 * @history:
 */
public class XN631395Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8582556386723542049L;

    // （选填）关键字
    private String keyword;

    // （选填）项目编号
    private String projectCode;

    // （选填）状态
    private String status;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
