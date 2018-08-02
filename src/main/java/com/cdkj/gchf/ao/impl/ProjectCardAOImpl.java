package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProjectCardAO;
import com.cdkj.gchf.bo.IProjectCardBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectCard;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631362Req;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;

@Service
public class ProjectCardAOImpl implements IProjectCardAO {

    @Autowired
    IProjectCardBO projectCardBO;

    @Autowired
    IProjectBO projectBO;

    @Autowired
    IUserBO userBO;

    @Override
    public void editProjectCard(XN631362Req req) {
        projectCardBO.refreshProjectCard(req.getCode(), req.getBankCode(),
            req.getBankName(), req.getAccountName(), req.getBankcardNumber(),
            req.getSubbranch(), req.getUpdater(), new Date(), req.getRemark());
    }

    @Override
    public Paginable<ProjectCard> queryProjectCardPage(int start, int limit,
            ProjectCard condition) {
        Paginable<ProjectCard> page = new Page<ProjectCard>();
        List<ProjectCard> list = new ArrayList<ProjectCard>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }
        }
        // 补全信息
        page = projectCardBO.getPaginable(start, limit, condition);
        for (ProjectCard companyCard : page.getList()) {
            initCompanyCard(companyCard);
        }
        return page;
    }

    @Override
    public List<ProjectCard> queryProjectCardList(ProjectCard condition) {
        List<ProjectCard> list = new ArrayList<ProjectCard>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }

        // 补全信息
        list = projectCardBO.queryProjectCardList(condition);
        for (ProjectCard companyCard : list) {
            initCompanyCard(companyCard);
        }

        return list;
    }

    @Override
    public ProjectCard getProjectCard(String code) {
        ProjectCard data = projectCardBO.getProjectCard(code);
        initCompanyCard(data);
        return data;
    }

    private void initCompanyCard(ProjectCard companyCard) {
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
