package com.cdkj.gchf.enums;

/**
 * 
 * @Description:国家平台错误信息转换本地错误信息枚举类
 * @author: Old3
 * @date:   2019年4月18日 下午5:02:56     
 * @Copyright:
 */
public enum EGovErrorMessage {
    WorkerList("[headImage]不能为空!", "项目人员头像信息未上传,请重新建档补充信息后再上传"),

    WebSite("[webSite]格式不正确!", "企业基本信息中网站格式不正确,请检查");

    EGovErrorMessage(String govMessage, String localMessage) {
        this.govMessage = govMessage;
        this.localMessage = localMessage;
    }

    private String govMessage;

    private String localMessage;

    public String getGovMessage() {
        return govMessage;
    }

    public void setGovMessage(String govMessage) {
        this.govMessage = govMessage;
    }

    public String getLocalMessage() {
        return localMessage;
    }

    public void setLocalMessage(String localMessage) {
        this.localMessage = localMessage;
    }

}
