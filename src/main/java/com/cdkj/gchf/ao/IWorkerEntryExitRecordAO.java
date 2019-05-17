package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;

@Component
public interface IWorkerEntryExitRecordAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 分页查询人员考勤记录数
     *
     * @param userId    用户id
     * @param start     开始页数
     * @param limit     每页记录数
     * @param condition 条件
     * @return
     */
    Paginable<WorkerEntryExitRecord> queryWorkerEntryExitRecordPage(
            String userId, int start, int limit,
            WorkerEntryExitRecord condition);

    /**
     * 列表查人员进出记录
     *
     * @param condition 条件
     * @return
     */
    List<WorkerEntryExitRecord> queryWorkerEntryExitRecordList(
            WorkerEntryExitRecord condition);

    /**
     * 根据code查询人员记录数
     *
     * @param code 主键code
     * @return
     */
    WorkerEntryExitRecord getWorkerEntryExitRecord(String code);

}
