package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerContractDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;

@Component
public class WorkerContractBOImpl extends PaginableBOImpl<WorkerContract>
        implements IWorkerContractBO {

    @Autowired
    private IWorkerContractDAO workerContractDAO;

    @Override
    public String saveWorkerContract(WorkerContract data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerContract.getCode());
            data.setCode(code);
            workerContractDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeWorkerContract(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            WorkerContract data = new WorkerContract();
            data.setCode(code);
            count = workerContractDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshWorkerContract(WorkerContract data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = workerContractDAO.update(data);
        }
        return count;
    }

    @Override
    public void doUpload(XN631916Req req, ProjectConfig projectConfig) {
        WorkerContract workerContract = new WorkerContract();
        BeanUtils.copyProperties(req, workerContract);

        String data = JSONObject.toJSON(workerContract).toString();

        GovConnecter.getGovData("WorkerContract.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public void doQuery(XN631917Req req, ProjectConfig projectConfig) {
        WorkerContract workerContract = new WorkerContract();
        BeanUtils.copyProperties(req, workerContract);

        String data = JSONObject.toJSON(workerContract).toString();

        GovConnecter.getGovData("WorkerContract.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public List<WorkerContract> queryWorkerContractList(
            WorkerContract condition) {
        return workerContractDAO.selectList(condition);
    }

    @Override
    public WorkerContract getWorkerContract(String code) {
        WorkerContract data = null;
        if (StringUtils.isNotBlank(code)) {
            WorkerContract condition = new WorkerContract();
            condition.setCode(code);
            data = workerContractDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "合同编号不存在");
            }
        }
        return data;
    }

}
