package com.cdkj.gchf.dto.req;

/**
 * 新增事件通知人
 * @author: nyc 
 * @since: 2018年5月28日 下午7:48:10 
 * @history:
 */
public class XN631510Req {

    // 名字
    private String name;

    // 手机号
    private String mobile;

    // 异常类型
    private String type;

    // 更新人
    private String updater;

    // 备注
    private String remark;

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getType() {
        return type;
    }

    public String getUpdater() {
        return updater;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
