package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IDepartmentAO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.dto.req.XN631030Req;
import com.cdkj.gchf.dto.req.XN631032Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Service
public class DepartmentAOImpl implements IDepartmentAO {

    @Autowired
    private IDepartmentBO departmentBO;

    @Override
    public String addDepartment(XN631030Req req) {
        Department data = new Department();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Department.getCode());
        data.setCode(code);
        data.setCompanyCode(req.getCompanyCode());
        data.setName(req.getName());
        data.setLeader(req.getLeader());
        data.setLeadeMobile(req.getLeadeMobile());

        data.setParentCode(req.getParentCode());
        departmentBO.saveDepartment(data);
        return code;
    }

    @Override
    public void editDepartment(XN631032Req req) {
        Department data = departmentBO.getDepartment(req.getCode());
        data.setCompanyCode(req.getCompanyCode());
        data.setName(req.getName());
        data.setLeader(req.getLeader());
        data.setLeadeMobile(req.getLeadeMobile());
        data.setParentCode(req.getParentCode());
        departmentBO.refreshDepartment(data);
    }

    @Override
    public void dropDepartment(String code) {
        Department data = departmentBO.getDepartment(code);
        // 是否存在下级部门
        List<Department> list = departmentBO.isExsit(data.getCode());
        if (CollectionUtils.isNotEmpty(list)) {
            throw new BizException("xn00000", "该部门下还有其他部门，无法删除");
        }
        departmentBO.removeDepartment(data);
    }

    @Override
    public Paginable<Department> queryDepartmentPage(int start, int limit,
            Department condition) {
        return departmentBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Department> queryDepartmentList(Department condition) {
        return departmentBO.queryDepartmentList(condition);
    }

    @Override
    public Department getDepartment(String code) {
        Department data = departmentBO.getDepartment(code);
        if (StringUtils.isNotBlank(data.getParentCode())) {
            String parent = departmentBO.getParentName(data.getParentCode());
            data.setParentName(parent);
        }
        return data;
    }
}
