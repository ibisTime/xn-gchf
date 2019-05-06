package com.cdkj.gchf.dao;

import java.util.List;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.WorkerInfo;

//dao层 
public interface IWorkerInfoDAO extends IBaseDAO<WorkerInfo> {
    String NAMESPACE = IWorkerInfoDAO.class.getName().concat(".");

    int updateWorkerInfo(WorkerInfo condition);

    int updateWorkerInfoAboutIdcard(WorkerInfo condition);

    int updateWorkerInfoAboutPhone(WorkerInfo condition);

    WorkerInfo selectBriefWorkerInfo(WorkerInfo condition);

    int updateWorkerInfoAttendance(WorkerInfo condition);

    int updateWorkerInfoAttendancePic(WorkerInfo condition);

    public List<WorkerInfo> selectBrifeList(WorkerInfo condition, int start,
            int count);
}
