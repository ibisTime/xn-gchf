package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;

public interface IWorkerEntryExitRecordBO
        extends IPaginableBO<WorkerEntryExitRecord> {

    public String saveWorkerEntryExitRecord(EquipmentInfo equipmentInfo,
            ProjectWorker projectWorker, String date, String direction,
            String image, String recMode, String attendType);

    public List<WorkerEntryExitRecord> queryWorkerEntryExitRecordList(
            WorkerEntryExitRecord condition);

    public WorkerEntryExitRecord getWorkerEntryExitRecord(String code);

}
