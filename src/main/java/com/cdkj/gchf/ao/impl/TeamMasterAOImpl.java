package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631651Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631653Req;
import com.cdkj.gchf.dto.req.XN631653ReqData;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;

@Service
public class TeamMasterAOImpl implements ITeamMasterAO {

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IWorkerContractBO workerContractBO;

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IPayRollBO payRollBO;

    @Autowired
    private IPayRollDetailBO payRollDetailBO;

    @Autowired
    private IProjectWorkerEntryExitHistoryBO projectWorkerEntryExitHistoryBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addTeamMaster(XN631650Req data) {

        CorpBasicinfo corpBasicinfoByCorp = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());
        if (corpBasicinfoByCorp == null) {
            throw new BizException("XN631650", "企业信息不存在");
        }

        TeamMaster teamMaster = teamMasterBO.getTeamMasterByProject(
            data.getProjectCode(), data.getCorpCode(), data.getTeamName());
        if (null != teamMaster) {
            throw new BizException("XN631650", "班组名称已存在，请重新输入");
        }

        return teamMasterBO.saveTeamMaster(data, corpBasicinfoByCorp);
    }

    @Transactional
    @Override
    public void dropTeamMaster(XN631651Req req) {
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(req.getCode());
        if (teamMaster.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_EDITABLE.getCode())
                || teamMaster.getUploadStatus()
                    .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631651", "班组信息已上传,无法删除");
        }
        teamMasterBO.updateTeamMasterDeleteStatus(req.getCode(),
            EDeleteStatus.DELETED.getCode());

        projectWorkerBO.fakeDeleteProjectWorkerByTeamNo(
            teamMaster.getProjectCode(), teamMaster.getCode());

        projectWorkerEntryExitHistoryBO
            .fakeDeleteProjectWorkerEntryHistoryByTeamMaster(
                teamMaster.getCode());

        workerAttendanceBO
            .fakeDeleteWorkAttendanceByTeamMaster(teamMaster.getCode());

        ProjectWorker condition = new ProjectWorker();
        condition.setTeamSysNo(req.getCode());
        List<ProjectWorker> queryProjectWorkerList = projectWorkerBO
            .queryProjectWorkerList(condition);
        for (ProjectWorker projectWorker : queryProjectWorkerList) {
            workerContractBO.fakeDeleteWorkerContract(projectWorker.getCode());
            payRollDetailBO.fakeDeletePayRollDetail(
                projectWorker.getIdcardType(), projectWorker.getIdcardNumber(),
                teamMaster.getProjectCode());
        }
        payRollBO.updatePayRollDeleteStatus(teamMaster.getProjectCode(),
            req.getCode(), teamMaster.getCorpCode());
    }

    @Override
    @Transactional
    public void editTeamMaster(XN631652Req data) {
        User user = userBO.getBriefUser(data.getUserId());

        TeamMaster teamMaster = teamMasterBO.getTeamMaster(data.getCode());
        if (teamMaster.getUploadStatus() != null & teamMaster.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631652", "班组信息已上传,不可修改");
        }

        if (!data.getTeamName().equals(teamMaster.getTeamName())) {
            teamMaster = teamMasterBO.getTeamMasterByProject(
                teamMaster.getProjectCode(), teamMaster.getCorpCode(),
                data.getTeamName());
            if (null != teamMaster) {
                throw new BizException("XN631650", "班组名称已存在，请重新输入");
            }
        }

        teamMasterBO.refreshTeamMaster(data);

        teamMasterBO.refreshUploadStatus(data.getCode(),
            EUploadStatus.TO_UPLOAD.getCode());

        operateLogBO.saveOperateLog(EOperateLogRefType.TeamMaster.getCode(),
            data.getCode(), EOperateLogOperate.EditTeamMaster.getValue(), user,
            null);
    }

    @Override
    public void uploadTeamMaster(List<String> codeList, String userId) {
        User operator = userBO.getBriefUser(userId);
        for (String code : codeList) {
            TeamMaster teamMaster = getTeamMaster(code);
            if (EUploadStatus.UPLOAD_EDITABLE.getCode()
                .equals(teamMaster.getUploadStatus())) {
                continue;
            }
            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(teamMaster.getProjectCode());
            if (null == projectConfig) {
                throw new BizException("XN631253", "不存在已配置的项目，无法上传");
            }
            // 处理需要加密的信息
            if (StringUtils
                .isNotBlank(teamMaster.getResponsiblePersonIdNumber())) {
                String encryptIdCardNumber = AesUtils.encrypt(
                    teamMaster.getResponsiblePersonIdNumber(),
                    projectConfig.getSecret());
                teamMaster.setResponsiblePersonIdNumber(encryptIdCardNumber);
            }
            teamMaster.setProjectCode(projectConfig.getProjectCode());

            // 上传班组信息
            String teamMasterInfo = JSONObject
                .toJSONStringWithDateFormat(teamMaster, "yyyy-MM-dd");
            String resString = GovConnecter.getGovData("Team.Add",
                teamMasterInfo, projectConfig.getProjectCode(),
                projectConfig.getSecret());

            // 添加操作日志
            String logCode = operateLogBO.saveOperateLog(
                EOperateLogRefType.TeamMaster.getCode(), code,
                EOperateLogOperate.UploadTeamMaster.getValue(), operator, null);

            // 添加到上传状态更新队列
            AsyncQueueHolder.addSerial(resString, projectConfig, "teamMasterBO",
                code, EUploadStatus.UPLOAD_EDITABLE.getCode(), logCode);
        }
    }

    @Override
    public void uploadTeamMaster(XN631908Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631908", "该项目未配置，无法上传");
        }

        teamMasterBO.doUpload(req, projectConfig);
    }

    @Override
    public void updateTeamMaster(XN631909Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631909", "该项目未配置，无法修改");
        }

        teamMasterBO.doUpdate(req, projectConfig);
    }

    @Override
    public Paginable<TeamMaster> queryTeamMaster(XN631910Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631910", "该项目未配置，无法查询");
        }

        return teamMasterBO.doQuery(req, projectConfig);

    }

    @Override
    public Paginable<TeamMaster> queryTeamMasterPage(int start, int limit,
            TeamMaster condition) {

        User user = userBO.getBriefUser(condition.getUserId());
        if (EUserKind.Plat.getCode().equals(user.getType())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        Paginable<TeamMaster> page = teamMasterBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (TeamMaster teamMaster : page.getList()) {
                Project project = projectBO
                    .getProject(teamMaster.getProjectCode());
                teamMaster.setProjectName(project.getName());
            }
        }

        return page;
    }

    @Override
    public List<TeamMaster> queryTeamMasterList(TeamMaster condition) {
        return teamMasterBO.queryTeamMasterList(condition);
    }

    @Override
    public TeamMaster getTeamMaster(String code) {
        return teamMasterBO.getTeamMaster(code);
    }

    @Override
    public void importTeamMaster(XN631653Req req) {
        User user = userBO.getBriefUser(req.getUserId());

        // 根据corpCode获取企业信息
        for (XN631653ReqData reqData : req.getDateList()) {

            CorpBasicinfo corpBasicinfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(reqData.getCorpCode());
            if (corpBasicinfo == null) {
                throw new BizException("XN631650",
                    "企业信息【" + reqData.getCorpName() + "】不存在");
            }

            TeamMaster preTeamMaster = teamMasterBO.getTeamMasterByProject(
                req.getProjectCode(), reqData.getCorpCode(),
                reqData.getTeamName());
            if (null != preTeamMaster) {
                throw new BizException("XN631650",
                    "班组名称【" + reqData.getTeamName() + "】已存在，请重新输入");
            }

            TeamMaster teamMaster = new TeamMaster();
            BeanUtils.copyProperties(reqData, teamMaster);
            if (StringUtils.isNotBlank(reqData.getEntryTime())) {
                Date entryTime = DateUtil.strToDate(reqData.getEntryTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                teamMaster.setEntryTime(entryTime);
            }
            if (StringUtils.isNotBlank(reqData.getExitTime())) {
                Date exitTime = DateUtil.strToDate(reqData.getExitTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                teamMaster.setEntryTime(exitTime);
            }
            teamMaster.setProjectCode(req.getProjectCode());
            teamMaster.setCorpName(corpBasicinfo.getCorpName());
            teamMaster.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
            teamMaster.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
            String code = teamMasterBO.saveTeamMaster(teamMaster);
            operateLogBO.saveOperateLog(EOperateLogRefType.TeamMaster.getCode(),
                code, "导入班组信息", user, null);
        }

    }

}
