package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Company;

public interface ICompanyBO extends IPaginableBO<Company> {

    public void saveCompany(Company data);

    public void removeCompany(Company data, String name);

    public void refreshCompany(Company data);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

}
