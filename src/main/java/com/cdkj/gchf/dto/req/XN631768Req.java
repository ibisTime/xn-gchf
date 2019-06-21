package com.cdkj.gchf.dto.req;

/**
 * @author old3
 * @title: XN631768Req
 * @description: 查询实名制银行卡
 * @date 2019-05-21 17:52
 */
public class XN631768Req {

    /**
     * workerCode : userId :
     */

    private String workerCode;
    private String userId;

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
