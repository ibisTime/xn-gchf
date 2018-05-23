package com.cdkj.gchf.domain;

import com.cdkj.gchf.dao.base.ABaseDO;

/**
 * 代发消息记录
 * @author: nyc 
 * @since: 2018年5月1日 上午11:31:30 
 * @history:
 */

public class MessageLog extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 代发消息编号
    private String messageCode;

    // 工资条编号
    private String salaryCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(String salaryCode) {
        this.salaryCode = salaryCode;
    }

}
