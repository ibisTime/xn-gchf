package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICompanyCardAO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.dto.req.XN631362Req;

@Service
public class CompanyCardAOImpl implements ICompanyCardAO {

    @Autowired
    private ICompanyCardBO companyCardBO;

    @Override
    public void editCompanyCard(XN631362Req req) {
        CompanyCard data = companyCardBO.getCompanyCard(req.getCode());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setBankcardNumber(req.getBankcardNumber());
        data.setSubbranch(req.getSubbranch());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        companyCardBO.refreshCompanyCard(data);
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
