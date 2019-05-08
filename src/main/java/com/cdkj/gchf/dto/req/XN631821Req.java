package com.cdkj.gchf.dto.req;

public class XN631821Req {
    // 设备编号
    private String code;

    // 设备名称
    private String name;

    // 设备tag
    private String tag;

    // 用户id
    private String userId;

    // 考勤设备方向
    private String direction;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
