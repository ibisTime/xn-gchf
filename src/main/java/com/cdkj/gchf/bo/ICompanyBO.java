package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Company;

public interface ICompanyBO extends IPaginableBO<Company> {

    public String saveCompany(Company data);

    public Company getCompany(String code);

    public int editCompany(Company data);

    public void deleteCompany(Company data);

    public List<Company> queryCompany(Company condition);

}
