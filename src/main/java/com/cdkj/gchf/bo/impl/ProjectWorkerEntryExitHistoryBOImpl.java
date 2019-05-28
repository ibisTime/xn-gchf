package com.cdkj.gchf.bo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.cdkj.gchf.bo.IOperateLogBO;
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
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631733ReqData;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631914ReqWorker;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectWorkerEntryExitUploadStatus;
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

    @Autowired
    private IOperateLogBO operateLogBO;

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
        data.setDate(DateUtil.strToDate(req.getDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setType(Integer.parseInt(req.getType()));
        data.setWorkerCode(projectWorker.getCode());
        data.setIdcardNumber(projectWorker.getIdcardNumber());
        data.setIdcardType(projectWorker.getIdcardType());
        data.setUploadStatus(
            EProjectWorkerEntryExitUploadStatus.TO_UPLOAD.getCode());
        data.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        data.setVoucherUrl(req.getVoucherUrl());
        projectWorkerEntryExitHistoryDAO.insert(data);
        return code;
    }

    @Override
    public String saveProjectWorkerEntryExitHistory(TeamMaster teamMaster,
            ProjectWorker projectWorker, XN631733ReqData data) {

        String code = null;
        ProjectWorkerEntryExitHistory entryExitHistory = new ProjectWorkerEntryExitHistory();

        entryExitHistory.setProjectCode(teamMaster.getProjectCode());
        entryExitHistory.setCorpCode(teamMaster.getCorpCode());
        entryExitHistory.setCorpName(teamMaster.getCorpName());
        entryExitHistory.setWorkerCode(projectWorker.getCode());
        entryExitHistory.setWorkerName(projectWorker.getWorkerName());

        entryExitHistory.setPosition(projectWorker.getWorkType());
        entryExitHistory.setJoinDatetime(projectWorker.getJoinedTime());
        entryExitHistory.setIdcardType("01");
        entryExitHistory.setIdcardNumber(data.getIdcardNumber());

        entryExitHistory.setDate(DateUtil.strToDate(data.getDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        entryExitHistory.setType(Integer.parseInt(data.getType()));
        entryExitHistory.setUploadStatus(
            EProjectWorkerEntryExitUploadStatus.TO_UPLOAD.getCode());
        entryExitHistory.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorkerEntryExitHistory.getCode());
        entryExitHistory.setCode(code);
        entryExitHistory.setTeamSysNo(teamMaster.getCode());

        projectWorkerEntryExitHistoryDAO.insert(entryExitHistory);

        return code;
    }

    @Override
    public void batchInsertWorkerEntryExitHistory(User user,
            List<XN631733ReqData> dataList, List<TeamMaster> teamMasterList,
            List<ProjectWorker> projectWorkerList) {
        List<ProjectWorkerEntryExitHistory> projectWorkerEntryExitHistories = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            XN631733ReqData data = dataList.get(i);
            TeamMaster teamMaster = teamMasterList.get(i);
            ProjectWorker projectWorker = projectWorkerList.get(i);

            String code;
            ProjectWorkerEntryExitHistory entryExitHistory = new ProjectWorkerEntryExitHistory();

            entryExitHistory.setProjectCode(teamMaster.getProjectCode());
            entryExitHistory.setCorpCode(teamMaster.getCorpCode());
            entryExitHistory.setCorpName(teamMaster.getCorpName());
            entryExitHistory.setWorkerCode(projectWorker.getCode());
            entryExitHistory.setWorkerName(projectWorker.getWorkerName());

            entryExitHistory.setPosition(projectWorker.getWorkType());
            entryExitHistory.setJoinDatetime(projectWorker.getJoinedTime());
            entryExitHistory.setIdcardType("01");
            entryExitHistory.setIdcardNumber(data.getIdcardNumber());

            entryExitHistory.setDate(DateUtil.strToDate(data.getDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
            entryExitHistory.setType(Integer.parseInt(data.getType()));
            entryExitHistory.setUploadStatus(
                EProjectWorkerEntryExitUploadStatus.TO_UPLOAD.getCode());
            entryExitHistory.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

            code = OrderNoGenerater.generate(
                EGeneratePrefix.ProjectWorkerEntryExitHistory.getCode());
            entryExitHistory.setCode(code);
            entryExitHistory.setTeamSysNo(teamMaster.getCode());
            projectWorkerEntryExitHistories.add(entryExitHistory);
            operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code,
                EOperateLogOperate.IMPORT_WORKER_ENTRYEXIT.getValue(), user,
                null);
            // 回写人员进场场信息
            projectWorkerBO.refreshStatus(projectWorker.getCode(),
                data.getType());

        }
        projectWorkerEntryExitHistoryDAO
            .batchInsert(projectWorkerEntryExitHistories);
    }

    @Override
    public void fakeDeleteProjectWorkerEntryHistoryByProject(
            String projectCode) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setProjectCode(projectCode);
        projectWorkerEntryExitHistory.setUploadStatus(
            EProjectWorkerEntryExitUploadStatus.TO_UPLOAD.getCode());
        projectWorkerEntryExitHistory
            .setDeleteStatus(EDeleteStatus.DELETED.getCode());
        projectWorkerEntryExitHistoryDAO
            .updateProjectWorkerEntryHistoryDeleteStatus(
                projectWorkerEntryExitHistory);
    }

    @Override
    public void fakeDeleteProjectWorkerEntryHistory(String workerCode) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setWorkerCode(workerCode);
        projectWorkerEntryExitHistory
            .setDeleteStatus(EDeleteStatus.DELETED.getCode());
        projectWorkerEntryExitHistory.setUploadStatus(
            EProjectWorkerEntryExitUploadStatus.TO_UPLOAD.getCode());
        projectWorkerEntryExitHistoryDAO
            .updateProjectWorkerEntryHistoryDeleteStatus(
                projectWorkerEntryExitHistory);
    }

    @Override
    public void fakeDeleteProjectWorkerEntryHistoryByTeamMaster(
            String teamMasterNo) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setTeamSysNo(teamMasterNo);
        projectWorkerEntryExitHistory.setUploadStatus(
            EProjectWorkerEntryExitUploadStatus.TO_UPLOAD.getCode());
        projectWorkerEntryExitHistory
            .setDeleteStatus(EDeleteStatus.DELETED.getCode());
        projectWorkerEntryExitHistoryDAO
            .updateProjectWorkerEntryHistoryDeleteStatus(
                projectWorkerEntryExitHistory);
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
    public void refreshUploadStatus(String code, String status) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        projectWorkerEntryExitHistory.setCode(code);
        projectWorkerEntryExitHistory.setUploadStatus(status);
        projectWorkerEntryExitHistoryDAO.updateProjectWorkerEntryHistoryStatus(
            projectWorkerEntryExitHistory);
    }

    @Override
    public Integer selectProjectWorkerLeavingCount(String userId) {
        return projectWorkerEntryExitHistoryDAO
            .selectProjectWorkerEntryHistoryLeavingCount30Day(userId);
    }

    @Override
    public Integer selectProjectWorkerComingCount(String userId) {
        return projectWorkerEntryExitHistoryDAO
            .selectProjectWorkerEntryHistoryComingCount30Day(userId);
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
    public ProjectWorkerEntryExitHistory queryProjectWorkerEntryExitHistory(
            String code) {
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
    public ProjectWorkerEntryExitHistory getLastTimeEntryTime(
            String workerCode) {
        ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
        condition.setWorkerCode(workerCode);
        condition.setOrder("code", false);
        condition.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        List<ProjectWorkerEntryExitHistory> selectList = projectWorkerEntryExitHistoryDAO
            .selectList(condition);
        if (CollectionUtils.isEmpty(selectList)) {
            return null;
        }
        return projectWorkerEntryExitHistoryDAO.selectList(condition).get(0);
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

}
