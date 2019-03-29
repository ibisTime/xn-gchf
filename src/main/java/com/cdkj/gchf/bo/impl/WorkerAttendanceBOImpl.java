package com.cdkj.gchf.bo.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerAttendanceDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631918ReqData;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class WorkerAttendanceBOImpl extends PaginableBOImpl<WorkerAttendance>
        implements IWorkerAttendanceBO {

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IWorkerAttendanceDAO workerAttendanceDAO;

    @Autowired
    private ICorpBasicinfoBO basicinfoBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    private IProjectWorkerBO projectWorkerBO;

    @Override
    public String saveWorkerAttendance(XN631710Req data) {
        String code = null;
        WorkerAttendance workerAttendance = new WorkerAttendance();
        BeanUtils.copyProperties(data, workerAttendance);
        ProjectConfig projectConfigByLocal = projectConfigBO
            .getProjectConfigByLocal(data.getProjectCode());
        workerAttendance.setProjectName(projectConfigByLocal.getProjectName());
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(data.getProjectWorkerCode());
        workerAttendance.setWorkerCode(projectWorker.getWorkerCode());
        workerAttendance.setWorkerName(projectWorker.getWorkerName());
        workerAttendance.setIdCardNumber(projectWorker.getIdCardNumber());
        workerAttendance.setIdCardType(projectWorker.getIdCardType());
        workerAttendance.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerAttendance.getCode());
        workerAttendance.setCode(code);
        System.out.println(workerAttendanceDAO);
        workerAttendanceDAO.insert(workerAttendance);
        return code;
    }

    @Override
    public int removeWorkerAttendance(String code) {
        int count = 0;
        WorkerAttendance data = new WorkerAttendance();
        data.setCode(code);
        count = workerAttendanceDAO.delete(data);
        return count;
    }

    @Override
    public void refreshWorkerAttendance(XN631712Req data) {

        User user = userBO.getBriefUser(data.getUserId());
        WorkerAttendance tempWorkerAttendance = new WorkerAttendance();
        tempWorkerAttendance.setCode(data.getCode());
        WorkerAttendance select = workerAttendanceDAO
            .select(tempWorkerAttendance);
        select.setDate(data.getDate());
        workerAttendanceDAO.update(select);
        operateLogBO.saveOperateLog(EOperateLogRefType.WorkAttendance.getCode(),
            data.getCode(), "修改人员考勤", user, null);
    }

    @Override
    public void doUpload(XN631918Req req, ProjectConfig projectConfig) {

        List<XN631918ReqData> dataList = req.getDataList();
        for (XN631918ReqData data : dataList) {
            data.setIdCardNumber(AesUtils.encrypt(data.getIdCardNumber(),
                projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req,
            "yyyy-MM-dd HH:mm:ss");

        String resString = GovConnecter.getGovData("WorkerAttendance.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public Paginable<WorkerAttendance> doQuery(XN631919Req req,
            ProjectConfig projectConfig) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        BeanUtils.copyProperties(req, workerAttendance);
        workerAttendance.setDate(DateUtil.strToDate(req.getDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING));

        String data = JSONObject.toJSONStringWithDateFormat(workerAttendance,
            "yyyy-MM-dd");

        String queryString = GovConnecter.getGovData("WorkerAttendance.Query",
            data, projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<WorkerAttendance> page = GovUtil.parseGovPage(
            req.getPageIndex(), req.getPageSize(), queryString, replaceMap,
            WorkerAttendance.class);

        return page;
    }

    @Override
    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition) {
        return workerAttendanceDAO.selectList(condition);
    }

    @Override
    public WorkerAttendance getWorkerAttendance(String code) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setCode(code);
        return workerAttendanceDAO.select(workerAttendance);
    }

    @Override
    public JsonObject getRequestJson(TeamMaster teamMaster,
            WorkerAttendance workerAttendance,
            ProjectConfig projectConfigByLocal) {
        workerAttendance.setProjectCode(projectConfigByLocal.getProjectCode());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("projectCode",
            projectConfigByLocal.getProjectCode());

        jsonObject.addProperty("teamSysNo", teamMaster.getTeamSysNo());
        JsonArray dataList = new JsonArray();
        JsonObject childJson = new JsonObject();
        childJson.addProperty("idCardType", workerAttendance.getIdCardType());
        childJson.addProperty("idCardNumber",
            AesUtils.encrypt(workerAttendance.getIdCardNumber(),
                projectConfigByLocal.getSecret()));

        childJson.addProperty("date",
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                .format(workerAttendance.getDate()));
        childJson.addProperty("direction", workerAttendance.getDirection());
        childJson.addProperty("image", workerAttendance.getImage());
        childJson.addProperty("channel", workerAttendance.getChannel());
        childJson.addProperty("attendType", workerAttendance.getAttendType());
        childJson.addProperty("lng", workerAttendance.getLng());
        childJson.addProperty("lat", workerAttendance.getLat());

        dataList.add(childJson);
        jsonObject.add("dataList", dataList);
        return jsonObject;
    }

    @Override
    public void saveWorkerAttendanceToPlantform(String userId,
            List<String> codeList) {
        User briefUser = userBO.getBriefUser(userId);
        for (String code : codeList) {
            WorkerAttendance workerAttendance = workerAttendanceBO
                .getWorkerAttendance(code);

            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(workerAttendance.getProjectCode());

            if (projectConfigByLocal == null) {
                throw new BizException("XN631714", "该项目未配置，无法查询");
            }
            TeamMaster teamMaster = teamMasterBO
                .getTeamMaster(workerAttendance.getTeamSysNo());

            JsonObject requestJson = getRequestJson(teamMaster,
                workerAttendance, projectConfigByLocal);
            System.out.println(requestJson);
            String resString = GovConnecter.getGovData("WorkerAttendance.Add",
                requestJson.toString(), projectConfigByLocal.getProjectCode(),
                projectConfigByLocal.getSecret());
            String saveOperateLog = operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code,
                EOperateLogOperate.UploadWorkAtendance.getValue(), briefUser,
                null);
            AsyncQueueHolder.addSerial(resString, projectConfigByLocal,
                "workerAttendanceBO", code,
                EUploadStatus.UPLOAD_UNEDITABLE.getCode(), saveOperateLog);
        }
    }

}
