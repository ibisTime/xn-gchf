package com.cdkj.gchf.enums;

/**
 * 操作日志类型
 * @author: silver 
 * @since: Mar 21, 2019 2:01:14 PM 
 * @history:
 */
public enum EOperateLogRefType {

    CorpBasicinfo("01", "企业基本信息"),

    ProjectConfig("02", "项目配置信息"),

    ProjectCorpinfo("03", "参见单位基本信息"),

    TeamMaster("04", "项目班组信息");

    EOperateLogRefType(String code, String value) {
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
