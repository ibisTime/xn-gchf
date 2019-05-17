package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;

/**
 * @author silver
 */
public interface IWorkerEntryExitRecordBO
        extends IPaginableBO<WorkerEntryExitRecord> {

    String saveWorkerEntryExitRecord(EquipmentInfo equipmentInfo,
                                     ProjectWorker projectWorker, String date, String direction,
                                     String image, String recMode, String attendType);

    List<WorkerEntryExitRecord> queryWorkerEntryExitRecordList(
            WorkerEntryExitRecord condition);

    WorkerEntryExitRecord getWorkerEntryExitRecord(String code);

    WorkerEntryExitRecord getMorningEntryExitRecord(String projectCode,
                                                    String workerCode);

    WorkerEntryExitRecord getAfternoonEntryExitRecord(String projectCode,
                                                      String workerCode);

}
