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

    StopProjectConfig("03", "停用项目配置"),

    UploadProjectCorpinfo("04", "上传参见单位基本信息"),

    EditProjectCorpinfo("05", "修改参见单位基本信息"),

    UploadTeamMaster("06", "上传项目班组信息"),

    UploadWorkContract("07", "上传劳动合同信息"),

    UploadWorkAtendance("08", "上传人员考勤信息"),

    UploadProjectWorkerEntryExitHistory("09", "上传人员进退场信息"),

    UploadPayRoll("10", "上传工资单信息"),

    UploadProjectWorker("11", "上传项目人员信息");

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