package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;

public interface IProjectWorkerBO extends IPaginableBO<ProjectWorker> {

    public String saveProjectWorker(ProjectWorker data);

    public int removeProjectWorker(String code);

    public int refreshProjectWorker(ProjectWorker data);

    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition);

    public ProjectWorker getProjectWorker(String code);

    /****国家平台接口****/
    public void doUpload(XN631911Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631912Req req, ProjectConfig projectConfig);

    public void doQuery(XN631913Req req, ProjectConfig projectConfig);
}
