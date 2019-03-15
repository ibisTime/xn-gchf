package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 编辑操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午5:07:09 
 * @history:
 */
public class XN631122Req {

    // 编号
    @NotBlank
    private String code;

    // 标题
    @NotBlank(message = "标题不能为空")
    private String title;

    // 内容
    private String content;

    // 排序编号
    @NotBlank(message = "排序编号不能为空")
    private String orderNo;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

}
