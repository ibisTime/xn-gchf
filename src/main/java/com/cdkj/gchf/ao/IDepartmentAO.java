package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.dto.req.XN631030Req;
import com.cdkj.gchf.dto.req.XN631032Req;

@Component
public interface IDepartmentAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addDepartment(XN631030Req req);

    public void dropDepartment(String code);

    public void editDepartment(XN631032Req req);

    public Paginable<Department> queryDepartmentPage(int start, int limit,
            Department condition);

    public List<Department> queryDepartmentList(Department condition);

    public Department getDepartment(String code);

}
