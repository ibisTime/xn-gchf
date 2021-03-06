package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 转为正常
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631451Req {

    // （必填）工资条编号
    @NotBlank(message = "工资条编号不能为空")
    private String salaryCode;

    // （必填）处理人
    @NotBlank(message = "处理人不能为空")
    private String handler;

    // （必填）处理备注
    @NotBlank(message = "处理备注不能为空")
    private String handleNote;

    public String getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(String salaryCode) {
        this.salaryCode = salaryCode;
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

}
