package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.api.impl.XN631693ReqData;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.QiniuUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectWorkerDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631690Req;
import com.cdkj.gchf.dto.req.XN631692Req;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631911ReqWorker;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.enums.EBankCardCodeType;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EGovErrorMessage;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EIsNotType;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectWorkerUploadStatus;
import com.cdkj.gchf.enums.ETeamMasterUploadStatus;
import com.cdkj.gchf.enums.EWorkerRoleType;
import com.cdkj.gchf.enums.EWorkerType;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class ProjectWorkerBOImpl extends PaginableBOImpl<ProjectWorker>
        implements IProjectWorkerBO {

    @Autowired
    private IProjectWorkerDAO projectWorkerDAO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String saveProjectWorker(XN631690Req data) {
        ProjectWorker projectWorkerInfo = new ProjectWorker();
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());

        Project project = projectBO.getProject(data.getProjectCode());

        projectWorkerInfo.setProjectName(project.getName());
        BeanUtils.copyProperties(data, projectWorkerInfo);

        projectWorkerInfo.setProjectName(project.getName());
        TeamMaster teamMaster = teamMasterBO
            .getTeamMaster(String.valueOf(data.getTeamSysNo()));
        projectWorkerInfo.setTeamName(teamMaster.getTeamName());

        WorkerInfo workerInfo = workerInfoBO
            .getWorkerInfo(data.getWorkerCode());
        projectWorkerInfo.setCorpName(corpBasicinfo.getCorpName());
        projectWorkerInfo.setWorkerMobile(workerInfo.getCellPhone());
        projectWorkerInfo.setIdcardType(workerInfo.getIdCardType());
        projectWorkerInfo.setIdcardNumber(workerInfo.getIdCardNumber());
        projectWorkerInfo.setWorkerCode(data.getWorkerCode());
        projectWorkerInfo.setWorkerName(workerInfo.getName());
        projectWorkerInfo.setBankName(data.getBankName());
        projectWorkerInfo
            .setUploadStatus(EProjectWorkerUploadStatus.TO_UPLOAD.getCode());
        projectWorkerInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        projectWorkerInfo.setLocalTeamSysNo(teamMaster.getCode());

        if (StringUtils.isNotBlank(data.getIsTeamLeader())) {
            EIsNotType.checkExists(data.getIsTeamLeader());
            projectWorkerInfo
                .setIsTeamLeader(Integer.parseInt(data.getIsTeamLeader()));
        }
        if (StringUtils.isNotBlank(data.getWorkType())) {
            EWorkerType.checkExists(data.getWorkType());
            projectWorkerInfo.setWorkType(data.getWorkType());
        }
        if (StringUtils.isNotBlank(data.getWorkRole())) {
            EWorkerRoleType.checkExists(data.getWorkRole());
            projectWorkerInfo.setWorkRole(Integer.parseInt(data.getWorkRole()));
        }
        if (StringUtils.isNotBlank(data.getPayRollTopBankCode())) {
            EBankCardCodeType.checkExists(data.getPayRollTopBankCode());
            projectWorkerInfo
                .setPayRollTopBankCode(data.getPayRollTopBankCode());
        }
        if (StringUtils.isNotBlank(data.getHasBuyInsurance())) {
            EIsNotType.checkExists(data.getHasBuyInsurance());
            projectWorkerInfo.setHasBuyInsurance(
                Integer.parseInt(data.getHasBuyInsurance()));
        }
        if (StringUtils.isNotBlank(data.getIssueCardDate())) {
            Date issueCardDate = DateUtil.strToDate(data.getIssueCardDate(),
                DateUtil.DATA_TIME_PATTERN_1);
            projectWorkerInfo.setIssueCardDate(issueCardDate);
        }
        if (StringUtils.isNotBlank(data.getWorkDate())) {
            Date workDate = DateUtil.strToDate(data.getWorkDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            projectWorkerInfo.setJoinDatetime(workDate);
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorker.getCode());
        projectWorkerInfo.setCode(code);

        projectWorkerDAO.insert(projectWorkerInfo);
        return code;
    }

    @Override
    public String saveProjectWorker(ProjectWorker projectWorker) {
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorker.getCode());
        projectWorker.setCode(code);
        projectWorkerDAO.insert(projectWorker);
        return code;
    }

    @Override
    public String saveProjectWorker(WorkerInfo workerInfo, XN631693ReqData req,
            Project project, TeamMaster teamMaster, CorpBasicinfo corpBasic) {
        ProjectWorker projectWorker = new ProjectWorker();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorker.getCode());
        projectWorker.setCode(code);
        BeanUtils.copyProperties(req, projectWorker);
        projectWorker.setProjectCode(project.getCode());
        projectWorker.setProjectName(project.getName());
        if (StringUtils.isNotBlank(req.getHasBuyInsurance())) {
            projectWorker
                .setHasBuyInsurance(Integer.parseInt(req.getHasBuyInsurance()));
        }
        projectWorker.setTeamSysNo(teamMaster.getCode());
        projectWorker.setCorpName(corpBasic.getCorpName());
        projectWorker.setWorkerCode(workerInfo.getCode());
        projectWorker.setCellPhone(workerInfo.getCellPhone());
        projectWorker.setWorkerName(workerInfo.getName());
        projectWorker
            .setUploadStatus(EProjectWorkerUploadStatus.TO_UPLOAD.getCode());
        projectWorker.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        projectWorker.setIsTeamLeader(Integer.parseInt(req.getIsTeamLeader()));
        projectWorker.setIdcardNumber(req.getIdCardNumber());
        projectWorker.setIdcardType("01");
        projectWorkerDAO.insert(projectWorker);
        return code;
    }

    @Override
    public String saveProjectWorker(String workerCode, String workerName,
            String idcardNumber, Project project) {
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(workerCode);

        ProjectWorker projectWorker = new ProjectWorker();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorker.getCode());
        projectWorker.setCode(code);
        projectWorker.setProjectCode(project.getCode());
        projectWorker.setProjectName(project.getName());
        projectWorker.setWorkerCode(workerCode);

        projectWorker.setWorkerName(workerName);
        projectWorker.setIdcardType(EIdCardType.JUMIN.getCode());
        projectWorker.setIdcardNumber(idcardNumber);
        projectWorker.setCellPhone(workerInfo.getCellPhone());
        projectWorker
            .setUploadStatus(EProjectWorkerUploadStatus.TO_UPLOAD.getCode());
        projectWorker.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        projectWorkerDAO.insert(projectWorker);

        return code;
    }

    @Override
    public String saveProjectWorker(String workerCode, Project project,
            CorpBasicinfo corpBasicinfo, TeamMaster teamMaster,
            XN631693ReqData req) {
        ProjectWorker projectWorker = new ProjectWorker();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorker.getCode());
        projectWorker.setCode(code);
        projectWorker.setWorkerCode(workerCode);
        projectWorker.setCorpCode(corpBasicinfo.getCorpCode());
        projectWorker.setCorpName(corpBasicinfo.getCorpName());
        projectWorker.setTeamName(req.getTeamName());
        projectWorker.setProjectName(project.getName());
        projectWorker.setProjectCode(project.getCode());
        projectWorker.setTeamSysNo(teamMaster.getCode());
        projectWorker.setIdcardNumber(req.getIdCardNumber());
        projectWorker.setCellPhone(req.getCellPhone());
        projectWorker.setWorkerName(req.getWorkerName());
        projectWorker.setIdcardType("01");
        projectWorker.setWorkType(req.getWorkType());
        projectWorker.setWorkRole(req.getWorkRole());

        if (StringUtils.isNotBlank(req.getIsTeamLeader())) {
            projectWorker
                .setIsTeamLeader(Integer.parseInt(req.getIsTeamLeader()));
        }

        if (StringUtils.isNotBlank(req.getHasBuyInsurance())) {
            projectWorker
                .setHasBuyInsurance(Integer.parseInt(req.getHasBuyInsurance()));
        }
        projectWorker
            .setUploadStatus(EProjectWorkerUploadStatus.TO_UPLOAD.getCode());
        projectWorker.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        projectWorkerDAO.insert(projectWorker);
        return code;
    }

    @Override
    public void removeProjectWorker(String code) {
        ProjectWorker data = new ProjectWorker();
        data.setCode(code);
        data.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        projectWorkerDAO.updateProjectWorkerDeleteStatus(data);
    }

    @Override
    public void updateProjectWorkerDeleteStatus(String code, String status) {
        ProjectWorker projectWorker = new ProjectWorker();
        projectWorker.setDeleteStatus(status);
        projectWorker.setCode(code);
        projectWorkerDAO.updateProjectWorkerDeleteStatus(projectWorker);
    }

    @Override
    public void refreshProjectWorker(XN631692Req req, TeamMaster teamMaster) {
        ProjectWorker projectWorkerInfo = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorkerInfo);
        projectWorkerInfo.setTeamName(teamMaster.getTeamName());
        projectWorkerInfo.setCorpCode(teamMaster.getCorpCode());
        projectWorkerInfo.setCorpName(teamMaster.getCorpName());
        if (StringUtils.isNotBlank(req.getIsTeamLeader())) {
            projectWorkerInfo
                .setIsTeamLeader(Integer.parseInt(req.getIsTeamLeader()));
        }
        if (StringUtils.isNotBlank(req.getWorkRole())) {
            projectWorkerInfo.setWorkRole(Integer.parseInt(req.getWorkRole()));
        }
        if (StringUtils.isNotBlank(req.getHasBuyInsurance())) {
            projectWorkerInfo
                .setHasBuyInsurance(Integer.parseInt(req.getHasBuyInsurance()));
        }
        if (StringUtils.isNotBlank(req.getBankName())) {
            projectWorkerInfo.setBankName(req.getBankName());
        }
        if (StringUtils.isNotBlank(req.getIssueCardDate())) {
            Date issueCardDate = DateUtil.strToDate(req.getIssueCardDate(),
                DateUtil.DATA_TIME_PATTERN_1);
            projectWorkerInfo.setIssueCardDate(issueCardDate);
        }
        if (StringUtils.isNotBlank(req.getWorkDate())) {
            Date workDate = DateUtil.strToDate(req.getWorkDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            projectWorkerInfo.setWorkDate(workDate);
        }

        projectWorkerDAO.update(projectWorkerInfo);
    }

    /**
     * @Description: 更新项目人员信息
     */
    @Override
    public void refreshWorkerIdCardNumber(String workerCode,
            String newIdCardNumber, String workerName) {
        ProjectWorker condition = new ProjectWorker();
        condition.setWorkerCode(workerCode);
        condition.setIdcardNumber(newIdCardNumber);
        condition.setWorkerName(workerName);
        condition.setIdcardType("01");
        projectWorkerDAO.updateProjectWorkerWorkerInfo(condition);
    }

    @Override
    public void refreshWorkerCelephone(String workerCode, String phone) {
        ProjectWorker condition = new ProjectWorker();
        condition.setWorkerCode(workerCode);
        condition.setCellPhone(phone);
        projectWorkerDAO.updateProjectWorkerWorkerPhone(condition);
    }

    /**
     * <p>Title: refreshProjectWorkerTeamName</p>   
     * <p>Description: 向下刷新班组名称</p>   
     */
    @Override
    public void refreshProjectWorkerTeamName(String teamNO, String teamName) {
        ProjectWorker condition = new ProjectWorker();
        condition.setTeamSysNo(teamNO);
        condition.setTeamName(teamName);
        projectWorkerDAO.updateProjectWorkerTeamName(condition);
    }

    /**
     * 
     * <p>Title: refreshIdCardInfo</p>   
     * <p>Description: 重新建档后-更新已存在的项目人员信息</p>   
     */
    @Override
    public void refreshIdCardInfo(String oldIdCardNum, String newIdCardNum,
            String name) {
        // 项目人员

        // 项目人员进退场

        //
    }

    @Override
    public void refreshUploadStatus(String code, String status) {
        ProjectWorker projectWorker = new ProjectWorker();
        projectWorker.setCode(code);
        projectWorker.setUploadStatus(status);
        projectWorkerDAO.updateStatus(projectWorker);
    }

    @Override
    public void doUpload(XN631911Req req, ProjectConfig projectConfig) {

        List<XN631911ReqWorker> workerList = req.getWorkerList();
        for (XN631911ReqWorker worker : workerList) {
            worker.setIdCardNumber(AesUtils.encrypt(worker.getIdCardNumber(),
                projectConfig.getSecret()));

            if (StringUtils.isNotBlank(worker.getPayRollBankCardNumber())) {
                worker.setPayRollBankCardNumber(
                    AesUtils.encrypt(worker.getPayRollBankCardNumber(),
                        projectConfig.getSecret()));
            }
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("ProjectWorker.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    /**
     * 
     * <p>Title: doUpdate</p>   
     * <p>Description: 国家平台修改接口</p>   
     */
    @Override
    public void doUpdate(XN631912Req req, ProjectConfig projectConfig) {
        User user = userBO.getBriefUser(req.getUserId());
        req.setIdCardNumber(
            AesUtils.encrypt(req.getIdCardNumber(), projectConfig.getSecret()));

        if (StringUtils.isNotBlank(req.getPayRollBankCardNumber())) {
            req.setPayRollBankCardNumber(AesUtils.encrypt(
                req.getPayRollBankCardNumber(), projectConfig.getSecret()));
        }
        req.setHeadImage(req.getHeadImage().replace("data:image/bmp;base64,",
            "data:image/png;base64,"));
        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = null;
        try {
            resString = GovConnecter.getGovData("ProjectWorker.Update", data,
                projectConfig.getProjectCode(), projectConfig.getSecret());
        } catch (BizException e) {
            e.printStackTrace();
            updateProjectWorkerStatus(req.getCode(),
                ETeamMasterUploadStatus.UPLOAD_UNUPDATE.getCode());
            throw e;
        }

        String operateLog = operateLogBO.saveOperateLog(
            EOperateLogRefType.ProjectWorker.getCode(), req.getCode(),
            "修改平台项目人员", user, "修改平台项目人员");
        AsyncQueueHolder.addSerial(resString, projectConfig, "projectWorkerBO",
            req.getCode(), ETeamMasterUploadStatus.UPLOAD_UPDATE.getCode(),
            operateLog, req.getUserId());

    }

    @Override
    public Paginable<ProjectWorker> doQuery(XN631913Req req,
            ProjectConfig projectConfig) {
        ProjectWorker projectWorker = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorker);

        if (StringUtils.isNotBlank(req.getIdCardNumber())) {
            projectWorker.setIdcardNumber(AesUtils
                .encrypt(req.getIdCardNumber(), projectConfig.getSecret()));
        }

        String data = JSONObject.toJSON(projectWorker).toString();

        String queryString = GovConnecter.getGovData("ProjectWorker.Query",
            data, projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<ProjectWorker> page = GovUtil.parseGovPage(req.getPageIndex(),
            req.getPageSize(), queryString, replaceMap, ProjectWorker.class);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (ProjectWorker worker : page.getList()) {
                worker.setIdcardNumber(AesUtils.decrypt(
                    worker.getIdcardNumber(), projectConfig.getSecret()));
            }
        }

        return page;
    }

    @Override
    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition) {
        return projectWorkerDAO.selectList(condition);
    }

    @Override
    public List<ProjectWorker> queryProjectWorkerList(String teamMasterNo) {
        ProjectWorker projectWorker = new ProjectWorker();

        projectWorker.setTeamSysNo(teamMasterNo);

        return projectWorkerDAO.selectList(projectWorker);
    }

    @Override
    public List<ProjectWorker> queryProjectWorkerListByProject(
            String projectCode) {
        ProjectWorker projectWorker = new ProjectWorker();
        projectWorker.setProjectCode(projectCode);
        return projectWorkerDAO.selectList(projectWorker);
    }

    @Override
    public List<ProjectWorker> queryProjectWorkerList(String projectCode,
            String idcardNumber) {
        ProjectWorker condition = new ProjectWorker();

        condition.setProjectCode(projectCode);
        condition.setIdcardNumber(idcardNumber);

        return projectWorkerDAO.selectList(condition);
    }

    @Override
    public ProjectWorker getProjectWorker(String code) {
        ProjectWorker data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectWorker condition = new ProjectWorker();
            condition.setCode(code);
            data = projectWorkerDAO.select(condition);
        }
        return data;
    }

    @Override
    public ProjectWorker getProjectWorkerByProjectCode(String code) {
        ProjectWorker condition = new ProjectWorker();
        condition.setProjectCode(code);
        return projectWorkerDAO.select(condition);
    }

    @Override
    public ProjectWorker getProjectWorker(String projectCode, String corpCode,
            String teamSysNo, String idcardNumber) {
        ProjectWorker condition = new ProjectWorker();

        condition.setProjectCode(projectCode);
        condition.setCorpCode(corpCode);
        condition.setTeamSysNo(teamSysNo);
        condition.setIdcardNumber(idcardNumber);
        condition.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        return projectWorkerDAO.select(condition);
    }

    @Override
    public int updateProjectWorkerStatus(String code, String status) {
        ProjectWorker projectWorker = new ProjectWorker();
        projectWorker.setCode(code);
        projectWorker.setUploadStatus(status);
        return projectWorkerDAO.updateProjectWorkerUploadStatus(projectWorker);
    }

    @Override
    public List<ProjectWorker> getProjectWorker(String projectCode,
            String idCardNumber) {
        ProjectWorker condition = new ProjectWorker();
        condition.setProjectCode(projectCode);
        condition.setIdcardNumber(idCardNumber);
        return projectWorkerDAO.selectList(condition);

    }

    @Override
    public ProjectWorker getProjectWorkerByIdentity(String teamSysNo,
            String idCardNumber) {
        ProjectWorker projectWorker = new ProjectWorker();
        projectWorker.setTeamSysNo(teamSysNo);
        projectWorker.setIdcardType("01");
        projectWorker.setIdcardNumber(idCardNumber);
        projectWorker.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        ProjectWorker infoByCondition = projectWorkerDAO.select(projectWorker);
        return infoByCondition;
    }

    @Override
    public JsonObject getProjectWorkerJson(TeamMaster teamMaster,
            ProjectWorker projectWorker, ProjectConfig projectConfig) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        WorkerInfo infoByIdCardNumber = workerInfoBO
            .getWorkerInfoByIdCardNumber(projectWorker.getIdcardNumber());
        if (StringUtils.isBlank(infoByIdCardNumber.getCellPhone())) {
            throw new BizException("XN631694", "人员电话号码不完整，请重新建档补充信息");
        }
        if (StringUtils.isBlank(infoByIdCardNumber.getHeadImageUrl())) {
            throw new BizException("xn631694",
                EGovErrorMessage.WorkerList.getLocalMessage());
        }
        if (StringUtils
            .isBlank(infoByIdCardNumber.getPositiveIdCardImageUrl())) {
            throw new BizException("xn631694", "员工身份证正面照信息无效,请重新建档补充信息");
        }
        if (StringUtils
            .isBlank(infoByIdCardNumber.getNegativeIdCardImageUrl())) {
            throw new BizException("xn631694", "员工身份证反面照信息无效,请重新建档补充信息");
        }

        projectWorker.setTeamSysNo(teamMaster.getTeamSysNo());
        projectWorker.setProjectCode(projectConfig.getProjectCode());
        jsonObject.addProperty("projectCode", projectWorker.getProjectCode());
        jsonObject.addProperty("corpCode", projectWorker.getCorpCode());
        jsonObject.addProperty("corpName", projectWorker.getCorpName());
        jsonObject.addProperty("teamSysNo", teamMaster.getTeamSysNo());
        jsonObject.addProperty("teamName", teamMaster.getTeamName());
        JsonObject workerList = new JsonObject();
        workerList.addProperty("workerName", projectWorker.getWorkerName());
        workerList.addProperty("isTeamLeader", projectWorker.getIsTeamLeader());
        workerList.addProperty("idCardType", projectWorker.getIdcardType());

        if (StringUtils.isNotBlank(projectWorker.getIdcardNumber())) {
            workerList.addProperty("idCardNumber", AesUtils.encrypt(
                projectWorker.getIdcardNumber(), projectConfig.getSecret()));
        }

        workerList.addProperty("workType", projectWorker.getWorkType());
        workerList.addProperty("workRole", projectWorker.getWorkRole());
        workerList.addProperty("issueCardDate",
            DateUtil.dateToStr(projectWorker.getIssueCardDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        workerList.addProperty("issueCardPic",
            projectWorker.getIssueCardPicUrl());
        workerList.addProperty("cardNumber", projectWorker.getCardNumber());

        if (StringUtils.isNotBlank(projectWorker.getPayRollBankCardNumber())) {
            workerList.addProperty("payRollBankCardNumber",
                AesUtils.encrypt(projectWorker.getPayRollBankCardNumber(),
                    projectConfig.getSecret()));
        }

        workerList.addProperty("payRollBankName",
            projectWorker.getPayRollBankName());
        workerList.addProperty("bankLinkNumber",
            projectWorker.getBankLinkNumber());
        workerList.addProperty("payRollTopBankCode",
            projectWorker.getPayRollTopBankCode());
        workerList.addProperty("hasBuyInsurance",
            projectWorker.getHasBuyInsurance());
        workerList.addProperty("nation", infoByIdCardNumber.getNation());
        workerList.addProperty("address", infoByIdCardNumber.getAddress());

        if (StringUtils.isNotBlank(infoByIdCardNumber.getHeadImageUrl())) {
            workerList.addProperty("headImage",
                infoByIdCardNumber.getHeadImageUrl().replace(
                    "data:image/bmp;base64,", "data:image/png;base64,"));
        }

        workerList.addProperty("politicsType",
            infoByIdCardNumber.getPoliticsType());
        workerList.addProperty("joinedTime",
            DateUtil.dateToStr(projectWorker.getJoinDatetime(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        workerList.addProperty("cellPhone", infoByIdCardNumber.getCellPhone());
        workerList.addProperty("cultureLevelType",
            infoByIdCardNumber.getCultureLevelType());
        workerList.addProperty("Specialty", infoByIdCardNumber.getSpecialty());
        workerList.addProperty("hasBadMedicalHistory",
            projectWorker.getHasBadMedicalHistory());
        workerList.addProperty("urgentLinkMan",
            infoByIdCardNumber.getUrgentLinkMan());
        workerList.addProperty("urgentLinkManPhone",
            infoByIdCardNumber.getUrgentLinkManPhone());
        if (projectWorker.getWorkDate() != null) {
            workerList.addProperty("workDate",
                DateUtil.dateToStr(projectWorker.getWorkDate(),
                    DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        workerList.addProperty("maritalStatus",
            infoByIdCardNumber.getMaritalStatus());
        workerList.addProperty("grantOrg", infoByIdCardNumber.getGrantOrg());
        workerList.addProperty("positiveIDCardImage",
            QiniuUtil.parseUrl(infoByIdCardNumber.getPositiveIdCardImageUrl()));
        workerList.addProperty("negativeIDCardImage",
            QiniuUtil.parseUrl(infoByIdCardNumber.getNegativeIdCardImageUrl()));
        workerList.addProperty("startDate", projectWorker.getStartDate());
        workerList.addProperty("expiryDate", projectWorker.getExpiryDate());
        jsonArray.add(workerList);
        jsonObject.add("workerList", jsonArray);
        return jsonObject;
    }

    @Override
    public void fakeDeleteProjectWorker(String projectcode) {
        ProjectWorker projectWorker = new ProjectWorker();
        projectWorker
            .setUploadStatus(EProjectWorkerUploadStatus.TO_UPLOAD.getCode());
        projectWorker.setProjectCode(projectcode);
        projectWorker.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        projectWorkerDAO.updateProjectWorkerDeleteStatus(projectWorker);
    }

    @Override
    public void fakeDeleteProjectWorkerByTeamNo(String projectCode,
            String teamMasterNo) {
        ProjectWorker projectWorker = new ProjectWorker();
        projectWorker.setProjectCode(projectCode);
        projectWorker.setTeamSysNo(teamMasterNo);
        projectWorker
            .setUploadStatus(EProjectWorkerUploadStatus.TO_UPLOAD.getCode());
        projectWorkerDAO.updateStatus(projectWorker);
    }

}
