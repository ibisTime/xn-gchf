package com.cdkj.gchf.enums;

public enum EStaffSalaryStatus {

    TO_Pay("0", "工资待发放"), Normal_Pay("1", "正常发放"), Pay_Portion("2",
            "部分发放"), Pay_Aagin("3", "已补发");

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
