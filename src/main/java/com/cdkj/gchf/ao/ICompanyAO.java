package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Company;

@Component
public interface ICompanyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCompany(String name);

    public void dropCompany(String code);

    public void editCompany(String code, String name);

    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

}
