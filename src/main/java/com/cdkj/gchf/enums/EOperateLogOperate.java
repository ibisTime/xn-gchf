package com.cdkj.gchf.enums;

/**
 * 操作日志操作
 * @author: silver 
 * @since: Mar 21, 2019 2:01:14 PM 
 * @history:
 */
public enum EOperateLogOperate {

    UploadCorpBasicinfo("01", "上传企业基本信息"),

    StartProjectConfig("02", "启用项目配置"),

    StopProjectConfig("03", "停用项目配置");

    EOperateLogOperate(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
