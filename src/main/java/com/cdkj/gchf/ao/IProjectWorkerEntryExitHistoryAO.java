package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;

@Component
public interface IProjectWorkerEntryExitHistoryAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProjectWorkerEntryExitHistory(XN631730Req data);

    public void dropProjectWorkerEntryExitHistory(String code);

    public void editProjectWorkerEntryExitHistory(XN631732Req data);

    public Object queryProjectWorkerEntryExitHistory(String code);

    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryPage(
            int start, int limit, ProjectWorkerEntryExitHistory condition);

    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition);

    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code);

    public void uploadProjectWorkerEntryExitHistoryList(String userId,
            List<String> codeList);

    /****国家平台接口****/
    public void uploadProjectWorkerEntryExitHistory(XN631914Req data);

    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistory(
            XN631915Req req);
}
