package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.impl.XN631693ReqData;
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
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631690Req;
import com.cdkj.gchf.dto.req.XN631692Req;
import com.cdkj.gchf.dto.req.XN631693Req;
import com.cdkj.gchf.dto.req.XN631694Req;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EIsNotType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EPoliticsType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.enums.EWorkerRoleType;
import com.cdkj.gchf.enums.EWorkerType;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.google.gson.JsonObject;

@Service
public class ProjectWorkerAOImpl implements IProjectWorkerAO {

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IPayRollBO payRollBO;

    @Autowired
    private IPayRollDetailBO payRollDetailBO;

    @Autowired
    private IWorkerContractBO workerContractBO;

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IProjectWorkerEntryExitHistoryBO projectWorkerEntryExitHistoryBO;

    @Override
    public String addProjectWorker(XN631690Req req) {

        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(req.getWorkerCode());
        if (workerInfo == null) {
            throw new BizException("XN631690", "人员编号无效,项目人员不存在");
        }
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(req.getTeamSysNo());
        if (teamMaster == null) {
            throw new BizException("XN631690", "班组信息不存在");
        }
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(req.getCorpCode());
        if (corpBasicinfo == null) {
            throw new BizException("xn631690", "企业信息不存在");
        }

        return projectWorkerBO.saveProjectWorker(req);
    }

    @Override
    public void editProjectWorker(XN631692Req req) {

        User user = userBO.getBriefUser(req.getUserId());
        if (projectWorkerBO.getProjectWorker(req.getCode()).getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631692", "班组人员已上传,无法删除");
        }
        if (StringUtils.isNotBlank(req.getWorkType())) {
            EWorkerType.checkExists(req.getWorkType());
        }
        projectWorkerBO.refreshProjectWorker(req);
        operateLogBO.saveOperateLog(EOperateLogRefType.ProjectWorker.getCode(),
            req.getCode(), "修改项目人员信息", user, null);

    }

    @Override
    public void dropProjectWorker(String code) {
        ProjectWorker projectWorker = projectWorkerBO.getProjectWorker(code);
        if (projectWorker.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())
                || projectWorker.getUploadStatus()
                    .equals(EUploadStatus.UPLOAD_EDITABLE.getCode())) {
            throw new BizException("XN631691", "班组人员已上传,无法删除");
        }
        projectWorkerBO.updateProjectWorkerDeleteStatus(code,
            EDeleteStatus.DELETED.getCode());

        workerContractBO.fakeDeleteWorkerContract(code);

        workerAttendanceBO.fakeDeleteWorkAttendanceByWorkerCode(code);

        projectWorkerEntryExitHistoryBO
            .fakeDeleteProjectWorkerEntryHistory(code);

        PayRollDetail condition = new PayRollDetail();
        condition.setIdcardType(projectWorker.getIdcardType());
        condition.setIdcardNumber(projectWorker.getIdcardNumber());
        List<PayRollDetail> queryList = payRollDetailBO.queryList(condition);
        for (PayRollDetail payRollDetail : queryList) {
            String payRollCode = payRollDetail.getPayRollCode();
            payRollBO.updatePayRollDeleteStatus(payRollCode);
        }
        payRollDetailBO.fakeDeletePayRollDetail(projectWorker.getIdcardType(),
            projectWorker.getIdcardNumber(), projectWorker.getProjectCode());
    }

    @Override
    public void uploadProjectWorker(XN631911Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631911", "该项目未配置，无法上传");
        }

        projectWorkerBO.doUpload(req, projectConfig);
    }

    @Override
    public void updateProjectWorker(XN631912Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631912", "该项目未配置，无法修改");
        }

        projectWorkerBO.doUpdate(req, projectConfig);
    }

    /**
     * 导入项目人员
     * 1、根据证件查询人员实名制表信息
     *  1.1、如果表中没有这个数据 添加到实名制信息表中
     *  1.2、否则判断是否添加过该员工，没添加再添加
     */
    @Override
    @Transactional
    public void importProjectWorkers(XN631693Req req) {

        Project project = projectBO.getProject(req.getProjectCode());

        for (XN631693ReqData projectWorkerData : req.getWorkerList()) {
            // 数据字典key校验
            checkDicKey(projectWorkerData);

            CorpBasicinfo corpBasicinfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(projectWorkerData.getCorpCode());
            if (corpBasicinfo == null) {
                throw new BizException("XN631793",
                    "企业【" + projectWorkerData.getCorpCode() + "】不存在");
            }

            TeamMaster teamMaster = teamMasterBO.getTeamMasterByProject(
                project.getCode(), projectWorkerData.getCorpCode(),
                projectWorkerData.getTeamName());
            if (teamMaster == null) {
                throw new BizException("XN631793",
                    "班组【" + projectWorkerData.getTeamName() + "】未录入");
            }

            ProjectWorker preProjectWorker = projectWorkerBO.getProjectWorker(
                project.getCode(), projectWorkerData.getCorpCode(),
                teamMaster.getCode(), projectWorkerData.getIdCardNumber());
            if (preProjectWorker != null) {
                continue;
            }

            ProjectWorker projectWorker = new ProjectWorker();
            BeanUtils.copyProperties(projectWorkerData, projectWorker);
            projectWorker.setProjectCode(project.getCode());

            projectWorker.setTeamSysNo(teamMaster.getCode());
            projectWorker.setCorpName(corpBasicinfo.getCorpName());
            projectWorker.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
            projectWorker.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
            projectWorker.setIsTeamLeader(
                Integer.parseInt(projectWorkerData.getIsTeamLeader()));
            projectWorker.setIdcardNumber(projectWorkerData.getIdCardNumber());
            projectWorker.setIdcardType(projectWorkerData.getIdCardType());
            // 检查人员实名信息表是否存在员工信息
            WorkerInfo infoByIdCardNumber = workerInfoBO
                .getWorkerInfoByIdCardNumber(
                    projectWorkerData.getIdCardNumber());
            if (infoByIdCardNumber != null) {
                BeanUtils.copyProperties(infoByIdCardNumber, projectWorker);
                if (StringUtils
                    .isNotBlank(projectWorkerData.getHasBuyInsurance())) {
                    projectWorker.setHasBuyInsurance(Integer
                        .parseInt(projectWorkerData.getHasBuyInsurance()));
                }
                projectWorkerBO.saveProjectWorker(projectWorker);
            } else {
                workerInfoBO.saveWorkerInfoByImport(projectWorkerData);
                projectWorker.setProjectName(project.getName());
                projectWorker
                    .setIdcardNumber(projectWorkerData.getIdCardNumber());
                if (StringUtils
                    .isNotBlank(projectWorkerData.getHasBuyInsurance())) {
                    projectWorker.setHasBuyInsurance(Integer
                        .parseInt(projectWorkerData.getHasBuyInsurance()));
                }
                projectWorkerBO.saveProjectWorker(projectWorker);

            }

        }
    }

    @Override
    public void uploadProjectWorker(XN631694Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        List<String> codeList = req.getCodeList();
        for (String code : codeList) {
            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(code);

            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(projectWorker.getTeamSysNo());

            if (teamMaster == null) {
                throw new BizException("XN631690", "班组信息不存在");
            }
            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(projectWorker.getProjectCode());

            JsonObject json = projectWorkerBO.getProjectWorkerJson(teamMaster,
                projectWorker, projectConfig);

            String resString = GovConnecter.getGovData("ProjectWorker.Add",
                json.toString(), projectConfig.getProjectCode(),
                projectConfig.getSecret());

            String logCode = operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectWorker.getCode(), code,
                EOperateLogOperate.UploadProjectWorker.getValue(), user, null);

            AsyncQueueHolder.addSerial(resString, projectConfig,
                "projectWorkerBO", code,
                EUploadStatus.UPLOAD_EDITABLE.getCode(), logCode);
        }
    }

    public void checkDicKey(XN631693ReqData projectWorkerData) {

        EWorkerRoleType
            .checkExists(String.valueOf(projectWorkerData.getWorkRole()));
        EWorkerType.checkExists(projectWorkerData.getWorkType());
        EPoliticsType.checkExists(projectWorkerData.getPoliticsType());
        EIdCardType.checkExists(projectWorkerData.getIdCardType());
        EIsNotType.checkExists(projectWorkerData.getIsTeamLeader());
        if (StringUtils
            .isNotBlank(projectWorkerData.getHasBadMedicalHistory())) {
            EIsNotType.checkExists(projectWorkerData.getHasBadMedicalHistory());
        }
        if (StringUtils.isNotBlank(projectWorkerData.getHasBuyInsurance())) {
            EIsNotType.checkExists(projectWorkerData.getHasBuyInsurance());
        }
    }

    @Override
    public Paginable<ProjectWorker> queryProjectWorker(XN631913Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631913", "该项目未配置，无法查询");
        }

        return projectWorkerBO.doQuery(req, projectConfig);

    }

    @Override
    public Paginable<ProjectWorker> queryProjectWorkerPage(int start, int limit,
            ProjectWorker condition) {

        User user = userBO.getBriefUser(condition.getUserId());
        if (EUserKind.Owner.getCode().equals(user.getType())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        return projectWorkerBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition) {
        return projectWorkerBO.queryProjectWorkerList(condition);
    }

    @Override
    public ProjectWorker getProjectWorker(String code) {
        return projectWorkerBO.getProjectWorker(code);
    }

}
