package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 批量删除工资条
 * @author: silver 
 * @since: 2018年7月5日 下午2:38:45 
 * @history:
 */
public class XN631441Req {
    // （必填）编号
    @NotEmpty(message = "项目编号不能为空")
    private List<String> salaryCodeList;

    public List<String> getSalaryCodeList() {
        return salaryCodeList;
    }

    public void setSalaryCodeList(List<String> salaryCodeList) {
        this.salaryCodeList = salaryCodeList;
    }

}
