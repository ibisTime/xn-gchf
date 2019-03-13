package com.cdkj.gchf.dto.req;

/**
 * 分页查询企业信息
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631255Req extends APageReq {

    private static final long serialVersionUID = -5911721981138096014L;

    // 统一社会信用代码
    private String corpCode;

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

}
