package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICompanyCardAO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CompanyCard;

@Service
public class CompanyCardAOImpl implements ICompanyCardAO {

    @Autowired
    private ICompanyCardBO companyCardBO;

    @Override
    public String addCompanyCard(CompanyCard data) {
        return companyCardBO.saveCompanyCard(data);
    }

    @Override
    public void editCompanyCard(CompanyCard data) {
    }

    @Override
    public void dropCompanyCard(String code) {
    }

    @Override
    public Paginable<CompanyCard> queryCompanyCardPage(int start, int limit,
            CompanyCard condition) {
        return companyCardBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CompanyCard> queryCompanyCardList(CompanyCard condition) {
        return companyCardBO.queryCompanyCardList(condition);
    }

    @Override
    public CompanyCard getCompanyCard(String code) {
        return companyCardBO.getCompanyCard(code);
    }
}
