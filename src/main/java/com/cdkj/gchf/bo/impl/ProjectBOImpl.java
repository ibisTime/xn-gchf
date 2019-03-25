package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class ProjectBOImpl extends PaginableBOImpl<Project>
        implements IProjectBO {

    @Autowired
    private IProjectDAO projectDAO;

    @Override
    public String saveProject(XN631600Req req, CorpBasicinfo contractorCorpInfo,
            CorpBasicinfo buildCorpInfo) {
        Project project = new Project();
        BeanUtils.copyProperties(req, project);

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Project.getCode());
        project.setCode(code);
        project.setContractorCorpName(contractorCorpInfo.getCorpName());
        project.setBuildCorpName(buildCorpInfo.getCorpName());

        projectDAO.insert(project);

        return code;
    }

    @Override
    public void refreshProject(XN631602Req req,
            CorpBasicinfo contractorCorpInfo, CorpBasicinfo buildCorpInfo) {

        Project project = new Project();
        BeanUtils.copyProperties(req, project);
        project.setContractorCorpName(contractorCorpInfo.getCorpName());
        project.setBuildCorpName(buildCorpInfo.getCorpName());

        projectDAO.update(project);
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        return projectDAO.selectList(condition);
    }

    @Override
    public Project getProject(String code) {
        Project data = null;
        if (StringUtils.isNotBlank(code)) {
            Project condition = new Project();
            condition.setCode(code);
            data = projectDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "项目编号不存在");
            }
        }
        return data;
    }
}
