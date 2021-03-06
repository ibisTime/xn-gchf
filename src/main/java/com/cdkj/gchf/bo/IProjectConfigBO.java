package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631620Req;
import com.cdkj.gchf.dto.req.XN631622Req;

public interface IProjectConfigBO extends IPaginableBO<ProjectConfig> {

    public String saveProjectConfig(Project project, XN631620Req req);

    public String saveLocalProjectConfig(String localProjectCode);

    public String saveProjectConfig(Project project, XN631622Req req);

    public int removeProjectConfig(String code);

    public void refreshProjectConfig(String code, XN631622Req req);

    public void refreshStatus(String code, String status);

    public List<ProjectConfig> queryProjectConfigList(ProjectConfig condition);

    public ProjectConfig getProjectConfig(String code);

    public ProjectConfig getDefaultProjectConfig();

    public ProjectConfig getProjectConfigByLocal(String localProjectCode);

    public ProjectConfig getProjectConfigByProject(String projectCode);

    // 检查项目密钥是否存在
    ProjectConfig checkProjectConfigBySecret(String projectCode, String secret);

}
