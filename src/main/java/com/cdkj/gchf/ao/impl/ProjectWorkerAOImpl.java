package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.impl.XN631693ReqData;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631690Req;
import com.cdkj.gchf.dto.req.XN631692Req;
import com.cdkj.gchf.dto.req.XN631693Req;
import com.cdkj.gchf.dto.req.XN631694Req;
import com.cdkj.gchf.dto.req.XN631694ReqData;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.enums.EWorkerType;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.google.gson.JsonArray;
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
        if (projectWorkerBO.getProjectWorker(code).getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631691", "班组人员已上传,无法删除");
        }
        projectWorkerBO.updateProjectWorkerDeleteStatus(code,
            EDeleteStatus.DELETED.getCode());
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

    @Transactional
    @Override
    public void importProjectWorkers(XN631693Req req) {
        ProjectConfig configByLocal = projectConfigBO
            .getProjectConfigByLocal(req.getProjectCode());
        if (configByLocal == null) {
            throw new BizException("XN631693", "项目不存在");
        }
        // 导入项目人员 先根据证件查询人员实名制表信息 如果表中没有这个数据 添加到实名制信息表中 否则判断是否添加过该员工 再添加 没添加再添加
        String projectcode = req.getProjectCode();
        List<XN631693ReqData> workerList = req.getWorkerList();
        List<String> errorCode = new ArrayList<>();
        for (XN631693ReqData projectWorkerData : workerList) {
            // 数据字典key校验
            projectWorkerBO.checkDicKeyRequest(projectWorkerData);
            ProjectWorker projectWorkerByIdCardNumber = projectWorkerBO
                .getProjectWorkerByIdentity(projectWorkerData.getIdCardType(),
                    projectWorkerData.getIdCardNumber());
            // 校验是否添加过该员工
            if (projectWorkerByIdCardNumber != null) {
                errorCode.add("员工信息已添加:" + projectWorkerData.getIdCardNumber());
                continue;
            }
            ProjectWorker projectWorker = new ProjectWorker();
            BeanUtils.copyProperties(projectWorkerData, projectWorker);
            projectWorker.setProjectCode(projectcode);
            CorpBasicinfo corpBasicinfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(projectWorkerData.getCorpCode());
            if (corpBasicinfo == null) {
                errorCode.add("企业基本信息未录入");
                continue;
            }
            TeamMaster teamMaster = teamMasterBO
                .getTeamMasterByProjectCodeAndTeamMasterNameAndCorpCode(
                    projectcode, projectWorkerData.getTeamName(),
                    projectWorkerData.getCorpCode());
            if (teamMaster == null) {
                errorCode.add("项目人员所在班组信息不存在");
                continue;
            }
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
                projectWorker.setProjectName(configByLocal.getProjectName());
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
        if (CollectionUtils.isNotEmpty(errorCode)) {
            throw new BizException("XN631793", errorCode.toString());
        }
    }

    @Override
    public void uploadProjectWorker(XN631694Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        List<String> codeList = req.getCodeList();
        List<String> errorCode = new ArrayList<>();
        for (String code : codeList) {
            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(code);
            if (projectWorker.getUploadStatus()
                .equals(EUploadStatus.UPLOAD_EDITABLE.getCode())) {
                errorCode.add("项目人员已上传" + projectWorker.getIdcardNumber());
                continue;
            }
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(projectWorker.getTeamSysNo());
            if (teamMaster == null) {
                errorCode
                    .add("第" + (codeList.indexOf(code)) + 1 + "行" + "班组信息未上传");
                continue;
            }

            projectWorker.setTeamSysNo(teamMaster.getTeamSysNo());
            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(projectWorker.getProjectCode());
            projectWorker.setProjectCode(projectConfig.getProjectCode());
            projectWorker.setTeamSysNo(teamMaster.getTeamSysNo());
            XN631694ReqData xn631694ReqData = new XN631694ReqData();
            xn631694ReqData.setTeamMasterCode(teamMaster.getTeamSysNo());
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("projectCode",
                projectWorker.getProjectCode());
            jsonObject.addProperty("corpCode", projectWorker.getCorpCode());
            jsonObject.addProperty("corpName", projectWorker.getCorpName());
            jsonObject.addProperty("teamSysNo", teamMaster.getTeamSysNo());
            jsonObject.addProperty("teamName", teamMaster.getTeamName());
            JsonArray jsonArray = new JsonArray();
            JsonObject projectWorkerJson = projectWorkerBO
                .getProjectWorkerJson(projectWorker, projectConfig);
            jsonArray.add(projectWorkerJson);
            jsonObject.add("workerList", jsonArray);
            System.out.println(jsonObject.toString());
            String resString = GovConnecter.getGovData("ProjectWorker.Add",
                jsonObject.toString(), projectConfig.getProjectCode(),
                projectConfig.getSecret());

            String logCode = operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectWorker.getCode(), code,
                EOperateLogOperate.UploadProjectWorker.getValue(), user, null);

            AsyncQueueHolder.addSerial(resString, projectConfig,
                "projectWorkerBO", code,
                EUploadStatus.UPLOAD_EDITABLE.getValue(), logCode);
        }
        if (CollectionUtils.isNotEmpty(errorCode)) {
            throw new BizException("XN631694", errorCode.toString());
        }

    }

}
