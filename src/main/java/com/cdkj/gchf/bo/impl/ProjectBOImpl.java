package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.ESecretStatus;

@Component
public class ProjectBOImpl extends PaginableBOImpl<Project>
        implements IProjectBO {

    @Autowired
    private IProjectDAO projectDAO;

    @Autowired
    private IUserBO userBO;

    @Override
    public Project saveProject(XN631600Req req,
            CorpBasicinfo contractorCorpInfo) {
        Project project = new Project();
        BeanUtils.copyProperties(req, project);

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Project.getCode());
        project.setCode(code);
        if (contractorCorpInfo != null
                && StringUtils.isNotBlank(contractorCorpInfo.getCorpName())) {
            project.setContractorCorpName(contractorCorpInfo.getCorpName());
        }
        if (StringUtils.isNotBlank(req.getInvest())) {
            project.setInvest(
                new BigDecimal(Integer.parseInt(req.getInvest()) * 10000));
        }
        if (StringUtils.isNotBlank(req.getBuildingArea())) {
            project.setBuildingArea(new BigDecimal(req.getBuildingArea()));
        }
        if (StringUtils.isNotBlank(req.getBuildingLength())) {
            project.setBuildingLength(new BigDecimal(req.getBuildingLength()));
        }
        if (StringUtils.isNotBlank(req.getStartDate())) {
            project.setStartDate(DateUtil.strToDate(req.getStartDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(req.getCompleteDate())) {
            project.setCompleteDate(DateUtil.strToDate(req.getCompleteDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(req.getLat())) {
            project.setLat(new BigDecimal(req.getLat()));
        }
        if (StringUtils.isNotBlank(req.getLng())) {
            project.setLng(new BigDecimal(req.getLng()));
        }
        project.setBuildCorpName(req.getBuildCorpName());
        project.setSecretStatus(ESecretStatus.NO.getCode());
        projectDAO.insert(project);

        return project;
    }

    @Override
    public void refreshProject(XN631602Req req,
            CorpBasicinfo contractorCorpInfo) {

        Project project = new Project();
        BeanUtils.copyProperties(req, project);
        project.setContractorCorpName(contractorCorpInfo.getCorpName());
        if (StringUtils.isNotBlank(req.getStartDate())) {
            project.setStartDate(DateUtil.strToDate(req.getStartDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(req.getCompleteDate())) {
            project.setCompleteDate(DateUtil.strToDate(req.getCompleteDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        project.setPrjStatus(req.getPrjStatus());

        if (StringUtils.isNotBlank(req.getInvest())) {
            project.setInvest(new BigDecimal(req.getInvest()));
        }
        if (StringUtils.isNotBlank(req.getBuildingArea())) {
            project.setBuildingArea(new BigDecimal(req.getBuildingArea()));
        }
        if (StringUtils.isNotBlank(req.getBuildingLength())) {
            project.setBuildingLength(new BigDecimal(req.getBuildingLength()));
        }
        if (StringUtils.isNotBlank(req.getLat())) {
            project.setLat(new BigDecimal(req.getLat()));
        }
        if (StringUtils.isNotBlank(req.getLng())) {
            project.setLng(new BigDecimal(req.getLng()));
        }

        project.setBuildCorpName(req.getBuildCorpName());

        projectDAO.update(project);
    }

    @Override
    public void refreshSecretStatus(String code, String secretStatus) {
        Project project = new Project();

        project.setCode(code);
        project.setSecretStatus(secretStatus);

        projectDAO.updateSecretStatus(project);
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
        }
        return data;
    }

    @Override
    public Project getProjectByFullName(String fullName) {
        Project project = new Project();
        project.setFullName(fullName);

        return projectDAO.select(project);
    }

    @Override
    public List<Map> getProjectInfoList_led(String userId) {
        List<Map> maps = projectDAO.queryProjectInfo_led(userId);
        return maps;
    }

    @Override
    public void refreshContractorCorp(String code, String contractorCorpCode,
            String contractorCorpName) {
        Project project = new Project();

        project.setCode(code);
        project.setContractorCorpCode(contractorCorpCode);
        project.setContractorCorpName(contractorCorpName);

        projectDAO.updateContractorCorp(project);
    }

}
