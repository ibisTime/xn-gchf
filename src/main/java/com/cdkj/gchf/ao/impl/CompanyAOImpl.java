package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICompanyAO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    ICompanyBO companyBO;

    @Autowired
    IProjectBO projectBO;

    @Autowired
    IDepartmentBO departmentBO;

    @Autowired
    IUserBO userBO;

    @Override
    public String addCompany(String name) {
        Company data = new Company();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Company.getCode());
        data.setCode(code);
        data.setName(name);
        data.setCreateDatetime(new Date());
        companyBO.saveCompany(data);
        return code;
    }

    @Override
    public void editCompany(String code, String name) {
        Company data = companyBO.getCompany(code);

        companyBO.refreshCompany(data, name);
    }

    @Override
    public void dropCompany(String code) {
        Company data = companyBO.getCompany(code);
        // 该公司下是否存在用户
        List<User> userList = userBO.getUserByCompany(data.getCode());
        if (CollectionUtils.isNotEmpty(userList)) {
            throw new BizException("xn00000", "该公司下下仍有人员，无法删除");
        }
        companyBO.removeCompany(data);
    }

    @Override
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition) {
        List<String> companyCodeList = new ArrayList<String>();
        if (EUserKind.Supervise.equals(condition.getKind())) {
            Project pCondition = new Project();
            pCondition.setProjectCodeList(condition.getProjectCodeList());
            List<Project> projectList = projectBO.queryProject(pCondition);
            for (Project project : projectList) {
                companyCodeList.add(project.getCompanyCode());
            }

            condition.setCompanyCodeList(companyCodeList);
        }
        return companyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        List<String> companyCodeList = new ArrayList<String>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            Project pCondition = new Project();
            pCondition.setProjectCodeList(condition.getProjectCodeList());
            List<Project> projectList = projectBO.queryProject(pCondition);
            for (Project project : projectList) {
                companyCodeList.add(project.getCompanyCode());
            }
            condition.setCompanyCodeList(companyCodeList);
        }
        return companyBO.queryCompanyList(condition);
    }

    @Override
    public Company getCompany(String code) {
        return companyBO.getCompany(code);
    }
}
