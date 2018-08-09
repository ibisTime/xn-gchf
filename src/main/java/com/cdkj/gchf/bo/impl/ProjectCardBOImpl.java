package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectCardBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectCardDAO;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectCard;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;

@Component
public class ProjectCardBOImpl extends PaginableBOImpl<ProjectCard>
        implements IProjectCardBO {
    @Autowired
    private IProjectCardDAO projectCardDAO;

    @Autowired
    private IProjectBO projectBO;

    public void saveProjectCard(String projectCode) {
        ProjectCard data = new ProjectCard();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CompanyBank.getCode());
        data.setCode(code);
        data.setProjectCode(projectCode);
        data.setCreateDatetime(new Date());
        data.setStatus(EBankCardStatus.Normal.getCode());
        data.setUpdateDatetime(new Date());

        Project project = projectBO.getProject(projectCode);
        if (null != project) {
            data.setProjectName(project.getName());
        }
        projectCardDAO.insert(data);
    }

    @Override
    public void refreshProjectCard(String code, String bankCode,
            String bankName, String accountName, String bankCardNumber,
            String subbranch, String updater, Date updateDatetime,
            String remark) {

        ProjectCard data = getProjectCard(code);
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setBankcardNumber(bankCardNumber);
        data.setSubbranch(subbranch);

        data.setAccountName(accountName);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);

        projectCardDAO.update(data);
    }

    @Override
    public List<ProjectCard> queryProjectCardList(ProjectCard condition) {
        return projectCardDAO.selectList(condition);
    }

    @Override
    public ProjectCard getProjectCard(String code) {
        ProjectCard data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectCard condition = new ProjectCard();
            condition.setCode(code);
            data = projectCardDAO.select(condition);
            if (data != null) {
                if (null != data.getBankName()) {
                    data.setBankSubbranch(
                        data.getBankName().concat(data.getSubbranch()));
                }
            }
        }
        return data;
    }

    @Override
    public ProjectCard getProjectCardByProject(String projectCode) {
        ProjectCard data = null;
        if (StringUtils.isNotBlank(projectCode)) {
            ProjectCard condition = new ProjectCard();
            condition.setProjectCode(projectCode);
            data = projectCardDAO.select(condition);
            if (data != null) {
                if (null != data.getBankName()) {
                    data.setBankSubbranch(
                        data.getBankName().concat(data.getSubbranch()));
                }
            }
        }
        return data;
    }
}
