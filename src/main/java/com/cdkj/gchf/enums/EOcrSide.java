package com.cdkj.gchf.enums;

/**
 * OCR身份证识别方向
 *
 * @author: Xiongk
 * @since: 2019-05-15 17:44
 */
public enum EOcrSide {

    FACE("face", "正面"),

    BACK("back", "反面");

    EOcrSide(String code, String value) {
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
