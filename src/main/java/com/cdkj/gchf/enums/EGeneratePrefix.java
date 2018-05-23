package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:29:02 
 * @history:
 */
public enum EGeneratePrefix {

    ADVERTISE("AD", "广告"), DH("DH", "导航"), TRADE_ORDER("JY",
            "交易订单"), ARBITRATE("ZC", "仲裁工单"),

    Account("A", "账户"), AJour("AJ", "账户流水"), EXCHANGE_CURRENCY("EC",
            "币种兑换"), HLORDER("HL",
                    "红蓝订单"), Charge("CZ", "充值订单"), Withdraw("QX", "取现订单"),

    Collection("CO", "归集订单"), Department("D", "部门编号"), Company("C",
            "公司编号"), Project("P",
                    "项目编号"), Bcontract("B", "承包合同"), CompanyBank("CB", "公司账户表"),

    Progress("PR", "工程进度"), Employ("E", "雇佣接口"), Staff("S", "员工编号"), BankCard(
            "BK", "工资卡编号"), Ccontract("CC", "用工合同"), Attendance("AT", "考勤表"),

    Salary("SA", "工资条"), Message("M", "代发消息"), SalaryLog("SA", "工资日志"), Report(
            "R", "统计报表"), StaffLog("SL", "员工记录"), QueryLog("QL", "查询记录"),

    Skill("SK", "技能证书");

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
