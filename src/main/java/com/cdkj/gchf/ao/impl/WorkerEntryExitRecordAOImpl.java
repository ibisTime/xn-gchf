package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerEntryExitRecordAO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerEntryExitRecordBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;

@Service
public class WorkerEntryExitRecordAOImpl implements IWorkerEntryExitRecordAO {

    @Autowired
    private IWorkerEntryExitRecordBO workerEntryExitRecordBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public Paginable<WorkerEntryExitRecord> queryWorkerEntryExitRecordPage(
            String userId, int start, int limit,
            WorkerEntryExitRecord condition) {
        if (StringUtils.isNotBlank(userId)) {
            User briefUser = userBO.getBriefUser(userId);
            condition.setProjectCode(briefUser.getOrganizationCode());
        }
        return workerEntryExitRecordBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WorkerEntryExitRecord> queryWorkerEntryExitRecordList(
            WorkerEntryExitRecord condition) {
        return workerEntryExitRecordBO
            .queryWorkerEntryExitRecordList(condition);
    }

    @Override
    public WorkerEntryExitRecord getWorkerEntryExitRecord(String code) {
        return workerEntryExitRecordBO.getWorkerEntryExitRecord(code);
    }
}
