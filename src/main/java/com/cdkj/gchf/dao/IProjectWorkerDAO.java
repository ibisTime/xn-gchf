package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectWorker;

import java.util.List;
import java.util.Map;

public interface IProjectWorkerDAO extends IBaseDAO<ProjectWorker> {
    String NAMESPACE = IProjectWorkerDAO.class.getName().concat(".");

    void batchInsertProjectWorker(List<ProjectWorker> projectWorkerList);

    int update(ProjectWorker projectWorker);

    int updateTeamSysNoByLocal(ProjectWorker projectWorker);

    int updateUploadStatus(ProjectWorker projectWorker);

    int updateStatus(ProjectWorker projectWorker);

    int updateLastPayRoll(ProjectWorker projectWorker);

    int updateLastInOutRecord(ProjectWorker projectWorker);

    int updateLastAttendance(ProjectWorker projectWorker);

    int updateProjectWorkerDeleteStatus(ProjectWorker projectWorker);

    int updateProjectWorkerUploadStatus(ProjectWorker projectWorker);

    int updateProjectWorkerTeamName(ProjectWorker projectWorker);

    // 重新建档-更新项目人员信息
    int updateProjectWorkerWorkerInfo(ProjectWorker projectWorker);

    // 重新建档-第三步-更新项目人员手机号
    int updateProjectWorkerWorkerPhone(ProjectWorker projectWorker);

    /**
     * 查询项目端工种分布
     *
     * @param userId 用户id
     */
    List<Map> selectWorkerTypeSpread(String userId);


    /**
     * 查询项目端年龄分布
     *
     * @param userId 用户id
     */
    List<Map> selectWorkerAgeInterval(String userId);


    /**
     * 查询在职、今日上班、总发薪
     */
    List<Map> selectWorkerData(String userId);

}
