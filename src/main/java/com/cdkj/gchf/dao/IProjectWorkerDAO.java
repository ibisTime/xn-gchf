package com.cdkj.gchf.dao;

import java.util.List;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectWorker;

public interface IProjectWorkerDAO extends IBaseDAO<ProjectWorker> {
    String NAMESPACE = IProjectWorkerDAO.class.getName().concat(".");

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

    ProjectWorker selectProjectWorkerWorkerGuid(String guid);

    List<ProjectWorker> selectDistinctWorkerByProjectCode(String projectCode);

}
