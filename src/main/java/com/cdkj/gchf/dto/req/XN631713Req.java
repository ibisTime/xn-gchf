package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cdkj.gchf.domain.WorkerAttendance;

public class XN631713Req {
    @NotBlank
    private String userId;

    @NotEmpty
    private List<WorkerAttendance> workerAttendanceList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WorkerAttendance> getWorkerAttendanceList() {
        return workerAttendanceList;
    }

    public void setWorkerAttendanceList(
            List<WorkerAttendance> workerAttendanceList) {
        this.workerAttendanceList = workerAttendanceList;
    }

}
