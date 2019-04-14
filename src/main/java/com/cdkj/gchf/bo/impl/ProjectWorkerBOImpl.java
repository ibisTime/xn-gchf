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
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
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
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EIsNotType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.enums.EWorkerRoleType;
import com.cdkj.gchf.enums.EWorkerType;
import com.cdkj.gchf.exception.BizException;
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
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Override
    public String saveProjectWorker(XN631690Req data) {
        ProjectWorker projectWorkerInfo = new ProjectWorker();
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());
        projectWorkerInfo.setProjectName(corpBasicinfo.getCorpName());
        BeanUtils.copyProperties(data, projectWorkerInfo);

        ProjectConfig configByLocal = projectConfigBO
            .getProjectConfigByLocal(data.getProjectCode());
        if (configByLocal != null) {
            projectWorkerInfo.setProjectName(configByLocal.getProjectName());
        }
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
        projectWorkerInfo.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
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
                DateUtil.FRONT_DATE_FORMAT_STRING);
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
        // 回写workerinfo

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
    public String saveProjectWorker(String workerCode, String workerName,
            String idcardNumber, Project project) {

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
        projectWorker.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        projectWorker.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        projectWorkerDAO.insert(projectWorker);

        return code;
    }

    @Override
    public void removeProjectWorker(String code) {
        ProjectWorker data = new ProjectWorker();
        data.setCode(code);
        data.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        // projectWorkerDAO.delete(data);
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
    public void refreshProjectWorker(XN631692Req req) {
        ProjectWorker projectWorkerInfo = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorkerInfo);
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
        if (StringUtils.isNotBlank(req.getIssueCardDate())) {
            Date issueCardDate = DateUtil.strToDate(req.getIssueCardDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            projectWorkerInfo.setIssueCardDate(issueCardDate);
        }
        if (StringUtils.isNotBlank(req.getWorkDate())) {
            Date workDate = DateUtil.strToDate(req.getWorkDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            projectWorkerInfo.setJoinDatetime(workDate);
        }

        projectWorkerDAO.update(projectWorkerInfo);
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

        req.setIdCardNumber(
            AesUtils.encrypt(req.getIdCardNumber(), projectConfig.getSecret()));

        if (StringUtils.isNotBlank(req.getPayRollBankCardNumber())) {
            req.setPayRollBankCardNumber(AesUtils.encrypt(
                req.getPayRollBankCardNumber(), projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("ProjectWorker.Update", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
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
                // worker.setWorkerRole(Integer.parseInt(worker.getWorkerRole()));
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

        return projectWorkerDAO.select(condition);
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
    public List<ProjectWorker> getProjectWorkerByIdentity(String teamSysNo,
            String idCardType, String idCardNumber) {
        ProjectWorker projectWorker = new ProjectWorker();
        projectWorker.setTeamSysNo(teamSysNo);
        projectWorker.setIdcardNumber(idCardNumber);
        projectWorker.setIdcardType(idCardType);
        List<ProjectWorker> infoByCondition = projectWorkerDAO
            .selectList(projectWorker);
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
        workerList.addProperty("idCardNumber", AesUtils.encrypt(
            projectWorker.getIdcardNumber(), projectConfig.getSecret()));
        workerList.addProperty("workType", projectWorker.getWorkType());
        workerList.addProperty("workRole", projectWorker.getWorkRole());
        workerList.addProperty("issueCardDate",
            DateUtil.dateToStr(projectWorker.getIssueCardDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        workerList.addProperty("issueCardPic",
            projectWorker.getIssueCardPicUrl());
        workerList.addProperty("cardNumber", projectWorker.getCardNumber());
        workerList.addProperty("payRollBankCardNumber",
            AesUtils.encrypt(projectWorker.getPayRollBankCardNumber(),
                projectConfig.getSecret()));
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
        workerList.addProperty("headImage", infoByIdCardNumber.getHeadImageUrl()
            .replace("data:image/bmp;base64,", "data:image/png;base64,"));
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
        projectWorker.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
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
        projectWorker.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        projectWorkerDAO.updateStatus(projectWorker);
    }

}
