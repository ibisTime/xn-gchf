package com.cdkj.gchf.dto.req;

import com.cdkj.gchf.gov.APIRequestBase;

/**
 * 查询项目参建单位信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631907Req extends APIRequestBase {

    private static final long serialVersionUID = 4903119830939604546L;

    // 项目编码
    private String projectCode;

    // 统一社会信用代码
    private String corpCode;

    // 企业名称
    private String corpName;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

}
