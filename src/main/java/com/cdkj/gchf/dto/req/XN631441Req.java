package com.cdkj.gchf.dto.req;

import java.util.List;

import com.cdkj.gchf.domain.Salary;

/**
 *  修改工资条
 * @author: nyc 
 * @since: 2018年4月29日 下午8:37:32 
 * @history:
 */
public class XN631441Req {

    // （必填）编号
    private List<Salary> list;

    public List<Salary> getList() {
        return list;
    }

    public void setList(List<Salary> list) {
        this.list = list;
    }

}
