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

    TeamMaster("04", "项目班组信息"),

    WorkContract("05", "劳动合同信息"),

    WorkAttendance("06", "人员考勤信息"),

    ProjectWorkerEntryExitHistory("07", "人员进退场信息"),

    PayRoll("08", "工资单信息"),

    WorkerInfo("09", "人员实名制信息"),

    PayRollDetail("10", "工资单详情信息"),

    ProjectWorker("11", "项目人员信息");

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
