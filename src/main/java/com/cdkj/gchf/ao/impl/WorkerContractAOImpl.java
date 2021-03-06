package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.req.XN631673Req;
import com.cdkj.gchf.dto.req.XN631673ReqData;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.cdkj.gchf.enums.EContractPeriodType;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectWorkerUploadStatus;
import com.cdkj.gchf.enums.EUnitType;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.enums.EWorkerContractUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
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
    private IProjectBO projectBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Override
    public String addWorkerContract(XN631670Req req) {
        // 如果是项目端 更改项目编号
        User user = userBO.getBriefUser(req.getUserId());
        if (user.getType().equals(EUserKind.Owner.getCode())) {
            req.setProjectCode(user.getOrganizationCode());
        }

        // 数据校验
        if (StringUtils.isNotBlank(req.getContractPeriodType())) {
            EContractPeriodType.checkExists(req.getContractPeriodType());
        }
        if (StringUtils.isNotBlank(req.getUnit())) {
            EUnitType.checkExists(req.getUnit());
        }
        Project project = projectBO.getProject(req.getProjectCode());
        if (project == null) {
            throw new BizException("XN631670", "请选择项目");
        }
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(req.getWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631670", "请选择员工");
        }
        // 国家平台上传会报已存在 、一个人员只能上传一个劳动合同
        WorkerContract workerContract = workerContractBO
                .getWorkerContract(req.getProjectCode(), req.getWorkerCode());
        if (workerContract != null) {
            throw new BizException("Xn631670",
                    "项目人员【" + projectWorker.getWorkerName() + "】合同已添加");
        }

        return workerContractBO.saveWorkerContract(req);
    }

    @Override
    public void dropWorkerContract(String userId, List<String> codeList) {
        for (String code : codeList) {
            WorkerContract workerContract = workerContractBO
                    .getWorkerContract(code);
            if (workerContract == null) {
                throw new BizException("XN631671", "项目合同不存在");
            }
            if (workerContract.getUploadStatus().equals(
                    EWorkerContractUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
                throw new BizException("XN631671", "项目已经上传 无法删除");
            }
            workerContractBO.updateWorkerContractDeleteStatus(code,
                    EDeleteStatus.DELETED.getCode());
        }
    }

    @Override
    public void editWorkerContract(XN631672Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        WorkerContract workerContract = workerContractBO
            .getWorkerContract(req.getCode());
        if (workerContract == null) {
            throw new BizException("XN631672", "劳动合同不存在");
        }
        if (workerContract.getUploadStatus()
                .equals(EWorkerContractUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631672", "劳动合同已上传,无法编辑");
        }
        if (StringUtils.isNotBlank(req.getUnit())) {
            EUnitType.checkExists(req.getUnit());
        }
        if (StringUtils.isNotBlank(req.getContractPeriodType())) {
            EContractPeriodType.checkExists(req.getContractPeriodType());
        }
        if (user.getType().equals(EUserKind.Owner.getCode())) {
            req.setProjectCode(user.getOrganizationCode());
        }
        workerContractBO.refreshWorkerContract(req);
    }

    @Transactional
    @Override
    public void importWorkContractList(XN631673Req req) {
        User user = userBO.getBriefUser(req.getUpdater());
        Project project = projectBO.getProject(req.getProjectCode());
        if (project == null) {
            throw new BizException("XN631673", "请选择项目");
        }
        List<XN631673ReqData> workContractList = req.getDateList();
        List<ProjectWorker> projectWorkers = new ArrayList<>();
        for (XN631673ReqData xn631673ReqData : workContractList) {

            // 校验数据字典数据
            EContractPeriodType
                    .checkExists(xn631673ReqData.getContractPeriodType());
            // 取得个人信息
            ProjectWorker projectWorker = projectWorkerBO.getProjectWorker(
                    req.getProjectCode(), xn631673ReqData.getIdCardNumber());
            if (null == projectWorker) {
                throw new BizException("XN631673",
                        "项目人员不存在【" + xn631673ReqData.getIdCardNumber() + "】");
            }
            projectWorkers.add(projectWorker);
        }
        workerContractBO.batchSaveWorkerContract(user, projectWorkers, req.getDateList());
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
            // 判断人员是否上传
            ProjectWorker projectWorker = projectWorkerBO
                    .getProjectWorker(workerContract.getWorkerCode());
            if (!projectWorker.getUploadStatus()
                    .equals(EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode())
                    && !projectWorker.getUploadStatus().equals(
                    EProjectWorkerUploadStatus.UPLOAD_UNUPDATE.getCode())) {
                // 不是已上传的人员
                throw new BizException("XN00000",
                        "项目人员未上传【 " + projectWorker.getWorkerName() + " 】");

            }
            // 请求json
            JsonObject jsonObject = workerContractBO
                    .getRequestJson(workerContract, projectConfig);

            workerContractBO.refreshUploadStatus(code,
                    EWorkerContractUploadStatus.UPLOADING.getCode());
            // 上传到国家平台
            String resString;
            try {
                resString = GovConnecter.getGovData("WorkerContract.Add",
                        jsonObject.toString(), projectConfig.getProjectCode(),
                        projectConfig.getSecret());
            } catch (BizException e) {
                e.printStackTrace();
                workerContractBO.refreshUploadStatus(code,
                        EWorkerContractUploadStatus.UPLOAD_FAIL.getCode());
                throw e;
            }
            // 增加操作日志
            String log = operateLogBO.saveOperateLog(
                    EOperateLogRefType.WorkContract.getCode(), code,
                    EOperateLogOperate.UploadWorkContract.getValue(), briefUser,
                    EOperateLogOperate.UploadWorkContract.getValue());
            // 队列更新状态
            AsyncQueueHolder.addSerial(resString, projectConfig,
                    "workerContractBO", code,
                    EWorkerContractUploadStatus.UPLOAD_UNEDITABLE.getCode(), log,
                    userId);
        }
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

        User user = userBO.getBriefUser(condition.getUserId());
        if (EUserKind.Owner.getCode().equals(user.getType())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        return workerContractBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WorkerContract> queryWorkerContractList(
            WorkerContract condition) {
        return workerContractBO.queryWorkerContractList(condition);
    }

    @Override
    public WorkerContract getWorkerContract(String code) {
        WorkerContract workerContract = workerContractBO
            .getWorkerContract(code);
        String workerCode = workerContract.getWorkerCode();
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(workerCode);
        workerContract.setTeamName(projectWorker.getWorkerName());
        workerContract.setIdcardNumber(projectWorker.getIdcardNumber());
        workerContract.setProjectName(projectWorker.getProjectName());
        return workerContract;
    }

}
