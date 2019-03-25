package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:29:02 
 * @history:
 */
public enum EGeneratePrefix {

    CollProject("P", "项目编号"),

    SubBranch("SB", "支行"),

    Supervise("SU", "监管单位"),

    OperatorGuide("OG", "操作指南"),

    CorpBasicinfo("CB", "企业基本信息"),

    Project("P", "项目基本信息"),

    ProjectConfig("PC", "项目配置"),

    ProjectCorpInfo("PCI", "项目参建单位"),

    TeamMaster("TM", "TeamMaster"),

    ProjectWorker("PW", "项目员工"),

    ProjectWorkerEntryExitHistory("PWEE", "人员进退场"),

    WorkerContract("WC", "人员合同"),

    WorkerAttendance("WA", "员工考勤"),

    PayRoll("PR", "工资单"),

    PayRollDetail("PRD", "工资单详情"),

    ProjectBuilderLicense("PBL", "项目许可证"),

    BankCardInfo("BCI", "银行卡"),

    OperateLog("OL", "操作日志");

    public static Map<String, EGeneratePrefix> getMap() {
        Map<String, EGeneratePrefix> map = new HashMap<String, EGeneratePrefix>();
        for (EGeneratePrefix orderType : EGeneratePrefix.values()) {
            map.put(orderType.getCode(), orderType);
        }
        return map;
    }

    EGeneratePrefix(String code, String value) {
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
