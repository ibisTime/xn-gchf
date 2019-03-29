package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cdkj.gchf.domain.ProjectWorker;

public class XN631693Req {
    @NotBlank
    private String userId;

    @NotEmpty
    private List<ProjectWorker> workerList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProjectWorker> getWorkerList() {
        return workerList;
    }

    public void setWorkerList(List<ProjectWorker> workerList) {
        this.workerList = workerList;
    }

}
