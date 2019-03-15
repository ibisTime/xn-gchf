package com.cdkj.gchf.dto.req;

/**
 * 列表查项目配置
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631626Req extends AListReq {
    private static final long serialVersionUID = 7723624296709412993L;

    // 国家平台项目编码
    private String projectCode;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

}
