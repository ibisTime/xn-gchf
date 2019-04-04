package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerContractDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.SerializeFilterHolder;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631916ReqContract;
import com.cdkj.gchf.dto.req.XN631917Req;
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
public class WorkerContractBOImpl extends PaginableBOImpl<WorkerContract>
        implements IWorkerContractBO {

    @Autowired
    private IWorkerContractDAO workerContractDAO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IWorkerContractBO workerContractBO;

    @Override
    public String saveWorkerContract(WorkerContract workerContract) {
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerContract.getCode());
        workerContract.setCode(code);
        workerContractDAO.insert(workerContract);
        return code;
    }

    @Override
    public void removeWorkerContract(String userId, String code) {
        WorkerContract data = new WorkerContract();
        data.setCode(code);
        WorkerContract workerContract = workerContractBO
            .getWorkerContract(code);
        if (workerContract.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631671", "劳动合同已上传,无法删除");
        }
        workerContractDAO.delete(data);
    }

    @Override
    public void refreshWorkerContract(XN631672Req req) {
        WorkerContract workerContract = new WorkerContract();
        workerContract.setCode(req.getCode());
        workerContract.setStartDate(req.getStartDate());
        workerContract.setEndDate(req.getEndDate());
        workerContract.setUnit(req.getUnit());
        workerContract.setUnitPrice(req.getUnitPrice());
        workerContract.setContentPic(req.getContentPic());
        if (workerContractBO.getWorkerContract(req.getCode()).getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631672", "劳动合同已上传,无法编辑");
        }
        workerContractDAO.update(workerContract);
    }

    @Override
    public void doUpload(XN631916Req req, ProjectConfig projectConfig) {

        List<XN631916ReqContract> contractList = req.getContractList();
        for (XN631916ReqContract contract : contractList) {
            contract.setIdCardNumber(AesUtils.encrypt(
                contract.getIdCardNumber(), projectConfig.getSecret()));
        }
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        String data = JSONObject.toJSONString(req,
            SerializeFilterHolder.XN631916Filter(),
            SerializerFeature.WriteDateUseDateFormat);

        String resString = GovConnecter.getGovData("WorkerContract.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public Paginable<WorkerContract> doQuery(XN631917Req req,
            ProjectConfig projectConfig) {
        WorkerContract workerContract = new WorkerContract();
        BeanUtils.copyProperties(req, workerContract);

        String data = JSONObject.toJSON(workerContract).toString();

        String queryString = GovConnecter.getGovData("WorkerContract.Query",
            data, projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<WorkerContract> page = GovUtil.parseGovPage(
            req.getPageIndex(), req.getPageSize(), queryString, replaceMap,
            WorkerContract.class);

        return page;
    }

    @Override
    public List<WorkerContract> queryWorkerContractList(
            WorkerContract condition) {
        return workerContractDAO.selectList(condition);
    }

    @Override
    public WorkerContract getWorkerContract(String code) {
        WorkerContract data = null;
        if (StringUtils.isNotBlank(code)) {
            WorkerContract condition = new WorkerContract();
            condition.setCode(code);
            data = workerContractDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "合同编号不存在");
            }
        }
        return data;
    }

    @Override
    public String saveWorkerContract(XN631670Req req) {
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(req.getWorkerCode());
        ProjectConfig projectConfigByLocal = projectConfigBO
            .getProjectConfigByLocal(req.getProjectCode());
        String code = null;
        ProjectCorpInfo condition = new ProjectCorpInfo();
        condition.setCorpCode(req.getCorpCode());
        ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
            .queryProjectCorpInfoList(condition).get(0);
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerContract.getCode());
        WorkerContract workerContract = new WorkerContract();
        BeanUtils.copyProperties(req, workerContract);
        workerContract.setProjectCode(projectConfigByLocal.getProjectCode());
        workerContract.setProjectName(projectConfigByLocal.getProjectName());
        workerContract.setCorpCode(projectCorpInfo.getCorpCode());
        workerContract.setCorpName(projectCorpInfo.getCorpName());
        workerContract.setIdcardNumber(workerInfo.getIdCardNumber());
        workerContract.setIdcardType(workerInfo.getIdCardType());
        workerContract.setWorkerCode(workerInfo.getCode());
        workerContract.setWorkerName(workerInfo.getName());
        workerContract.setWorkerMobile(workerInfo.getCellPhone());
        workerContract.setContractPeriodType(
            Integer.parseInt(req.getContractPeriodType()));
        workerContract.setCode(code);
        workerContract.setStartDate(DateUtil.strToDate(req.getStartDate(),
            DateUtil.DATA_TIME_PATTERN_1));
        workerContract.setEndDate(
            DateUtil.strToDate(req.getEndDate(), DateUtil.DATA_TIME_PATTERN_1));
        workerContract.setUnitPrice(new BigDecimal(req.getUnitPrice()));
        workerContract.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        workerContractDAO.insert(workerContract);
        return code;
    }

    @Transactional
    @Override
    public void saveWorkerContractToPlantfrom(String userId,
            List<String> codeList) {
        User briefUser = userBO.getBriefUser(userId);

        for (String code : codeList) {
            WorkerContract workerContract = workerContractBO
                .getWorkerContract(code);
            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(workerContract.getProjectCode());
            if (projectConfig == null) {
                throw new BizException("XN631674", "该项目未配置，无法查询");
            }
            workerContract.setProjectCode(projectConfig.getProjectCode());
            // 封装请求json
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("projectCode",
                projectConfig.getProjectCode());
            JsonObject childJson = new JsonObject();
            childJson.addProperty("corpCode", workerContract.getCorpCode());
            childJson.addProperty("corpName", workerContract.getCorpName());
            childJson.addProperty("idCardType", workerContract.getIdcardType());

            String encrypt = AesUtils.encrypt(workerContract.getIdcardNumber(),
                projectConfig.getSecret());
            childJson.addProperty("idCardNumber", encrypt);
            childJson.addProperty("contractPeriodType",
                workerContract.getContractPeriodType());
            childJson.addProperty("startDate",
                new SimpleDateFormat("yyyy-MM-dd")
                    .format(workerContract.getStartDate()));
            childJson.addProperty("endDate", new SimpleDateFormat("yyyy-MM-dd")
                .format(workerContract.getEndDate()));
            childJson.addProperty("unit", workerContract.getUnit());
            childJson.addProperty("unitPrice", workerContract.getUnitPrice());
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(childJson);
            jsonObject.add("contractList", jsonArray);

            // 上传到国家平台
            String resString = GovConnecter.getGovData("WorkerContract.Add",
                jsonObject.toString(), projectConfig.getProjectCode(),
                projectConfig.getSecret());
            // 增加操作日志
            String log = operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkContract.getCode(), code,
                EOperateLogOperate.UploadWorkContract.getValue(), briefUser,
                null);
            // 消息队列更新状态
            AsyncQueueHolder.addSerial(resString, projectConfig,
                "workerContractBO", code,
                EUploadStatus.UPLOAD_UNEDITABLE.getCode(), log);
        }
    }

}
