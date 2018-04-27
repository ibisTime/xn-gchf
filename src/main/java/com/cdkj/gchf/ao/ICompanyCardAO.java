package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CompanyCard;

@Component
public interface ICompanyCardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCompanyCard(CompanyCard data);

    public void dropCompanyCard(String code);

    public void editCompanyCard(CompanyCard data);

    public Paginable<CompanyCard> queryCompanyCardPage(int start, int limit,
            CompanyCard condition);

    public List<CompanyCard> queryCompanyCardList(CompanyCard condition);

    public CompanyCard getCompanyCard(String code);

}
