package com.cdkj.gchf.ao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class WorkerContractAOImpl implements IWorkerContractAO {

    @Autowired
    private IWorkerContractBO workerContractBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Override
    public String addWorkerContract(XN631670Req req) {
        return workerContractBO.saveWorkerContract(req);
    }

    @Override
    public void editWorkerContract(XN631672Req data) {
        workerContractBO.refreshWorkerContract(data);
    }

    @Override
    public void dropWorkerContract(String code) {
        workerContractBO.removeWorkerContract(code);
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

    @Transactional
    @Override
    public void uploadWorkContractList(String userId, List<String> codeList) {
        User briefUser = userBO.getBriefUser(userId);

        for (String code : codeList) {
            WorkerContract workerContract = workerContractBO
                .getWorkerContract(code);
            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(workerContract.getProjectCode());
            if (projectConfig == null) {
                throw new BizException("XN631674", "该项目未配置，无法查询");
            }
            workerContract.setProjectCode(projectConfig.getProjectCode());
            // 封装请求json
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("projectCode",
                projectConfig.getProjectCode());
            JsonObject childJson = new JsonObject();
            childJson.addProperty("corpCode", workerContract.getCorpCode());
            childJson.addProperty("corpName", workerContract.getCorpName());
            childJson.addProperty("idCardType", workerContract.getIdcardType());

            String encrypt = AesUtils.encrypt(workerContract.getIdcardNumber(),
                projectConfig.getSecret());
            childJson.addProperty("idCardNumber", encrypt);
            childJson.addProperty("contractPeriodType",
                workerContract.getContractPeriodType());
            childJson.addProperty("startDate",
                new SimpleDateFormat("yyyy-MM-dd")
                    .format(workerContract.getStartDate()));
            childJson.addProperty("endDate", new SimpleDateFormat("yyyy-MM-dd")
                .format(workerContract.getEndDate()));
            childJson.addProperty("unit", workerContract.getUnit());
            childJson.addProperty("unitPrice", workerContract.getUnitPrice());
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(childJson);
            jsonObject.add("contractList", jsonArray);
            System.out.println(jsonObject.toString());

            // 上传到国家平台
            String resString = GovConnecter.getGovData("WorkerContract.Add",
                jsonObject.toString(), projectConfig.getProjectCode(),
                projectConfig.getSecret());
            // 增加操作日志
            String log = operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkContract.getCode(), code,
                EOperateLogOperate.UploadWorkContract.getValue(), briefUser,
                null);
            // 消息队列更新状态
            AsyncQueueHolder.addSerial(resString, projectConfig,
                "workerContractBO", code,
                EUploadStatus.UPLOAD_UNEDITABLE.getCode(), log);
        }
    }

}
