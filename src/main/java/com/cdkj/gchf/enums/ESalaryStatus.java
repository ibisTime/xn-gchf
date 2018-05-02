package com.cdkj.gchf.enums;

public enum ESalaryStatus {

    To_Approve("0", "待人工复核"), TO_Send("1", "已审核待提交"), TO_Pay("2",
            "已提交待发放"), Payed("3",
                    "已发放"), Pay_Portion("4", "部分发放"), Pay_Again("5", "补发");

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
