package com.cdkj.gchf.dto.req;

public class XN631670ReqAttachments {
    // 附件名称
    private String name;

    // 附件 Base64 字符串或文件地址
    private String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
