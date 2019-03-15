package com.cdkj.gchf.enums;

/**
 * 银行卡号状态
 * @author: silver 
 * @since: 2018年9月10日 下午12:48:11 
 * @history:
 */
public enum ECardNumberStatus {

    Non_Input("0", "未录入"), Inputed("1", "已录入");

    ECardNumberStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
