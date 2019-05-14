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

/**
 * @author old3
 */
@Component
public interface IProjectWorkerEntryExitHistoryAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 新增进退场 
     */
    String addProjectWorkerEntryExitHistory(XN631730Req data);

    /**
     * 删除进退场
     */
    void dropProjectWorkerEntryExitHistory(List<String> codeList);

    /**
     * 修改进退场
     */
    void editProjectWorkerEntryExitHistory(XN631732Req data);

    /**
     * 导入进退场 
     */
    void importProjectWorkerEntryExitHistoryList(XN631733Req req);

    /**
     * <p>Title: uploadProjectWorkerEntryExitHistoryList</p>   
     * <p>Description: 上传人员进退场</p>   
     */
    void uploadProjectWorkerEntryExitHistoryList(String userId,
                                                 List<String> codeList);

    /**
     *根据code查 
     */
    Object queryProjectWorkerEntryExitHistory(String code);

    /**
     * 分页查 
     */
    Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryPage(
            int start, int limit, ProjectWorkerEntryExitHistory condition);

    /**
     * 条件查 
     */
    List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition);


    /****国家平台接口****/
    void uploadProjectWorkerEntryExitHistory(XN631914Req data);

    /**
     * 查询国家平台人员进退场
     * @param req
     * @return
     */
    Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistory(
            XN631915Req req);
}
