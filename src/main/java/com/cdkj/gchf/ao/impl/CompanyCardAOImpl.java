package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICompanyCardAO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631362Req;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;

@Service
public class CompanyCardAOImpl implements ICompanyCardAO {

    @Autowired
    ICompanyCardBO companyCardBO;

    @Autowired
    IProjectBO projectBO;

    @Autowired
    IUserBO userBO;

    @Override
    public void editCompanyCard(XN631362Req req) {
        companyCardBO.refreshCompanyCard(req.getCode(), req.getBankCode(),
            req.getBankName(), req.getAccountName(), req.getBankcardNumber(),
            req.getSubbranch(), req.getUpdater(), new Date(), req.getRemark());
    }

    @Override
    public Paginable<CompanyCard> queryCompanyCardPage(int start, int limit,
            CompanyCard condition) {
        Paginable<CompanyCard> page = new Page<CompanyCard>();
        List<CompanyCard> list = new ArrayList<CompanyCard>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }
        }
        // 补全信息
        page = companyCardBO.getPaginable(start, limit, condition);
        for (CompanyCard companyCard : page.getList()) {
            initCompanyCard(companyCard);
        }
        return page;
    }

    @Override
    public List<CompanyCard> queryCompanyCardList(CompanyCard condition) {
        List<CompanyCard> list = new ArrayList<CompanyCard>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }

        // 补全信息
        list = companyCardBO.queryCompanyCardList(condition);
        for (CompanyCard companyCard : list) {
            initCompanyCard(companyCard);
        }

        return list;
    }

    @Override
    public CompanyCard getCompanyCard(String code) {
        CompanyCard data = companyCardBO.getCompanyCard(code);
        initCompanyCard(data);
        return data;
    }

    private void initCompanyCard(CompanyCard companyCard) {
        companyCard.setUpdateName(getName(companyCard.getUpdater()));
        companyCard.setBankSubbranch(
            companyCard.getBankName().concat(companyCard.getSubbranch()));
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;
    }
}
