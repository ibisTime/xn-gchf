package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectWorker;

public interface IProjectWorkerDAO extends IBaseDAO<ProjectWorker> {
    String NAMESPACE = IProjectWorkerDAO.class.getName().concat(".");

    int update(ProjectWorker projectWorker);

    int updateTeamSysNoByLocal(ProjectWorker projectWorker);

    int updateStatus(ProjectWorker projectWorker);

    int updateProjectWorkerDeleteStatus(ProjectWorker projectWorker);
}
