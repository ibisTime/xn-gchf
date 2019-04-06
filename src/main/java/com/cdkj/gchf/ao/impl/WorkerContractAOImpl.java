package com.cdkj.gchf.ao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.req.XN631673Req;
import com.cdkj.gchf.dto.req.XN631673ReqData;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.cdkj.gchf.enums.EContractPeriodType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUnitType;
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
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Override
    public String addWorkerContract(XN631670Req req) {

        if (StringUtils.isNotBlank(req.getContractPeriodType())) {
            EContractPeriodType.checkExists(req.getContractPeriodType());
        }
        if (StringUtils.isNotBlank(req.getUnit())) {
            EUnitType.checkExists(req.getUnit());
        }
        ProjectConfig projectConfigByLocal = projectConfigBO
            .getProjectConfigByLocal(req.getProjectCode());
        if (projectConfigByLocal == null) {
            throw new BizException("XN631670", "项目未部署");
        }
        ProjectCorpInfo queryProjectCorpInfoList = projectCorpInfoBO
            .getProjectCorpInfoByCorpCode(req.getCorpCode());
        if (queryProjectCorpInfoList == null) {
            throw new BizException("XN631670", "企业信用代码无效");
        }
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(req.getWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631670", "员工信息不存在");
        }
        return workerContractBO.saveWorkerContract(req);
    }

    @Override
    public void editWorkerContract(XN631672Req req) {
        WorkerContract workerContract = workerContractBO
            .getWorkerContract(req.getCode());
        if (workerContract == null) {
            throw new BizException("XN631672", "劳动合同不存在");
        }
        if (workerContract.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631672", "劳动合同已上传,无法编辑");
        }
        if (StringUtils.isNotBlank(req.getUnit())) {
            EUnitType.checkExists(req.getUnit());
        }
        workerContractBO.refreshWorkerContract(req);

    }

    @Override
    public void dropWorkerContract(String userId, String code) {

        WorkerContract workerContract = workerContractBO
            .getWorkerContract(code);
        if (workerContract == null) {
            throw new BizException("XN631671", "项目合同不存在");
        }
        if (workerContract.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631671", "项目已经上传 无法删除");
        }
        workerContractBO.removeWorkerContract(userId, code);

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

        Paginable<WorkerContract> page = workerContractBO.doQuery(req,
            projectConfig);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (WorkerContract workerContract : page.getList()) {
                String idcardNumber = AesUtils.decrypt(
                    workerContract.getIdcardNumber(),
                    projectConfig.getSecret());

                XN631913Req workerReq = new XN631913Req(req.getProjectCode(),
                    workerContract.getCorpCode(), idcardNumber);
                workerReq.setPageIndex(0);
                workerReq.setPageSize(1);
                Paginable<ProjectWorker> projectWorker = projectWorkerBO
                    .doQuery(workerReq, projectConfig);
                if (null != projectWorker && CollectionUtils
                    .isNotEmpty(projectWorker.getList())) {
                    workerContract.setWorkerName(
                        projectWorker.getList().get(0).getWorkerName());
                }
                String idcardNumberReal = AesUtils.decrypt(
                    workerContract.getIdcardNumber(),
                    projectConfig.getSecret());

                workerContract.setIdcardNumber(idcardNumberReal);
            }
        }

        return page;

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
                throw new BizException("XN631674", "该项目未配置，无法上传");
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

    /**
     * <p>Description:导入劳动合同 </p>   
     */
    @Transactional
    @Override
    public void importWorkContractList(XN631673Req req) {
        ProjectConfig configByLocal = projectConfigBO
            .getProjectConfigByLocal(req.getProjectCode());
        if (configByLocal == null) {
            throw new BizException("XN631673", "项目不存在");
        }
        List<XN631673ReqData> workContractList = req.getDataList();
        for (XN631673ReqData xn631673ReqData : workContractList) {

            // 校验数据字典数据
            EContractPeriodType
                .checkExists(xn631673ReqData.getContractPeriodType());

            WorkerContract workerContract = new WorkerContract();
            // 核实参见单位信息
            ProjectCorpInfo corpInfoByCorpCode = projectCorpInfoBO
                .getProjectCorpInfoByCorpCode(xn631673ReqData.getCorpCode());
            if (corpInfoByCorpCode == null) {
                throw new BizException("XN631673",
                    "参建单位信息不存在" + xn631673ReqData.getCorpCode());
            }
            // 取得个人信息
            WorkerInfo workerInfoByIdCardNumber = workerInfoBO
                .getWorkerInfoByIdCardNumber(xn631673ReqData.getIdCardNumber());
            if (workerInfoByIdCardNumber == null) {
                throw new BizException("XN631673",
                    "人员信息不存在" + xn631673ReqData.getIdCardNumber());
            }
            BeanUtils.copyProperties(xn631673ReqData, workerContract);
            BeanUtils.copyProperties(workerInfoByIdCardNumber, workerContract);
            // 录入数据
            workerContractBO.saveWorkerContract(workerContract);
        }
    }

}
