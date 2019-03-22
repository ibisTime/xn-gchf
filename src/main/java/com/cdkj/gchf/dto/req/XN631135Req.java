package com.cdkj.gchf.dto.req;

/**
 * 分页查询操作日志
 * @author: silver 
 * @since: Mar 13, 2019 1:34:10 PM 
 * @history:
 */
public class XN631135Req extends APageReq {

    private static final long serialVersionUID = 1072995820547228064L;

    // 关联类型
    private String refType;

    // 关联编号
    private String refCode;

    // 操作名称
    private String operate;

    // 操作人编号
    private String operator;

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
