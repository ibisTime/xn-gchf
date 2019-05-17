package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectConfigDAO;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631620Req;
import com.cdkj.gchf.dto.req.XN631622Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class ProjectConfigBOImpl extends PaginableBOImpl<ProjectConfig>
        implements IProjectConfigBO {

    @Autowired
    private IProjectConfigDAO projectConfigDAO;

    @Override
    public String saveProjectConfig(Project project, XN631620Req req) {
        ProjectConfig projectConfig = new ProjectConfig();
        projectConfig.setSecret(projectConfig.getSecret().trim());
        BeanUtils.copyProperties(req, projectConfig);

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectConfig.getCode());
        projectConfig.setCode(code);
        projectConfig.setLocalProjectCode(project.getCode());
        projectConfig.setProjectName(project.getName());

        projectConfigDAO.insert(projectConfig);

        return code;
    }

    @Override
    public String saveLocalProjectConfig(String localProjectCode) {
        String code = null;
        ProjectConfig localProjectConfig = new ProjectConfig();
        code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectConfig.getCode());
        localProjectConfig.setCode(code);
        localProjectConfig.setLocalProjectCode(localProjectCode);
        projectConfigDAO.insert(localProjectConfig);
        return code;
    }

    @Override
    public String saveProjectConfig(Project project, XN631622Req req) {
        ProjectConfig localProjectConfig = new ProjectConfig();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectConfig.getCode());

        localProjectConfig.setCode(code);
        localProjectConfig.setLocalProjectCode(req.getLocalProjectCode());
        localProjectConfig.setProjectCode(req.getProjectCode());
        localProjectConfig.setPassword(req.getPassword());
        localProjectConfig.setProjectName(project.getName());
        localProjectConfig.setSecret(req.getSecret());

        projectConfigDAO.insert(localProjectConfig);
        return code;
    }

    @Override
    public int removeProjectConfig(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ProjectConfig data = new ProjectConfig();
            data.setCode(code);
            count = projectConfigDAO.delete(data);
        }
        return count;
    }

    @Override
    public void refreshProjectConfig(String code, XN631622Req req) {
        ProjectConfig projectConfig = new ProjectConfig();
        projectConfig.setCode(code);
        BeanUtils.copyProperties(req, projectConfig);

        projectConfigDAO.update(projectConfig);
    }

    @Override
    public void refreshStatus(String code, String status) {
        ProjectConfig projectConfig = new ProjectConfig();

        projectConfig.setCode(code);
        projectConfig.setStatus(status);

        projectConfigDAO.updateStatus(projectConfig);
    }

    @Override
    public List<ProjectConfig> queryProjectConfigList(ProjectConfig condition) {
        return projectConfigDAO.selectList(condition);
    }

    @Override
    public ProjectConfig getProjectConfig(String code) {
        ProjectConfig data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectConfig condition = new ProjectConfig();
            condition.setCode(code);
            data = projectConfigDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "项目配置编号不存在");
            }
        }
        return data;
    }

    @Override
    public ProjectConfig getDefaultProjectConfig() {
        return projectConfigDAO.selectDefaultProjectConfig(new ProjectConfig());
    }

    @Override
    public ProjectConfig getProjectConfigByLocal(String localProjectCode) {
        ProjectConfig data = new ProjectConfig();

        data.setLocalProjectCode(localProjectCode);

        return projectConfigDAO.select(data);
    }

    @Override
    public ProjectConfig getProjectConfigByProject(String projectCode) {
        ProjectConfig data = new ProjectConfig();

        data.setProjectCode(projectCode);

        return projectConfigDAO.select(data);
    }

    @Override
    public ProjectConfig checkProjectConfigBySecret(String projectCode,
                                                    String secret) {
        ProjectConfig projectConfig = new ProjectConfig();
        projectConfig.setSecret(secret);
        projectConfig.setProjectCode(projectCode);
        List<ProjectConfig> selectList = projectConfigDAO
                .selectList(projectConfig);
        if (CollectionUtils.isNotEmpty(selectList)) {
            throw new BizException("XN631752", "项目配置已存在");
        }
        return null;
    }

}
