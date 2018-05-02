package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改工资条
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631443Req {

    // （必填）编号
    @NotBlank(message = "编号不能为空")
    private String code;

    // （必填）审核人
    @NotBlank(message = "approveNote不能为空")
    private String approver;

    // （必填）审核备注
    private String approveNote;

    // （必填）审核结果
    @NotBlank(message = "审核结果不能为空")
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
