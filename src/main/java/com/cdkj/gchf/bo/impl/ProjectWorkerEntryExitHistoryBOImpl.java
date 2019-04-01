package com.cdkj.gchf.bo.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            .getProjectWorker(req.getProjectWorkerCode());
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorkerEntryExitHistory.getCode());
        BeanUtils.copyProperties(projectWorker, data);
        data.setCode(code);
        data.setDate(req.getDate());
        data.setType(req.getType());
        data.setIdcardNumber(projectWorker.getIdCardType());
        data.setIdcardType(projectWorker.getIdCardType());
        data.setJoinDatetime(projectWorker.getJoinDatetime());
        data.setLeavingDatetime(projectWorker.getLeavingDatetime());
        data.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
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
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, projectWorkerEntryExitHistory);
        projectWorkerEntryExitHistoryDAO.update(projectWorkerEntryExitHistory);
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
        if (entryExitHistory == null) {
            throw new BizException("进退场信息不能为空");
        }
        code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorkerEntryExitHistory.getValue());
        projectWorkerEntryExitHistoryDAO.insert(entryExitHistory);
        return code;
    }

}
