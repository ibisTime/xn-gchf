package com.cdkj.gchf.domain;

import java.util.Date;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
* 操作日志
* @author: xiongk 
* @since: 2019-03-22 11:08:02
* @history:
*/
public class OperateLog extends ABaseDO {

    private static final long serialVersionUID = -8910685600823192009L;

    // 编号
    private String code;

    // 关联类型
    private String refType;

    // 关联编号
    private String refCode;

    // 操作名称
    private String operate;

    // 操作人编号
    private String operator;

    // 操作人名称
    private String operatorName;

    // 操作时间
    private Date operateDatetime;

    // 备注
    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getRefCode() {
        return refCode;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperateDatetime(Date operateDatetime) {
        this.operateDatetime = operateDatetime;
    }

    public Date getOperateDatetime() {
        return operateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}
