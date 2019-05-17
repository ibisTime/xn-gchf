package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectWorker;

import java.util.List;

public interface IProjectWorkerDAO extends IBaseDAO<ProjectWorker> {
    String NAMESPACE = IProjectWorkerDAO.class.getName().concat(".");

    void batchInsertProjectWorker(List<ProjectWorker> projectWorkerList);

    int update(ProjectWorker projectWorker);

    int updateTeamSysNoByLocal(ProjectWorker projectWorker);

    int updateStatus(ProjectWorker projectWorker);

    int updateProjectWorkerDeleteStatus(ProjectWorker projectWorker);

    int updateProjectWorkerUploadStatus(ProjectWorker projectWorker);

    int updateProjectWorkerTeamName(ProjectWorker projectWorker);

    // 重新建档-更新项目人员信息
    int updateProjectWorkerWorkerInfo(ProjectWorker projectWorker);

    // 重新建档-第三步-更新项目人员手机号
    int updateProjectWorkerWorkerPhone(ProjectWorker projectWorker);

}
