package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631733Req;
import com.cdkj.gchf.dto.req.XN631733ReqData;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.cdkj.gchf.enums.EEntryExitType;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.google.gson.JsonObject;

@Service
public class ProjectWorkerEntryExitHistoryAOImpl
        implements IProjectWorkerEntryExitHistoryAO {

    @Autowired
    private IProjectWorkerEntryExitHistoryBO projectWorkerEntryExitHistoryBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Override
    public String addProjectWorkerEntryExitHistory(XN631730Req data) {
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(data.getProjectWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631730", "员工信息不存在");
        }
        ProjectWorkerEntryExitHistory workerEntryExitHistory = projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistoryByIdCardNumber(
                projectWorker.getIdCardNumber());
        if (workerEntryExitHistory != null) {
            throw new BizException("XN631730", "人员进退场已添加");
        }
        return projectWorkerEntryExitHistoryBO
            .saveProjectWorkerEntryExitHistory(data);
    }

    @Override
    public void editProjectWorkerEntryExitHistory(XN631732Req req) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistory(req.getCode());
        if (projectWorkerEntryExitHistory.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631732", "人员进退场已上传,不可编辑");
        }

        projectWorkerEntryExitHistoryBO
            .refreshProjectWorkerEntryExitHistory(req);
    }

    @Override
    public void dropProjectWorkerEntryExitHistory(String code) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistory(code);
        if (projectWorkerEntryExitHistory.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631731", "人员进退场已上传，不可删除");
        }
        projectWorkerEntryExitHistoryBO
            .removeProjectWorkerEntryExitHistory(code);
    }

    @Override
    public void uploadProjectWorkerEntryExitHistory(XN631914Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631914", "该项目未配置，无法上传");
        }

        projectWorkerEntryExitHistoryBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistory(
            XN631915Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631915", "该项目未配置，无法查询");
        }

        Paginable<ProjectWorkerEntryExitHistory> page = projectWorkerEntryExitHistoryBO
            .doQuery(req, projectConfig);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory : page
                .getList()) {

                String idcardNumber = AesUtils.decrypt(
                    projectWorkerEntryExitHistory.getIdcardNumber(),
                    projectConfig.getSecret());

                XN631913Req workerReq = new XN631913Req(req.getProjectCode(),
                    projectWorkerEntryExitHistory.getCorpCode(), idcardNumber);
                workerReq.setPageIndex(0);
                workerReq.setPageSize(1);
                Paginable<ProjectWorker> projectWorker = projectWorkerBO
                    .doQuery(workerReq, projectConfig);
                if (null != projectWorker && CollectionUtils
                    .isNotEmpty(projectWorker.getList())) {
                    projectWorkerEntryExitHistory.setWorkerName(
                        projectWorker.getList().get(0).getWorkerName());
                }

                projectWorkerEntryExitHistory.setIdcardNumber(idcardNumber);

            }
        }

        return page;
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryPage(
            int start, int limit, ProjectWorkerEntryExitHistory condition) {
        return projectWorkerEntryExitHistoryBO.getPaginable(start, limit,
            condition);
    }

    @Override
    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition) {
        return projectWorkerEntryExitHistoryBO
            .queryProjectWorkerEntryExitHistoryList(condition);
    }

    @Override
    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code) {
        return projectWorkerEntryExitHistoryBO
            .getProjectWorkerEntryExitHistory(code);
    }

    @Override
    public Object queryProjectWorkerEntryExitHistory(String code) {
        return projectWorkerEntryExitHistoryBO
            .queryProjectWorkerEntryExitHistory(code);
    }

    @Transactional
    @Override
    public void uploadProjectWorkerEntryExitHistoryList(String userId,
            List<String> codeList) {
        User briefUser = userBO.getBriefUser(userId);
        for (String code : codeList) {
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = projectWorkerEntryExitHistoryBO
                .getProjectWorkerEntryExitHistory(code);
            projectWorkerEntryExitHistory.getProjectCode();
            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(
                    projectWorkerEntryExitHistory.getProjectCode());
            if (projectConfigByLocal == null) {
                throw new BizException("XN631734", "项目编号不存在");
            }
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(projectWorkerEntryExitHistory.getTeamSysNo());
            JsonObject requestJson = projectWorkerEntryExitHistoryBO
                .getRequestJson(teamMaster, projectWorkerEntryExitHistory,
                    projectConfigByLocal);
            System.out.println(requestJson.toString());

            String resString = GovConnecter.getGovData("WorkerEntryExit.Add",
                requestJson.toString(), projectConfigByLocal.getProjectCode(),
                projectConfigByLocal.getSecret());

            String operateLog = operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectWorkerEntryExitHistory.getCode(),
                code, EOperateLogOperate.UploadProjectWorkerEntryExitHistory
                    .getValue(),
                briefUser, null);

            AsyncQueueHolder.addSerial(resString, projectConfigByLocal,
                "projectWorkerEntryExitHistoryBO", code,
                EUploadStatus.UPLOAD_UNEDITABLE.getCode(), operateLog);

        }
    }

    /**
     * <p>Description: 导入人员进退场</p>   
     */
    @Transactional
    @Override
    public void importProjectWorkerEntryExitHistoryList(XN631733Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        String projectCode = req.getProjectCode();
        ProjectConfig configByLocal = projectConfigBO
            .getProjectConfigByLocal(projectCode);
        if (configByLocal == null) {
            throw new BizException("XN631733", "项目不存在" + req.getProjectCode());
        }

        List<XN631733ReqData> dateList = req.getDateList();
        for (XN631733ReqData xn631733ReqData : dateList) {
            // 校验数据字典类型数据
            EIdCardType.checkExists(xn631733ReqData.getIdcardType());
            EEntryExitType
                .checkExists(String.valueOf(xn631733ReqData.getType()));
            ProjectWorkerEntryExitHistory entryExitHistory = new ProjectWorkerEntryExitHistory();
            entryExitHistory.setProjectCode(projectCode);
            WorkerInfo infoByIdCardNumber = workerInfoBO
                .getWorkerInfoByIdCardNumber(xn631733ReqData.getIdcardNumber());
            if (infoByIdCardNumber == null) {
                throw new BizException("XN631733",
                    "项目人员未录入" + xn631733ReqData.getIdcardNumber());
            }
            // 根据idcardnumber和项目名称获取班组信息
            TeamMaster condition = new TeamMaster();
            condition.setTeamName(xn631733ReqData.getTeamName());
            condition.setProjectCode(projectCode);
            TeamMaster masterByCondition = teamMasterBO
                .getTeamMasterByCondition(condition);
            if (masterByCondition == null) {
                throw new BizException("XN631733", "班组信息不存在");
            }
            entryExitHistory.setCorpCode(masterByCondition.getCorpCode());
            entryExitHistory.setCorpName(masterByCondition.getCorpName());
            entryExitHistory.setWorkerCode(infoByIdCardNumber.getCode());
            entryExitHistory.setWorkerName(infoByIdCardNumber.getName());
            entryExitHistory.setPosition(infoByIdCardNumber.getWorkerType());
            entryExitHistory
                .setJoinDatetime(infoByIdCardNumber.getJoinedTime());
            entryExitHistory.setIdcardType(xn631733ReqData.getIdcardType());
            entryExitHistory.setIdcardNumber(xn631733ReqData.getIdcardNumber());
            Date date = DateUtil.strToDate(xn631733ReqData.getDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            entryExitHistory.setDate(date);
            entryExitHistory.setType(xn631733ReqData.getType());
            String code = projectWorkerEntryExitHistoryBO
                .saveProjectWorkerEntryExitHistory(entryExitHistory);
            operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code, "导入人员进退场信息",
                user, null);
        }
    }

}
