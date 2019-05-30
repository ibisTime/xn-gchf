package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;

import java.util.List;

public interface IProjectWorkerEntryExitHistoryDAO
        extends IBaseDAO<ProjectWorkerEntryExitHistory> {
    String NAMESPACE = IProjectWorkerEntryExitHistoryDAO.class.getName()
        .concat(".");
    void batchInsert(List<ProjectWorkerEntryExitHistory> projectWorkerEntryExitHistoryList);
    int update(ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory);

    int updateProjectWorkerEntryHistoryStatus(
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory);

    int updateProjectWorkerEntryHistoryDeleteStatus(
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory);

    int selectProjectWorkerEntryHistoryLeavingCount30Day(String userId);

    /**
     * 查询30天入职人数
     */
    int selectProjectWorkerEntryHistoryComingCount30Day(String userId);
}
