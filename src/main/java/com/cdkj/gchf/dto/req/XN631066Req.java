package com.cdkj.gchf.dto.req;

public class XN631066Req {

    // 菜单名称(选填)
    private String name;

    // 类型(选填)
    private String type;

    // 系统编号(选填)
    private String systemCode;

    // 父菜单编号(选填)
    private String parentCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
