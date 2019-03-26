package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerContractDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631916ReqContract;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;

@Component
public class WorkerContractBOImpl extends PaginableBOImpl<WorkerContract>
        implements IWorkerContractBO {

    @Autowired
    private IWorkerContractDAO workerContractDAO;

    @Override
    public String saveWorkerContract(XN631670Req req) {
        String code = null;
        WorkerContract workerContract = new WorkerContract();
        BeanUtils.copyProperties(req, workerContract);
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerContract.getCode());
        workerContract.setCode(code);
        workerContractDAO.insert(workerContract);
        return code;
    }

    @Override
    public void removeWorkerContract(String code) {
        WorkerContract data = new WorkerContract();
        data.setCode(code);
        workerContractDAO.delete(data);
    }

    @Override
    public void refreshWorkerContract(XN631672Req data) {
        WorkerContract workerContract = new WorkerContract();
        BeanUtils.copyProperties(data, workerContract);
        workerContractDAO.update(workerContract);
    }

    @Override
    public void doUpload(XN631916Req req, ProjectConfig projectConfig) {

        List<XN631916ReqContract> contractList = req.getContractList();
        for (XN631916ReqContract contract : contractList) {
            contract.setIdCardNumber(AesUtils.encrypt(
                contract.getIdCardNumber(), projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("WorkerContract.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public Paginable<WorkerContract> doQuery(XN631917Req req,
            ProjectConfig projectConfig) {
        WorkerContract workerContract = new WorkerContract();
        BeanUtils.copyProperties(req, workerContract);

        String data = JSONObject.toJSON(workerContract).toString();

        String queryString = GovConnecter.getGovData("WorkerContract.Query",
            data, projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<WorkerContract> page = GovUtil.parseGovPage(
            req.getPageIndex(), req.getPageSize(), queryString, replaceMap,
            WorkerContract.class);

        return page;
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
