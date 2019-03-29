package com.cdkj.gchf.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cdkj.gchf.domain.WorkerContract;

public class XN631673Req {
    @NotBlank
    private String userId;

    @NotEmpty
    private List<WorkerContract> workContractList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WorkerContract> getWorkContractList() {
        return workContractList;
    }

    public void setWorkContractList(List<WorkerContract> workContractList) {
        this.workContractList = workContractList;
    }

}
