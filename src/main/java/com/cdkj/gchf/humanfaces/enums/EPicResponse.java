package com.cdkj.gchf.humanfaces.enums;

/**
 * @ClassName: EPicResponse
 * @Description:人员照片响应枚举
 * @author: Old3
 * @date: 2019年5月6日 下午1:45:04
 * @Copyright:
 */
public enum EPicResponse {
    //图片相关响应
    TIANJIACHENG("GS_SUS600", "照片录入成功");

    EPicResponse(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
