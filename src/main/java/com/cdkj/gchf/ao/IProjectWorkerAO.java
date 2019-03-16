package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;

@Component
public interface IProjectWorkerAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProjectWorker(ProjectWorker data);

    public int dropProjectWorker(String code);

    public int editProjectWorker(ProjectWorker data);

    public Paginable<ProjectWorker> queryProjectWorkerPage(int start, int limit,
            ProjectWorker condition);

    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition);

    public ProjectWorker getProjectWorker(String code);

    /****国家平台接口****/
    public void uploadProjectWorker(XN631911Req req);

    public void updateProjectWorker(XN631912Req req);

    public Paginable<ProjectWorker> queryProjectWorker(XN631913Req req);
}
