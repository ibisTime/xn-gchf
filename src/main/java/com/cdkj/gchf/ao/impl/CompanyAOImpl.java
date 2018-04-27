package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICompanyAO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.enums.EGeneratePrefix;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private ICompanyBO companyBO;

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
        companyBO.removeCompany(data);
    }

    @Override
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition) {
        return companyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyBO.queryCompanyList(condition);
    }

    @Override
    public Company getCompany(String code) {
        return companyBO.getCompany(code);
    }
}
