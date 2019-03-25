package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631620Req;
import com.cdkj.gchf.dto.req.XN631622Req;

@Component
public interface IProjectConfigAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProjectConfig(XN631620Req req);

    public int dropProjectConfig(String code);

    public void editProjectConfig(XN631622Req req);

    public void startStop(String code, String userId, String remark);

    public Paginable<ProjectConfig> queryProjectConfigPage(int start, int limit,
            ProjectConfig condition);

    public List<ProjectConfig> queryProjectConfigList(ProjectConfig condition);

    public ProjectConfig getProjectConfig(String code);

}
