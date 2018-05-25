package com.cdkj.gchf.dto.req;

/**
 * 分页查工资条日志
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631456Req extends APageReq {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -3735724866853750864L;

    // （选填）操作人
    private String handler;

    // （选填）工资条编号
    private String salaryCode;

    // （选填）员工编号
    private String staffCode;

    // （选填）类型
    private String type;

    // （选填）用户类型
    private String kind;

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(String salaryCode) {
        this.salaryCode = salaryCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
