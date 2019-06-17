package com.cdkj.gchf.bo.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerAttendanceDAO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631713ReqData;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631918ReqData;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.enums.EAttendanceSource;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EWorkerAttendanceUploadStatus;
import com.cdkj.gchf.enums.EWorkerContractUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class WorkerAttendanceBOImpl extends PaginableBOImpl<WorkerAttendance>
        implements IWorkerAttendanceBO {

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IWorkerAttendanceDAO workerAttendanceDAO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    @Override
    public String saveWorkerAttendance(XN631710Req data,
            TeamMaster teamMaster) {
        String code = null;
        WorkerAttendance workerAttendance = new WorkerAttendance();
        BeanUtils.copyProperties(data, workerAttendance);

        Project project = projectBO.getProject(data.getProjectCode());
        workerAttendance.setProjectName(project.getName());
        ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(data.getWorkerCode());
        if (StringUtils.isNotBlank(data.getDate())) {
            workerAttendance.setDate(DateUtil.strToDate(data.getDate(),
                    DateUtil.DATA_TIME_PATTERN_1));
        }
        workerAttendance.setWorkerCode(projectWorker.getCode());
        workerAttendance.setWorkerName(projectWorker.getWorkerName());
        workerAttendance.setIdCardNumber(projectWorker.getIdcardNumber());
        workerAttendance.setIdCardType(projectWorker.getIdcardType());
        workerAttendance
                .setUploadStatus(EWorkerAttendanceUploadStatus.TO_UPLOAD.getCode());
        workerAttendance.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerAttendance.getCode());
        workerAttendance.setCode(code);
        workerAttendance.setTeamName(teamMaster.getTeamName());
        workerAttendance.setSource(EAttendanceSource.SYSTEM.getCode());

        workerAttendanceDAO.insert(workerAttendance);
        return code;
    }

    @Override
    public String saveWorkerAttendance(WorkerAttendance workerAttendance) {
        String code = null;
        if (workerAttendance == null) {
            throw new BizException("添加考勤失败 考勤信息不能为空");

        }
        code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerAttendance.getCode());
        workerAttendance.setCode(code);
        workerAttendanceDAO.insert(workerAttendance);
        return code;
    }

    @Override
    public String saveWorkerAttendance(String projectCode, XN631713ReqData data,
            ProjectWorker projectWorker, WorkerAttendance workerAttendance) {
        String code = null;
        // Operate
        BeanUtils.copyProperties(data, workerAttendance);
        BeanUtils.copyProperties(projectWorker, workerAttendance);

        TeamMaster condition = new TeamMaster();
        condition.setCorpCode(data.getCorpCode());
        condition.setProjectCode(projectCode);
        condition.setTeamName(data.getTeamName());
        TeamMaster masterByCondition = teamMasterBO
                .getTeamMasterByCondition(condition);
        workerAttendance.setTeamSysNo(masterByCondition.getCode());
        if (StringUtils.isNotBlank(data.getDate())) {
            Date strToDate = DateUtil.strToDate(data.getDate(), "yyyy/mm/dd");
            //
            String format = new SimpleDateFormat("yyyy-MM-dd")
                    .format(strToDate);
            Date toDate = DateUtil.strToDate(format, "yyyy-MM-dd");
            workerAttendance.setDate(toDate);
        }
        workerAttendance
                .setUploadStatus(EWorkerAttendanceUploadStatus.TO_UPLOAD.getCode());
        workerAttendance.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerAttendance.getCode());
        workerAttendance.setCode(code);
        workerAttendance.setSource(EAttendanceSource.SYSTEM.getCode());

        return code;
    }

    @Override
    public void deleteWorkerAttendance(String code) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setCode(code);
        workerAttendanceDAO.deleteWorkerAttendance(workerAttendance);
    }

    @Override
    public WorkerAttendance select(WorkerAttendance condition) {
        return workerAttendanceDAO.select(condition);
    }

    @Override
    public void deleteWorkerAttendaceByWorkerCode(String workerCode) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setWorkerCode(workerCode);
        workerAttendanceDAO
                .deleteWorkerAttendanceByWorkerCode(workerAttendance);
    }

    @Override
    public void batchDeleteWorkerAttendance(List<String> codes) {
        WorkerAttendance workerAttendance = new WorkerAttendance();

        workerAttendance.setCodeList(codes);

        workerAttendanceDAO.batchDeleteWorkerAttendacne(workerAttendance);
    }

    @Override
    public String addWorkerAttendace(
            WorkerEntryExitRecord workerEntryExitRecord,
            EquipmentInfo equipmentInfo, Date time, String photoUrl,
            String type) {
        WorkerAttendance workerAttendance = new WorkerAttendance();

        workerAttendance.setProjectCode(workerEntryExitRecord.getProjectCode());
        workerAttendance.setProjectName(workerEntryExitRecord.getProjectName());
        workerAttendance.setTeamSysNo(workerEntryExitRecord.getTeamCode());
        workerAttendance.setTeamName(workerEntryExitRecord.getTeamName());
        workerAttendance.setWorkerCode(workerEntryExitRecord.getWorkerCode());
        workerAttendance.setWorkerName(workerEntryExitRecord.getWorkerName());
        workerAttendance
                .setIdCardNumber(workerEntryExitRecord.getIdcardNumber());
        workerAttendance.setCreateDatetime(workerEntryExitRecord.getDate());

        workerAttendance.setTeamSysNo(workerEntryExitRecord.getTeamCode());
        workerAttendance.setTerminalCode(workerEntryExitRecord.getCode());
        workerAttendance.setIdCardType("01");
        workerAttendance
                .setIdCardNumber(workerEntryExitRecord.getIdcardNumber());
        workerAttendance.setDirection(workerEntryExitRecord.getDirection());
        workerAttendance.setImage(photoUrl);
        workerAttendance.setDate(time);
        String code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerAttendance.getCode());
        workerAttendance.setCode(code);
        workerAttendance
                .setCreateDatetime(new Date(System.currentTimeMillis()));
        workerAttendance.setSource(EAttendanceSource.REAL_TIME.getCode());
        workerAttendance
                .setUploadStatus(EWorkerAttendanceUploadStatus.TO_UPLOAD.getCode());
        workerAttendance.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        workerAttendanceDAO.insert(workerAttendance);
        return code;
    }

    @Override
    public String saveWorkerAttendance(Project project, TeamMaster teamMaster,
            ProjectWorker projectWorker, Date date, String direction) {
        WorkerAttendance workerAttendance = new WorkerAttendance();

        String code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerAttendance.getCode());
        workerAttendance.setCode(code);
        workerAttendance.setProjectCode(project.getCode());
        workerAttendance.setProjectName(project.getName());
        workerAttendance.setTeamSysNo(teamMaster.getCode());

        workerAttendance.setTeamName(teamMaster.getTeamName());
        workerAttendance.setWorkerCode(projectWorker.getCode());
        workerAttendance.setWorkerName(projectWorker.getWorkerName());
        workerAttendance.setIdCardType(projectWorker.getIdcardType());
        workerAttendance.setIdCardNumber(projectWorker.getIdcardNumber());

        workerAttendance.setDate(date);
        workerAttendance.setDirection(direction);
        workerAttendance.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        workerAttendance
                .setUploadStatus(EWorkerContractUploadStatus.TO_UPLOAD.getCode());
        workerAttendance.setSource(EAttendanceSource.SYSTEM.getCode());

        workerAttendanceDAO.insert(workerAttendance);

        return code;
    }

    /**
     * <p>Title: saveDeviceWorkerAttendance</p>
     * <p>Description: 保存设备识别的人员考勤</p>
     */
    @Override
    public WorkerAttendance saveDeviceWorkerAttendance(
            ProjectWorker projectWorker, String deviceKey, String dateTime,
            String photoUrl, String type, String dataString, String recMode,
            String idCardInfo) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        String code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerAttendance.getCode());
        workerAttendance.setCode(code);
        workerAttendance.setProjectCode(projectWorker.getProjectCode());
        workerAttendance.setProjectName(projectWorker.getProjectName());
        workerAttendance.setDate(
                DateUtil.strToDate(dateTime, DateUtil.DATA_TIME_PATTERN_1));

        workerAttendance.setTeamSysNo(projectWorker.getTeamSysNo());
        workerAttendance.setTeamName(projectWorker.getTeamName());
        workerAttendance.setWorkerCode(projectWorker.getCode());
        workerAttendance.setWorkerName(projectWorker.getWorkerName());
        workerAttendance.setIdCardType(projectWorker.getIdcardType());

        workerAttendance.setIdCardNumber(projectWorker.getIdcardNumber());
        workerAttendance.setImage(photoUrl);
        workerAttendance.setTerminalCode(deviceKey);
        workerAttendance.setDirection(
                equipmentInfoBO.getEquipmentInfoByKey(deviceKey).getDirection());

        workerAttendance
                .setUploadStatus(EWorkerAttendanceUploadStatus.TO_UPLOAD.getCode());

        workerAttendance.setSource(EAttendanceSource.REAL_TIME.getCode());
        workerAttendance.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        workerAttendanceDAO.insert(workerAttendance);
        return workerAttendance;
    }

    @Override
    public int deleteWorkAttendanceByProject(String projectCode) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setProjectCode(projectCode);
        return workerAttendanceDAO.delete(workerAttendance);
    }

    @Override
    public int deleteWorkAttendanceByTeamMaster(String teamMasterNo) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setTeamSysNo(teamMasterNo);
        return workerAttendanceDAO.delete(workerAttendance);
    }

    @Override
    public int deleteWorkAttendanceByWorkerCode(String workerCode) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setWorkerCode(workerCode);

        return workerAttendanceDAO.delete(workerAttendance);
    }

    @Override
    public void refreshUploadStatus(String code, String status) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setCode(code);
        workerAttendance.setUploadStatus(status);
        workerAttendanceDAO.updateStatus(workerAttendance);
    }

    @Override
    public void refreshWorkerAttendance(XN631712Req data) {

        User user = userBO.getBriefUser(data.getUserId());
        WorkerAttendance tempWorkerAttendance = new WorkerAttendance();
        tempWorkerAttendance.setCode(data.getCode());
        WorkerAttendance select = workerAttendanceDAO
                .select(tempWorkerAttendance);
        select.setDate(data.getDate());
        select.setDirection(data.getDirection());
        select.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        select
                .setUploadStatus(EWorkerAttendanceUploadStatus.TO_UPLOAD.getCode());
        workerAttendanceDAO.update(select);
        operateLogBO.saveOperateLog(EOperateLogRefType.WorkAttendance.getCode(),
                data.getCode(), "修改人员考勤", user, "修改人员考勤");
    }

    @Override
    public void refreshWorkerAttendance(String code, String status) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setCode(code);
        workerAttendance.setUploadStatus(status);
        workerAttendanceDAO.updateStatus(workerAttendance);
    }

    @Override
    public void refreshWorkerAttendanceTeamName(String teamSysNo,
            String teamName) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setTeamName(teamName);
        workerAttendance.setTeamSysNo(teamSysNo);
        workerAttendanceDAO.updateWorkerAttendanceTeamName(workerAttendance);
    }

    ;

    @Override
    public int updateWorkerAttendanceDeleteStatus(String code, String status) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        workerAttendance.setCode(code);
        workerAttendance.setDeleteStatus(status);
        return workerAttendanceDAO
                .updateWorkerAttendanceDeleteStatus(workerAttendance);
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
    public Integer selectWorkerAttendance30Day(String userId) {
        return workerAttendanceDAO.selectWorkerAttendance30Day(userId);
    }

    @Override
    public Integer selectWorkerAttendanceToday(String userId) {
        return workerAttendanceDAO.selectWorkerAttendanceToday(userId);
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
        childJson.addProperty("channel", workerAttendance.getChannel());
        childJson.addProperty("attendType", workerAttendance.getAttendType());
        childJson.addProperty("lng", workerAttendance.getLng());
        childJson.addProperty("lat", workerAttendance.getLat());

        dataList.add(childJson);
        jsonObject.add("dataList", dataList);
        return jsonObject;
    }

    @Override
    public WorkerAttendance getLastAttendance(String workerCode) {
        return workerAttendanceDAO.selectWorkerNewlyWorkerAttendanceData(workerCode);
    }
}
