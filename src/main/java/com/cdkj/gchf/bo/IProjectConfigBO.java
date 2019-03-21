package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631620Req;
import com.cdkj.gchf.dto.req.XN631622Req;

public interface IProjectConfigBO extends IPaginableBO<ProjectConfig> {

    public String saveProjectConfig(XN631620Req req);

    public int removeProjectConfig(String code);

    public void refreshProjectConfig(XN631622Req req);

    public List<ProjectConfig> queryProjectConfigList(ProjectConfig condition);

    public ProjectConfig getProjectConfig(String code);

    public ProjectConfig getDefaultProjectConfig();

    public ProjectConfig getProjectConfigByLocal(String localProjectCode);

    public ProjectConfig getProjectConfigByProject(String projectCode);

}
