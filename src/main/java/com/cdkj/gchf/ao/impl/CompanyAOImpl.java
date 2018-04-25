package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICompanyAO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.dto.req.XN631020Req;
import com.cdkj.gchf.dto.req.XN631022Req;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String addCompany(XN631020Req req) {
        Company company = new Company();
        company.setName(req.getName());
        company.setCreateDatetime(new Date());
        return null;
    }

    @Override
    public void editCompany(XN631022Req req) {
        Company company = companyBO.getCompany(req.getCode());
        company.setName(req.getName());
        companyBO.editCompany(company);
    }

    @Override
    public void deleteCompany(String code) {
        Company data = companyBO.getCompany(code);
        companyBO.deleteCompany(data);
    }

    @Override
    public Company getCompany(String code) {
        return companyBO.getCompany(code);
    }

    @Override
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition) {
        return companyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyBO.queryCompany(condition);
    }
}
