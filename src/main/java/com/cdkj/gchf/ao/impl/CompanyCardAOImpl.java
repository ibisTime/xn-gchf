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
        CompanyCard data = companyCardBO.getCompanyCard(req.getCode());
        data.setBankCode(req.getBankCode());
        data.setBankName(req.getBankName());
        data.setBankcardNumber(req.getBankcardNumber());
        data.setSubbranch(req.getSubbranch());

        data.setAccountName(req.getAccountName());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        companyCardBO.refreshCompanyCard(data);
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
            companyCard.setUpdateName(getName(companyCard.getUpdater()));
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
            companyCard.setUpdateName((getName(companyCard.getUpdater())));
        }

        return list;
    }

    @Override
    public CompanyCard getCompanyCard(String code) {
        CompanyCard data = companyCardBO.getCompanyCard(code);
        data.setUpdateName(getName(data.getUpdater()));
        return data;
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
