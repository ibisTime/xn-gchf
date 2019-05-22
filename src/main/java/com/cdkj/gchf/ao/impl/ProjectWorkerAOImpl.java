package com.cdkj.gchf.ao.impl;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.impl.XN631693ReqData;
import com.cdkj.gchf.bo.*;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.ImportUtil;
import com.cdkj.gchf.domain.*;
import com.cdkj.gchf.dto.req.*;
import com.cdkj.gchf.enums.*;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.humanfaces.DeviceWorker;
import com.cdkj.gchf.humanfaces.enums.EAttendancePicUploadStatus;
import com.cdkj.gchf.humanfaces.enums.EWorkerUploadStatus;
import com.google.gson.JsonObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private IEquipmentWorkerBO equipmentWorkerBO;

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    @Autowired
    private DeviceWorker deviceWorker;

    @Override
    @Transactional
    public String addProjectWorker(XN631690Req req) {
        User user = userBO.getBriefUser(req.getUserId());
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
        if (projectBO.getProject(req.getProjectCode()) == null) {
            throw new BizException("XN631690", "请选择项目");
        }
        // 项目端
        if (StringUtils.isBlank(req.getProjectCode())) {
            req.setProjectCode(user.getOrganizationCode());
        }
        ProjectWorker preProjectWorker = projectWorkerBO.getProjectWorker(
            req.getProjectCode(), workerInfo.getIdCardNumber());

        if (preProjectWorker != null && preProjectWorker.getDeleteStatus()
            .equals(EDeleteStatus.NORMAL.getCode())) {
            throw new BizException("XN631690",
                "项目中已存在【" + workerInfo.getName() + "】的人员");
        }

        ProjectWorker projectWorker = projectWorkerBO.saveProjectWorker(req);

        //关联设备人员
        assignEquipmentWorker(projectWorker);

        return projectWorker.getCode();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addProjectWorker(XN631696Req req) {

        EWorkerRoleType.checkExists(req.getWorkRole());
        EWorkerType.checkExists(req.getWorkType());
        EIsNotType.checkExists(req.getHasBuyInsurance());

        User briefUser = userBO.getBriefUser(req.getUserId());

        Project project = projectBO.getProject(briefUser.getOrganizationCode());
        if (project == null) {
            throw new BizException("xn000000", "项目不存在");
        }
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(req.getWorkerCode());
        if (workerInfo == null) {
            throw new BizException("xn00000", "人员不存在");
        }
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(req.getTeamSysNo());
        if (teamMaster == null) {
            throw new BizException("XN00000", "请选择班组");
        }
        ProjectWorker projectWorker = projectWorkerBO.saveProjectWorker(project, teamMaster, workerInfo, req);

        //关联设备人员
        assignEquipmentWorker(projectWorker);

        return projectWorker.getCode();
    }

    private void assignEquipmentWorker(ProjectWorker projectWorker) {
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(projectWorker.getWorkerCode());
        if (!workerInfo.getWorkerPicUploadStatus()
                .equals(EAttendancePicUploadStatus.SUCCESS.getCode())
                && !workerInfo.getWorkerUploadStatus()
                .equals(EWorkerUploadStatus.SUCCESS.getCode())) {
            return;
        }

        List<EquipmentInfo> equipmentInfos = equipmentInfoBO.queryEquipmentList(projectWorker.getProjectCode());
        if (CollectionUtils.isEmpty(equipmentInfos)) {
            return;
        }
        deviceWorker.personnelEquipmentAuthorization(equipmentInfos, workerInfo.getWorkerGuid(), null, null);

        equipmentWorkerBO.batchSaveEquipmentWorker(projectWorker, equipmentInfos);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editProjectWorker(XN631692Req req) {

        User user = userBO.getBriefUser(req.getUserId());
        if (StringUtils.isNotBlank(req.getWorkType())) {
            EWorkerType.checkExists(req.getWorkType());
        }
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(req.getCode());

        if (projectWorker.getUploadStatus()
            .equals(EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode())
                || projectWorker.getUploadStatus().equals(
                    EProjectWorkerUploadStatus.UPLOAD_UNUPDATE.getCode())) {
            // 修改本地和平台
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(req.getTeamSysNo());

            projectWorkerBO.refreshProjectWorker(req, teamMaster);
            XN631695Req editReq = new XN631695Req();
            editReq.setUserId(req.getUserId());
            editReq.setCodeList(Arrays.asList(req.getCode()));
            updatePlantformProjectWorker(editReq);

            operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectWorker.getCode(), req.getCode(),
                "修改项目人员信息", user, "修改项目人员信息");

        } else {
            // 修改本地
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(req.getTeamSysNo());

            projectWorkerBO.refreshProjectWorker(req, teamMaster);

            operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectWorker.getCode(), req.getCode(),
                "修改项目人员信息", user, "修改项目人员信息");

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dropProjectWorker(List<String> codeList) {
        for (String code : codeList) {
            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(code);
            if (projectWorker.getUploadStatus()
                .equals(EProjectWorkerUploadStatus.UPLOAD_UNUPDATE.getCode())
                    || projectWorker.getUploadStatus().equals(
                        EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode())) {
                throw new BizException("XN631691", "班组人员已上传,无法删除");
            }
            projectWorkerBO.updateProjectWorkerDeleteStatus(code,
                EDeleteStatus.DELETED.getCode());

            workerContractBO.fakeDeleteWorkerContract(code);

            workerAttendanceBO.deleteWorkAttendanceByWorkerCode(code);

            projectWorkerEntryExitHistoryBO
                .fakeDeleteProjectWorkerEntryHistory(code);

            PayRollDetail condition = new PayRollDetail();
            condition.setIdcardType(projectWorker.getIdcardType());
            condition.setIdcardNumber(projectWorker.getIdcardNumber());
            List<PayRollDetail> queryList = payRollDetailBO
                .queryList(condition);
            // 如果工资单下还存在未删除的工资明细 不删除
            for (PayRollDetail payRollDetail : queryList) {
                String payRollCode = payRollDetail.getPayRollCode();
                List<PayRoll> payRollList = payRollBO
                    .getPayRollList(payRollCode);
                if (payRollList.size() > 1) {
                    continue;
                }
                payRollBO.updatePayRollDeleteStatus(payRollCode);
            }
            payRollDetailBO.fakeDeletePayRollDetail(
                projectWorker.getIdcardType(), projectWorker.getIdcardNumber(),
                projectWorker.getProjectCode());

        }

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importProjectWorkers(XN631693Req req) {

        Project project = projectBO.getProject(req.getProjectCode());
        if (project == null) {
            throw new BizException("XN631793", "项目不存在");
        }

        String repeatValue = ImportUtil.checkRepeat(req.getWorkerList(),
            "idCardNumber");
        if (StringUtils.isNotBlank(repeatValue)) {
            throw new BizException("XN000000",
                "导入数据中证件号码【" + repeatValue + "】存在重复");
        }

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

            // 校验班组中是否已存在该成员
            ProjectWorker projectWorkerByIdentity = projectWorkerBO
                .getProjectWorkerByIdentity(teamMaster.getCode(),
                    projectWorkerData.getIdCardNumber());
            if (projectWorkerByIdentity != null) {
                throw new BizException("XN631690",
                    teamMaster.getTeamName() + "班组中" + "已存在该人员【"
                            + projectWorkerData.getIdCardNumber() + "】");
            }

            // 检查人员实名信息表是否存在员工信息
            WorkerInfo infoByIdCardNumber = workerInfoBO
                .getWorkerInfoByIdCardNumber(
                    projectWorkerData.getIdCardNumber());
            if (infoByIdCardNumber != null) {
                projectWorkerBO.saveProjectWorker(infoByIdCardNumber,
                    projectWorkerData, project, teamMaster, corpBasicinfo);
            } else {
                String workerCode = workerInfoBO
                    .saveWorkerInfoByImport(projectWorkerData);
                projectWorkerBO.saveProjectWorker(workerCode, project,
                    corpBasicinfo, teamMaster, projectWorkerData);
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

            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(projectWorker.getProjectCode());
            if (projectConfig == null) {
                throw new BizException("XN631690",
                    "项目未配置" + projectWorker.getProjectName() + ",请检查项目配置");
            }

            JsonObject json = projectWorkerBO.getProjectWorkerJson(teamMaster,
                projectWorker, projectConfig);
            projectWorkerBO.refreshUploadStatus(code,
                EProjectWorkerUploadStatus.UPLOADING.getCode());
            String resString = null;
            try {
                // 捕捉国家平台异常
                resString = GovConnecter.getGovData("ProjectWorker.Add",
                    json.toString(), projectConfig.getProjectCode(),
                    projectConfig.getSecret());
            } catch (BizException e) {
                // 国家平台抛出的异常 数据处理后再抛出
                projectWorkerBO.refreshUploadStatus(code,
                    EProjectWorkerUploadStatus.UPLOAD_FAIL.getCode());
                throw e;
            }
            // 保存日志
            String logCode = operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectWorker.getCode(), code,
                EOperateLogOperate.UploadProjectWorker.getValue(), user, null);

            AsyncQueueHolder.addSerial(resString, projectConfig,
                "projectWorkerBO", code,
                EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode(), logCode,
                req.getUserId());
        }
    }

    @Override
    public void updatePlantformProjectWorker(XN631695Req req) {
        List<String> codeList = req.getCodeList();
        for (String code : codeList) {
            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(code);
            ProjectConfig configByLocal = projectConfigBO
                .getProjectConfigByLocal(projectWorker.getProjectCode());
            if (configByLocal == null) {
                throw new BizException("XN631695",
                    "项目未配置：" + projectWorker.getProjectName());
            }
            if (projectWorker.getUploadStatus()
                .equals(EProjectWorkerUploadStatus.TO_UPLOAD.getCode())) {
                throw new BizException("XN631695", "项目人员未上传,无法修改");
            }
            XN631912Req xn631612Req = new XN631912Req();
            WorkerInfo workerInfoByIdCardNumber = workerInfoBO
                .getWorkerInfoByIdCardNumber(projectWorker.getIdcardNumber());
            BeanUtils.copyProperties(projectWorker, xn631612Req);
            BeanUtils.copyProperties(workerInfoByIdCardNumber, xn631612Req);
            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(projectWorker.getProjectCode());
            xn631612Req
                .setHeadImage(workerInfoByIdCardNumber.getHeadImageUrl());
            xn631612Req.setPositiveIdCardImageUrl(
                workerInfoByIdCardNumber.getPositiveIdCardImageUrl());
            xn631612Req.setNegativeIdCardImageUrl(
                workerInfoByIdCardNumber.getNegativeIdCardImageUrl());
            xn631612Req.setProjectCode(projectConfigByLocal.getProjectCode());
            xn631612Req.setTeamSysNo(Integer.parseInt(teamMasterBO
                .getTeamMaster(projectWorker.getTeamSysNo()).getTeamSysNo()));
            xn631612Req.setUserId(req.getUserId());
            xn631612Req.setCode(code);
            projectWorkerBO.doUpdate(xn631612Req, configByLocal);
        }

    }

    public void checkDicKey(XN631693ReqData projectWorkerData) {

        EWorkerRoleType
            .checkExists(String.valueOf(projectWorkerData.getWorkRole()));
        EWorkerType.checkExists(projectWorkerData.getWorkType());
        EPoliticsType.checkExists(projectWorkerData.getPoliticsType());
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

        Paginable<ProjectWorker> page = projectWorkerBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (ProjectWorker projectWorker : page.getList()) {
                WorkerInfo workerInfo = workerInfoBO
                    .getBriefWorkerInfo(projectWorker.getWorkerCode());
                if (null != workerInfo) {
                    projectWorker
                        .setArchiveDatetime(workerInfo.getCreateDatetime());
                    projectWorker.setWorkerPicUploadStatus(workerInfo.getWorkerPicUploadStatus());
                }
            }
        }

        return page;

    }

    @Override
    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition) {
        // 项目端
        User user = userBO.getBriefUser(condition.getUserId());
        if (StringUtils.isNotBlank(condition.getUserId())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        // 查询项目端人员
        List<ProjectWorker> queryProjectWorkerList = projectWorkerBO
            .queryProjectWorkerList(condition);

        if (StringUtils.isNotBlank(condition.getDeviceKey())) {
            // 项目端-查询人员-包含已授权和未授权

            // 查询设备人员(已授权)
            EquipmentWorker tempEquipMentWorker = new EquipmentWorker();
            tempEquipMentWorker.setDeviceKey(condition.getDeviceKey());
            List<EquipmentWorker> queryEquipmentWorkerList = equipmentWorkerBO
                .queryEquipmentWorkerList(tempEquipMentWorker);
            List<ProjectWorker> hasAuthorization = new ArrayList<>();
            List<ProjectWorker> tempList = new ArrayList<>();
            for (EquipmentWorker equipmentWorker : queryEquipmentWorkerList) {
                ProjectWorker projectWorker = projectWorkerBO
                    .getProjectWorker(equipmentWorker.getWorkerCode());
                tempList.add(projectWorker);
                projectWorker.setIsLink(EIsNotType.IS.getCode());
                hasAuthorization.add(projectWorker);
            }
            // 此项目下所有人员
            List<ProjectWorker> projectWorkerByProjectCode = projectWorkerBO
                .getProjectWorkerByProjectCode(user.getOrganizationCode(),
                    EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode());
            projectWorkerByProjectCode.removeAll(tempList);

            for (ProjectWorker projectWorker : projectWorkerByProjectCode) {
                projectWorker.setIsLink(EIsNotType.NOT.getCode());
            }
            projectWorkerByProjectCode.addAll(hasAuthorization);
            return projectWorkerByProjectCode;
        }
        return queryProjectWorkerList;
    }

    @Override
    public ProjectWorker getProjectWorker(String code) {
        ProjectWorker projectWorker = projectWorkerBO.getProjectWorker(code);

        WorkerInfo workerInfo = workerInfoBO
            .getWorkerInfo(projectWorker.getWorkerCode());
        projectWorker.setWorkerInfo(workerInfo);

        return projectWorker;
    }

}
