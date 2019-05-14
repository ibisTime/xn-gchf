package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631713Req;
import com.cdkj.gchf.dto.req.XN631713ReqData;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.enums.EAttendanceSource;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EDirectionType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectWorkerUploadStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.enums.EWorkerAttendanceUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.google.gson.JsonObject;

@Service
public class WorkerAttendanceAOImpl implements IWorkerAttendanceAO {

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addWorkerAttendance(XN631710Req data) {
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(data.getTeamSysNo());
        if (teamMaster == null) {
            throw new BizException("XN631710", "班组信息不存在");
        }
        if (projectBO.getProject(data.getProjectCode()) == null) {
            throw new BizException("XN631710", "请选择项目");
        }
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(data.getWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631710", "项目人员不存在");
        }
        return workerAttendanceBO.saveWorkerAttendance(data, teamMaster);
    }

    @Override
    public void dropWorkerAttendance(List<String> codeList) {
        for (String code : codeList) {
            WorkerAttendance workerAttendance = workerAttendanceBO
                .getWorkerAttendance(code);
            if (workerAttendance.getUploadStatus().equals(
                EWorkerAttendanceUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
                throw new BizException("XN631711", "人员考勤已上传，不可删除");
            }
            workerAttendanceBO.updateWorkerAttendanceDeleteStatus(code,
                EDeleteStatus.DELETED.getCode());
        }
    }

    @Override
    public void editWorkerAttendance(XN631712Req data) {
        if (workerAttendanceBO.getWorkerAttendance(data.getCode())
            .getUploadStatus().equals(
                EWorkerAttendanceUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631712", "人员考勤已上传,无法修改");
        }
        workerAttendanceBO.refreshWorkerAttendance(data);
    }

    @Override
    @Transactional
    public void batchCreateAttandance(String projectCode, String teamMasterNo,
            String direction, Date startDatetime, Date endDatetime) {

        Project project = projectBO.getProject(projectCode);
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(teamMasterNo);

        List<ProjectWorker> projectWorkers = projectWorkerBO
            .queryUploadedProjectWorkerList(teamMaster.getCode());

        if (CollectionUtils.isNotEmpty(projectWorkers)) {
            for (ProjectWorker projectWorker : projectWorkers) {
                Date date = new Date(
                    random(startDatetime.getTime(), endDatetime.getTime()));

                workerAttendanceBO.saveWorkerAttendance(project, teamMaster,
                    projectWorker, date, direction);
            }
        }
    }

    @Override
    @Transactional
    public void importWorkerAttendanceList(XN631713Req req) {

        User user = userBO.getBriefUser(req.getUpdater());
        if (projectBO.getProject(req.getProjectCode()) == null) {
            throw new BizException("xn631713", "请选择项目");
        }
        if (teamMasterBO.getTeamMaster(req.getTeamSysNo()) == null) {
            throw new BizException("xn631713", "请选择班组");
        }
        for (XN631713ReqData dateReq : req.getDateList()) {
            // 校验数据字典数据
            EDirectionType.checkExists(dateReq.getDirection());

            // 核实身份信息
            String idcardNumber = dateReq.getIdCardNumber();
            ProjectWorker workerByIdCardNumber = projectWorkerBO
                .getProjectWorker(req.getProjectCode(), idcardNumber);
            if (workerByIdCardNumber == null) {
                throw new BizException("XN631713",
                    "员工信息【" + idcardNumber + "】不存在");
            }

            // 录入数据
            WorkerAttendance workerAttendance = new WorkerAttendance();
            BeanUtils.copyProperties(dateReq, workerAttendance);
            BeanUtils.copyProperties(workerByIdCardNumber, workerAttendance);
            workerAttendance.setWorkerCode(workerByIdCardNumber.getCode());

            TeamMaster condition = new TeamMaster();
            condition.setCorpCode(dateReq.getCorpCode());
            condition.setRealTeamName(dateReq.getTeamName());
            TeamMaster masterByCondition = teamMasterBO
                .getTeamMasterByCondition(condition);
            if (masterByCondition == null) {
                throw new BizException("XN631713",
                    "班组信息【" + dateReq.getTeamName() + "】不存在");
            }

            workerAttendance.setTeamSysNo(masterByCondition.getCode());
            if (StringUtils.isNotBlank(dateReq.getDate())) {
                Date date = DateUtil.strToDate(dateReq.getDate(),
                    DateUtil.DATA_TIME_PATTERN_1);
                workerAttendance.setDate(date);
            }
            workerAttendance.setIdCardType("01");
            workerAttendance.setWorkerName(dateReq.getWorkerName());
            workerAttendance.setSource(EAttendanceSource.SYSTEM.getCode());
            workerAttendance.setUploadStatus(
                EWorkerAttendanceUploadStatus.TO_UPLOAD.getCode());
            workerAttendance.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
            String code = workerAttendanceBO
                .saveWorkerAttendance(workerAttendance);
            operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code, "导入人员考勤",
                user, null);
        }
    }

    @Transactional
    @Override
    public void uploadWorkerAttendanceList(String userId,
            List<String> codeList) {
        User briefUser = userBO.getBriefUser(userId);
        // 未上传的项目人员不能上传
        for (String code : codeList) {
            WorkerAttendance workerAttendance = workerAttendanceBO
                .getWorkerAttendance(code);

            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(workerAttendance.getProjectCode());

            if (projectConfigByLocal == null) {
                throw new BizException("XN631714",
                    "项目【" + workerAttendance.getProjectName() + "】未配置，无法上传");
            }

            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(workerAttendance.getWorkerCode());
            if (!projectWorker.getUploadStatus()
                .equals(EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode())
                    && !projectWorker.getUploadStatus().equals(
                        EProjectWorkerUploadStatus.UPLOAD_UNUPDATE.getCode())) {
                // 不是已上传的人员
                throw new BizException("XN00000",
                    "项目人员未上传【 " + projectWorker.getWorkerName() + " 】");
            }
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(workerAttendance.getTeamSysNo());
            // 获取上传json
            JsonObject requestJson = workerAttendanceBO.getRequestJson(
                teamMaster, workerAttendance, projectConfigByLocal);
            // 更改上传状态为上传中
            workerAttendanceBO.refreshWorkerAttendance(code,
                EWorkerAttendanceUploadStatus.UPLOADING.getCode());
            String resString;
            try {
                resString = GovConnecter.getGovData("WorkerAttendance.Add",
                    requestJson.toString(),
                    projectConfigByLocal.getProjectCode(),
                    projectConfigByLocal.getSecret());
            } catch (BizException e) {
                // 捕捉国家平台抛出的异常,将数据状态更新为上传失败
                e.printStackTrace();
                workerAttendanceBO.refreshWorkerAttendance(code,
                    EWorkerAttendanceUploadStatus.UPLOAD_FAIL.getCode());
                throw e;
            }

            String saveOperateLog = operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code,
                EOperateLogOperate.UploadWorkAtendance.getValue(), briefUser,
                EOperateLogOperate.UploadWorkAtendance.getValue());
            AsyncQueueHolder.addSerial(resString, projectConfigByLocal,
                "workerAttendanceBO", code,
                EWorkerAttendanceUploadStatus.UPLOAD_UNEDITABLE.getCode(),
                saveOperateLog, userId);
        }

    }

    @Override
    public void uploadWorkerAttendance(XN631918Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631918", "该项目未配置，无法上传");
        }

        workerAttendanceBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<WorkerAttendance> queryWorkerAttendance(XN631919Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631919", "该项目未配置，无法查询");
        }

        Paginable<WorkerAttendance> page = workerAttendanceBO.doQuery(req,
            projectConfig);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (WorkerAttendance workerAttendance : page.getList()) {

                String idcardNumber = AesUtils.decrypt(
                    workerAttendance.getIdCardNumber(),
                    projectConfig.getSecret());

                XN631913Req workerReq = new XN631913Req(req.getProjectCode(),
                    null, idcardNumber);
                workerReq.setPageIndex(0);
                workerReq.setPageSize(1);
                Paginable<ProjectWorker> projectWorker = projectWorkerBO
                    .doQuery(workerReq, projectConfig);

                if (null != projectWorker && CollectionUtils
                    .isNotEmpty(projectWorker.getList())) {
                    workerAttendance.setCorpName(
                        projectWorker.getList().get(0).getCorpName());
                    workerAttendance.setTeamName(
                        projectWorker.getList().get(0).getTeamName());
                    workerAttendance.setWorkerName(
                        projectWorker.getList().get(0).getWorkerName());
                }

                workerAttendance.setIdCardNumber(idcardNumber);
            }
        }

        return page;
    }

    @Override
    public Paginable<WorkerAttendance> queryWorkerAttendancePage(int start,
            int limit, WorkerAttendance condition) {

        User user = userBO.getBriefUser(condition.getUserId());
        if (EUserKind.Owner.getCode().equals(user.getType())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        Paginable<WorkerAttendance> page = workerAttendanceBO
            .getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (WorkerAttendance workerAttendance : page.getList()) {
                TeamMaster teamMaster = teamMasterBO
                    .getTeamMaster(workerAttendance.getTeamSysNo());
                workerAttendance.setCorpName(teamMaster.getCorpName());
            }
        }
        page.setList(page.getList());
        return page;
    }

    @Override
    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition) {
        return workerAttendanceBO.queryWorkerAttendanceList(condition);
    }

    @Override
    public WorkerAttendance getWorkerAttendance(String code) {
        return workerAttendanceBO.getWorkerAttendance(code);
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }
}
