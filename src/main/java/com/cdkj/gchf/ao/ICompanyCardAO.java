package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.dto.req.XN631362Req;

@Component
public interface ICompanyCardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void editCompanyCard(XN631362Req req);

    public Paginable<CompanyCard> queryCompanyCardPage(int start, int limit,
            CompanyCard condition);

    public List<CompanyCard> queryCompanyCardList(CompanyCard condition);

    public CompanyCard getCompanyCard(String code);

}
