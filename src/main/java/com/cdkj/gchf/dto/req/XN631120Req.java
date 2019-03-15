package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午5:07:09 
 * @history:
 */
public class XN631120Req {

    // 标题
    @NotBlank(message = "标题不能为空")
    private String title;

    // 内容
    private String content;

    // 排序编号
    @NotBlank(message = "排序编号不能为空")
    private String orderNo;

    // 系统编号
    @NotBlank(message = "系统编号不能为空")
    private String systemCode;

    // 备注
    private String remark;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    private String updater;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

}
