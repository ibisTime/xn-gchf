package com.cdkj.gchf.dto.req;

public class XN630405Req extends APageReq {

    // 名称（选填）
    private String name;

    // 字母序号（选填）
    private String letter;

    // 状态（选填）
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
