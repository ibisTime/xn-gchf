package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631690Req;
import com.cdkj.gchf.dto.req.XN631692Req;
import com.cdkj.gchf.dto.req.XN631693Req;
import com.cdkj.gchf.dto.req.XN631694Req;
import com.cdkj.gchf.dto.req.XN631695Req;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;

@Component
public interface IProjectWorkerAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProjectWorker(XN631690Req req);

    public void dropProjectWorker(List<String> codeList);

    public void editProjectWorker(XN631692Req req);

    public Paginable<ProjectWorker> queryProjectWorkerPage(int start, int limit,
            ProjectWorker condition);

    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition);

    public ProjectWorker getProjectWorker(String code);

    public void importProjectWorkers(XN631693Req req);

    void updatePlantformProjectWorker(XN631695Req req);

    // List<ProjectWorker> getProjectWorkerByProject(String projectCode);

    public void uploadProjectWorker(XN631694Req req);

    /****国家平台接口****/
    public void uploadProjectWorker(XN631911Req req);

    public void updateProjectWorker(XN631912Req req);

    public Paginable<ProjectWorker> queryProjectWorker(XN631913Req req);
}
