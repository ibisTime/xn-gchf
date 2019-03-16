package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;

public interface IProjectWorkerEntryExitHistoryDAO
        extends IBaseDAO<ProjectWorkerEntryExitHistory> {
    String NAMESPACE = IProjectWorkerEntryExitHistoryDAO.class.getName()
        .concat(".");

    int update(ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory);

}
