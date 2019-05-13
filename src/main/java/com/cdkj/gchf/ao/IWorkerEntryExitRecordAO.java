package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;

@Component
public interface IWorkerEntryExitRecordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<WorkerEntryExitRecord> queryWorkerEntryExitRecordPage(
            String userId, int start, int limit,
            WorkerEntryExitRecord condition);

    public List<WorkerEntryExitRecord> queryWorkerEntryExitRecordList(
            WorkerEntryExitRecord condition);

    public WorkerEntryExitRecord getWorkerEntryExitRecord(String code);

}
