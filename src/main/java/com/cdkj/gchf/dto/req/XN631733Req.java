package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cdkj.gchf.gov.WorkerEntryExit;

public class XN631733Req {
    @NotBlank
    private String userId;

    @NotEmpty
    private List<WorkerEntryExit> workerEntryExitHistoryList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WorkerEntryExit> getWorkerEntryExitHistoryList() {
        return workerEntryExitHistoryList;
    }

    public void setWorkerEntryExitHistoryList(
            List<WorkerEntryExit> workerEntryExitHistoryList) {
        this.workerEntryExitHistoryList = workerEntryExitHistoryList;
    }

}
