package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631733Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;

@Component
public interface IProjectWorkerEntryExitHistoryAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 新增进退场 
     */
    public String addProjectWorkerEntryExitHistory(XN631730Req data);

    /**
     * 删除进退场
     */
    public void dropProjectWorkerEntryExitHistory(List<String> codeList);

    /**
     * 修改进退场
     */
    public void editProjectWorkerEntryExitHistory(XN631732Req data);

    /**
     * 导入进退场 
     */
    public void importProjectWorkerEntryExitHistoryList(XN631733Req req);

    /**
     * <p>Title: uploadProjectWorkerEntryExitHistoryList</p>   
     * <p>Description: 上传人员进退场</p>   
     */
    public void uploadProjectWorkerEntryExitHistoryList(String userId,
            List<String> codeList);

    /**
     *根据code查 
     */
    public Object queryProjectWorkerEntryExitHistory(String code);

    /**
     * 分页查 
     */
    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryPage(
            int start, int limit, ProjectWorkerEntryExitHistory condition);

    /**
     * 条件查 
     */
    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition);

    /**
     * 根据code 查 
     */
    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code);

    /****国家平台接口****/
    public void uploadProjectWorkerEntryExitHistory(XN631914Req data);

    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistory(
            XN631915Req req);
}
