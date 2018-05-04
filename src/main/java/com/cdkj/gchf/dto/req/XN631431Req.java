package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.cdkj.gchf.api.impl.XN631439Req;

/**
 * 处理代发消息
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631431Req {

    // 编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // 发送人
    @NotBlank(message = "发送人不能为空")
    private String handler;

    // 发送备注
    private String handleNote;

    // 代发消息
    private List<XN631439Req> payList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public List<XN631439Req> getPayList() {
        return payList;
    }

    public void setPayList(List<XN631439Req> payList) {
        this.payList = payList;
    }

}
