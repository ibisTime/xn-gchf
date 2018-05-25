package com.cdkj.gchf.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESalaryStatus {

    To_Approve("0", "待人工复核"), TO_Send("1", "已审核待提交"), TO_Pay("2",
            "已提交待发放"), Payed("3",
                    "正常发放"), Pay_Portion("4", "部分发放"), Pay_Again("5", "已转正常");

    public static Map<String, ESalaryStatus> getMap() {
        Map<String, ESalaryStatus> map = new HashMap<String, ESalaryStatus>();
        for (ESalaryStatus direction : ESalaryStatus.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    ESalaryStatus(String code, String value) {
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
