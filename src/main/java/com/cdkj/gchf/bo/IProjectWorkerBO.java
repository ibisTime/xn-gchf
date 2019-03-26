package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631690Req;
import com.cdkj.gchf.dto.req.XN631692Req;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;

public interface IProjectWorkerBO extends IPaginableBO<ProjectWorker> {

    public String saveProjectWorker(XN631690Req req);

    public void removeProjectWorker(String code);

    public void refreshProjectWorker(XN631692Req req);

    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition);

    public ProjectWorker getProjectWorker(String code);

    /****国家平台接口****/
    public void doUpload(XN631911Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631912Req req, ProjectConfig projectConfig);

    public Paginable<ProjectWorker> doQuery(XN631913Req req,
            ProjectConfig projectConfig);
}
