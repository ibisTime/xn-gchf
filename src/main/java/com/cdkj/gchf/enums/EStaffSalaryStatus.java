package com.cdkj.gchf.enums;

public enum EStaffSalaryStatus {

    Normal("1", "正常发放"), Pay_Portion("2", "部分发放"), Pay_Aagin("3", "已转正常");

    EStaffSalaryStatus(String code, String value) {
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
