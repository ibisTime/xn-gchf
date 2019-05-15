package com.cdkj.gchf.enums;

/**
 * 合同类型
 *
 * @author: silver
 * @since: Mar 29, 2019 2:11:09 PM
 * @history:
 */
public enum EWorkerContractType {

    //固定期限合同
    GUDING_HETONG("0", "固定期限合同"),
    //以一定工作量为期限的合同
    GONGZUOLIANG_HETONG("1", "以一定工作量为期限的合同");

    EWorkerContractType(String code, String status) {
        this.code = code;
        this.status = status;
    }

    private String code;

    private String status;

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}
