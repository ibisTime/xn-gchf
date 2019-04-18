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
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EDirectionType;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;

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

        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(data.getWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631710", "项目人员不存在");
        }
        return workerAttendanceBO.saveWorkerAttendance(data, teamMaster);
    }

    @Override
    public void editWorkerAttendance(XN631712Req data) {
        if (workerAttendanceBO.getWorkerAttendance(data.getCode())
            .getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631712", "人员考勤已上传,无法修改");
        }
        workerAttendanceBO.refreshWorkerAttendance(data);
    }

    @Override
    public void dropWorkerAttendance(List<String> codeList) {
        for (String code : codeList) {
            WorkerAttendance workerAttendance = workerAttendanceBO
                .getWorkerAttendance(code);
            if (workerAttendance.getUploadStatus()
                .equals(EUploadStatus.UPLOAD_EDITABLE.getCode())
                    || workerAttendance.getUploadStatus()
                        .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
                throw new BizException("XN631711", "人员考勤已上传，不可删除");
            }
            workerAttendanceBO.updateWorkerAttendanceDeleteStatus(code,
                EDeleteStatus.DELETED.getCode());
        }
    }

    @Override
    @Transactional
    public void batchCreateAttandance(String projectCode, String direction,
            Date startDatetime, Date endDatetime) {

        Project project = projectBO.getProject(projectCode);

        List<TeamMaster> teamMasters = teamMasterBO
            .queryTeamMasterList(projectCode);

        if (CollectionUtils.isNotEmpty(teamMasters)) {
            for (TeamMaster teamMaster : teamMasters) {
                List<ProjectWorker> projectWorkers = projectWorkerBO
                    .queryProjectWorkerList(teamMaster.getCode());

                if (CollectionUtils.isNotEmpty(projectWorkers)) {
                    for (ProjectWorker projectWorker : projectWorkers) {
                        Date date = new Date(random(startDatetime.getTime(),
                            endDatetime.getTime()));

                        workerAttendanceBO.saveWorkerAttendance(project,
                            teamMaster, projectWorker, date, direction);
                    }
                }
            }
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

    @Override
    public void uploadWorkerAttendanceList(String userId,
            List<String> codeList) {
        for (String code : codeList) {
            WorkerAttendance workerAttendance = workerAttendanceBO
                .getWorkerAttendance(code);
            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(workerAttendance.getProjectCode());
            if (projectConfigByLocal == null) {
                throw new BizException("XN631714", "该项目未配置，无法查询");
            }
        }
        workerAttendanceBO.saveWorkerAttendanceToPlantform(userId, codeList);
    }

    @Override
    @Transactional
    public void importWorkerAttendanceList(XN631713Req req) {

        User user = userBO.getBriefUser(req.getUpdater());

        for (XN631713ReqData dateReq : req.getDateList()) {
            // 校验数据字典数据
            EDirectionType.checkExists(dateReq.getDirection());

            // 核实身份信息
            String idcardNumber = dateReq.getIdCardNumber();
            ProjectWorker workerByIdCardNumber = projectWorkerBO
                .getProjectWorker(req.getProjectCode(), dateReq.getCorpCode(),
                    req.getTeamSysNo(), idcardNumber);
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
            workerAttendance.setTeamSysNo(masterByCondition.getCode());
            if (StringUtils.isNotBlank(dateReq.getDate())) {
                Date date = DateUtil.strToDate(dateReq.getDate(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                // Date strToDate = DateUtil.strToDate(dateReq.getDate(),
                // "yyyy/mm/dd");
                // //
                // String format = new SimpleDateFormat("yyyy-MM-dd")
                // .format(strToDate);
                // Date toDate = DateUtil.strToDate(format, "yyyy-MM-dd");
                workerAttendance.setDate(date);
            }
            workerAttendance.setIdCardType("01");
            workerAttendance.setWorkerName(dateReq.getWorkerName());
            workerAttendance.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
            workerAttendance.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
            String code = workerAttendanceBO
                .saveWorkerAttendance(workerAttendance);
            operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code, "导入人员考勤",
                user, null);
        }
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
