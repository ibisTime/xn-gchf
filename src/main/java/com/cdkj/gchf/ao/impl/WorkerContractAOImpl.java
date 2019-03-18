package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.cdkj.gchf.exception.BizException;

@Service
public class WorkerContractAOImpl implements IWorkerContractAO {

    @Autowired
    private IWorkerContractBO workerContractBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    public String addWorkerContract(WorkerContract data) {
        return workerContractBO.saveWorkerContract(data);
    }

    @Override
    public int editWorkerContract(WorkerContract data) {
        return workerContractBO.refreshWorkerContract(data);
    }

    @Override
    public int dropWorkerContract(String code) {
        return workerContractBO.removeWorkerContract(code);
    }

    @Override
    public void uploadWorkerContract(XN631916Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631916", "该项目未配置，无法上传");
        }

        workerContractBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<WorkerContract> queryWorkerContract(XN631917Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631917", "该项目未配置，无法查询");
        }

        return workerContractBO.doQuery(req, projectConfig);

    }

    @Override
    public Paginable<WorkerContract> queryWorkerContractPage(int start,
            int limit, WorkerContract condition) {
        return workerContractBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WorkerContract> queryWorkerContractList(
            WorkerContract condition) {
        return workerContractBO.queryWorkerContractList(condition);
    }

    @Override
    public WorkerContract getWorkerContract(String code) {
        return workerContractBO.getWorkerContract(code);
    }

}
