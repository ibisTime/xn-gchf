package com.cdkj.gchf.bo.impl;

import java.text.SimpleDateFormat;
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
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectWorkerEntryExitHistoryDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631914ReqWorker;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class ProjectWorkerEntryExitHistoryBOImpl
        extends PaginableBOImpl<ProjectWorkerEntryExitHistory>
        implements IProjectWorkerEntryExitHistoryBO {

    @Autowired
    private IProjectWorkerEntryExitHistoryDAO projectWorkerEntryExitHistoryDAO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Override
    public String saveProjectWorkerEntryExitHistory(XN631730Req req) {
        ProjectWorkerEntryExitHistory data = new ProjectWorkerEntryExitHistory();
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(req.getWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631730", "员工信息不存在");
        }
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorkerEntryExitHistory.getCode());
        BeanUtils.copyProperties(projectWorker, data);
        data.setCode(code);
        data.setDate(req.getDate());
        data.setType(req.getType());
        data.setWorkerCode(projectWorker.getCode());
        data.setIdcardNumber(projectWorker.getIdcardNumber());
        data.setIdcardType(projectWorker.getIdcardType());
        data.setJoinDatetime(projectWorker.getJoinDatetime());
        data.setLeavingDatetime(projectWorker.getLeavingDatetime());
        data.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        data.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        data.setVoucherUrl(req.getVoucherUrl());
        projectWorkerEntryExitHistoryDAO.insert(data);
        return code;
    }

    @Override
    public void removeProjectWorkerEntryExitHistory(String code) {
        ProjectWorkerEntryExitHistory data = new ProjectWorkerEntryExitHistory();
        data.setCode(code);
        projectWorkerEntryExitHistoryDAO.delete(data);
    }

    @Override
    public void refreshProjectWorkerEntryExitHistory(XN631732Req req) {
        ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
        condition.setCode(req.getCode());
        BeanUtils.copyProperties(req, condition);
        if (StringUtils.isNotBlank(req.getDate())) {
            Date strToDate = DateUtil.strToDate(req.getDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            condition.setDate(strToDate);
        }
        if (StringUtils.isNotBlank(req.getType())) {
            condition.setType(Integer.parseInt(req.getType()));
        }
        projectWorkerEntryExitHistoryDAO.update(condition);
    }

    @Override
    public void updateProjectWorkerEntryExitHistoryDeleteStatus(String code,
            String status) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setCode(code);
        projectWorkerEntryExitHistory.setDeleteStatus(status);
        projectWorkerEntryExitHistoryDAO
            .updateProjectWorkerEntryHistoryDeleteStatus(
                projectWorkerEntryExitHistory);
    }

    @Override
    public void doUpload(XN631914Req req, ProjectConfig projectConfig) {

        List<XN631914ReqWorker> workerList = req.getWorkerList();
        for (XN631914ReqWorker worker : workerList) {
            worker.setIdCardNumber(AesUtils.encrypt(worker.getIdCardNumber(),
                projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("WorkerEntryExit.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> doQuery(XN631915Req req,
            ProjectConfig projectConfig) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, projectWorkerEntryExitHistory);

        String data = JSONObject.toJSON(projectWorkerEntryExitHistory)
            .toString();

        String queryString = GovConnecter.getGovData("WorkerEntryExit.Query",
            data, projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<ProjectWorkerEntryExitHistory> page = GovUtil.parseGovPage(
            req.getPageIndex(), req.getPageSize(), queryString, replaceMap,
            ProjectWorkerEntryExitHistory.class);

        return page;
    }

    @Override
    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition) {
        return projectWorkerEntryExitHistoryDAO.selectList(condition);
    }

    @Override
    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code) {
        ProjectWorkerEntryExitHistory data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
            condition.setCode(code);
            data = projectWorkerEntryExitHistoryDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "人员进退场编号不存在");
            }
        }
        return data;
    }

    @Override
    public Object queryProjectWorkerEntryExitHistory(String code) {
        ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
        condition.setCode(code);
        return projectWorkerEntryExitHistoryDAO.select(condition);
    }

    @Override
    public JsonObject getRequestJson(TeamMaster teamMaster,
            ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory,
            ProjectConfig projectConfigByLocal) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("projectCode",
            projectConfigByLocal.getProjectCode());
        jsonObject.addProperty("corpCode",
            projectWorkerEntryExitHistory.getCorpCode());
        jsonObject.addProperty("corpName",
            projectWorkerEntryExitHistory.getCorpName());
        jsonObject.addProperty("teamSysNo", teamMaster.getTeamSysNo());
        JsonArray jsonArray = new JsonArray();
        JsonObject childJson = new JsonObject();
        childJson.addProperty("idCardType",
            projectWorkerEntryExitHistory.getIdcardType());
        childJson.addProperty("idCardNumber",
            AesUtils.encrypt(projectWorkerEntryExitHistory.getIdcardNumber(),
                projectConfigByLocal.getSecret()));
        childJson.addProperty("date", new SimpleDateFormat("yyyy-MM-dd")
            .format(projectWorkerEntryExitHistory.getDate()));
        childJson.addProperty("voucher",
            projectWorkerEntryExitHistory.getVoucherUrl());
        childJson.addProperty("type", projectWorkerEntryExitHistory.getType());
        jsonArray.add(childJson);
        jsonObject.add("workerList", jsonArray);
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    @Override
    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistoryByIdCardNumber(
            String idCardNumber) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setIdcardNumber(idCardNumber);
        return projectWorkerEntryExitHistoryDAO
            .select(projectWorkerEntryExitHistory);
    }

    @Override
    public String saveProjectWorkerEntryExitHistory(
            ProjectWorkerEntryExitHistory entryExitHistory) {
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorkerEntryExitHistory.getCode());
        entryExitHistory.setCode(code);
        projectWorkerEntryExitHistoryDAO.insert(entryExitHistory);
        return code;
    }

    @Override
    public void refreshUploadStatus(String code, String status) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setCode(code);
        projectWorkerEntryExitHistory.setUploadStatus(status);
        projectWorkerEntryExitHistoryDAO.updateProjectWorkerEntryHistoryStatus(
            projectWorkerEntryExitHistory);
    }

    @Override
    public ProjectWorkerEntryExitHistory getLastTimeEntryTime(
            String workerCode) {
        ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
        condition.setWorkerCode(workerCode);
        condition.setOrder("date", false);
        List<ProjectWorkerEntryExitHistory> selectList = projectWorkerEntryExitHistoryDAO
            .selectList(condition);
        if (CollectionUtils.isEmpty(selectList)) {
            return null;
        }
        return projectWorkerEntryExitHistoryDAO.selectList(condition).get(0);
    }

    @Override
    public void fakeDeleteProjectWorkerEntryHistory(String ProjectCode,
            String teamMasterCode) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setProjectCode(ProjectCode);
        projectWorkerEntryExitHistory.setTeamSysNo(teamMasterCode);
        projectWorkerEntryExitHistory
            .setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        projectWorkerEntryExitHistoryDAO
            .updateProjectWorkerEntryHistoryDeleteStatus(
                projectWorkerEntryExitHistory);
    }

    @Override
    public void fakeDeleteProjectWorkerEntryHistory(String workerCode) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setWorkerCode(workerCode);
        projectWorkerEntryExitHistory
            .setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        projectWorkerEntryExitHistoryDAO
            .updateProjectWorkerEntryHistoryDeleteStatus(
                projectWorkerEntryExitHistory);
    }

}
