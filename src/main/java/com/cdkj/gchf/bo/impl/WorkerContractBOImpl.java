package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerContractDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.SerializeFilterHolder;
import com.cdkj.gchf.dto.req.XN631670Req;
import com.cdkj.gchf.dto.req.XN631672Req;
import com.cdkj.gchf.dto.req.XN631673ReqData;
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631916ReqContract;
import com.cdkj.gchf.dto.req.XN631917Req;
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
public class WorkerContractBOImpl extends PaginableBOImpl<WorkerContract>
        implements IWorkerContractBO {

    @Autowired
    private IWorkerContractDAO workerContractDAO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IWorkerContractBO workerContractBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String saveWorkerContract(WorkerContract workerContract) {
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerContract.getCode());
        workerContract.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        workerContract.setCode(code);
        workerContractDAO.insert(workerContract);
        return code;
    }

    @Override
    public String saveWorkerContract(XN631673ReqData data,
            ProjectWorker projectWorker) {
        String code = null;
        WorkerContract workerContract = new WorkerContract();

        workerContract.setCode(code);
        BeanUtils.copyProperties(data, workerContract);
        BeanUtils.copyProperties(projectWorker, workerContract);
        workerContract.setWorkerCode(projectWorker.getCode());
        workerContract.setIdcardType("01");
        workerContract.setWorkerName(data.getWorkerName());
        workerContract.setWorkerCode(projectWorker.getCode());
        if (StringUtils.isNotBlank(data.getUnit())) {
            workerContract.setUnit(Integer.parseInt(data.getUnit()));
        }

        if (StringUtils.isNotBlank(data.getStartDate())) {
            workerContract.setStartDate(DateUtil.strToDate(data.getStartDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
                // Date strToDate = DateUtil.strToDate(data.getStartDate(),
                // "yyyy/mm/dd");

            // String format = new SimpleDateFormat("yyyy-MM-dd")
            // .format(strToDate);
            // Date toDate = DateUtil.strToDate(format, "yyyy-MM-dd");
            // workerContract.setStartDate());
        }
        if (StringUtils.isNotBlank(data.getEndDate())) {
            workerContract.setEndDate(DateUtil.strToDate(data.getEndDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
            // Date strToDate = DateUtil.strToDate(data.getStartDate(),
            // "yyyy/mm/dd");
            // String format = new SimpleDateFormat("yyyy-MM-dd")
            // .format(strToDate);
            // Date toDate = DateUtil.strToDate(format, "yyyy-MM-dd");
            // workerContract.setEndDate(toDate);
        }
        if (StringUtils.isNotBlank(data.getContractPeriodType())) {
            workerContract.setContractPeriodType(
                Integer.parseInt(data.getContractPeriodType()));
        }
        if (StringUtils.isNotBlank(data.getUnitPrice())) {
            workerContract.setUnitPrice(new BigDecimal(data.getUnitPrice()));
        }
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerContract.getCode());
        workerContract.setCode(code);

        // 录入数据
        workerContract.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        workerContract.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
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
        BeanUtils.copyProperties(req, workerContract);
        if (StringUtils.isNotBlank(req.getStartDate())) {
            Date startDate = DateUtil.strToDate(req.getStartDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            workerContract.setStartDate(startDate);
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            Date endDate = DateUtil.strToDate(req.getEndDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            workerContract.setEndDate(endDate);
        }
        if (StringUtils.isNotBlank(req.getUnit())) {
            workerContract.setUnit(Integer.parseInt(req.getUnit()));
        }
        if (StringUtils.isNotBlank(req.getUnitPrice())) {
            workerContract.setUnitPrice(new BigDecimal(req.getUnitPrice()));
        }
        if (StringUtils.isNotBlank(req.getContentPic())) {
            workerContract.setContentPic(req.getContentPic());
        }
        if (StringUtils.isNotBlank(req.getContractCode())) {
            workerContract.setContractCode(req.getContractCode());
        }
        if (StringUtils.isNotBlank(req.getContractPeriodType())) {
            workerContract.setContractPeriodType(
                Integer.parseInt(req.getContractPeriodType()));
        }
        workerContract.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        workerContract.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());

        workerContractDAO.update(workerContract);
    }

    @Override
    public void updateWorkerContractDeleteStatus(String code, String status) {
        WorkerContract workerContract = new WorkerContract();
        workerContract.setCode(code);
        workerContract.setDeleteStatus(status);
        workerContractDAO.updateWorkerContractDeleteStatus(workerContract);
    }

    @Override
    public WorkerContract getWorkerContract(String projectCode,
            String workerCode) {
        WorkerContract workerContract = new WorkerContract();
        workerContract.setProjectCode(projectCode);
        workerContract.setWorkerCode(workerCode);
        workerContract.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        WorkerContract select = workerContractDAO.select(workerContract);
        return select;
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
    public List<WorkerContract> queryWorkerContract(String projectCode,
            String workerCode) {
        WorkerContract workerContract = new WorkerContract();
        workerContract.setProjectCode(projectCode);
        workerContract.setWorkerCode(workerCode);
        workerContract.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        return workerContractDAO.selectList(workerContract);

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
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(req.getWorkerCode());
        String code = null;
        WorkerContract workerContract = new WorkerContract();
        if (StringUtils.isNotBlank(req.getUnit())) {
            workerContract.setUnit(Integer.parseInt(req.getUnit()));
        }
        if (StringUtils.isNotBlank(req.getContractPeriodType())) {
            workerContract.setContractPeriodType(
                Integer.parseInt(req.getContractPeriodType()));
        }
        if (StringUtils.isNotBlank(req.getUnitPrice())) {
            workerContract.setUnitPrice(new BigDecimal(req.getUnitPrice()));
        }
        BeanUtils.copyProperties(projectWorker, workerContract);
        BeanUtils.copyProperties(req, workerContract);
        workerContract.setTeamName(projectWorker.getTeamName());
        workerContract.setContractPeriodType(
            Integer.parseInt(req.getContractPeriodType()));
        workerContract.setCode(code);
        Date startDate = DateUtil.strToDate(req.getStartDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        Date endDate = DateUtil.strToDate(req.getEndDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerContract.getCode());
        workerContract.setCode(code);
        workerContract.setStartDate(startDate);
        workerContract.setEndDate(endDate);
        workerContract.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        workerContract.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        workerContractDAO.insert(workerContract);
        return code;
    }

    @Override
    public void refreshUploadStatus(String code, String status) {
        WorkerContract workerContract = new WorkerContract();
        workerContract.setCode(code);
        workerContract.setUploadStatus(status);
        workerContractDAO.updateWorkerContractStatus(workerContract);
    }

    @Override
    public void fakeDeleteWorkerContract(String workerCode) {
        WorkerContract workerContract = new WorkerContract();
        workerContract.setWorkerCode(workerCode);
        workerContract.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        workerContract.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        workerContractDAO.updateWorkerContractDeleteStatus(workerContract);
    }

    @Override
    public void fakeDeleteWorkerContractByProjectCode(String projectCode) {
        WorkerContract workerContract = new WorkerContract();
        workerContract.setProjectCode(projectCode);
        workerContract.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        workerContract.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        workerContractDAO.updateWorkerContractDeleteStatus(workerContract);
    }

    @Override
    public JsonObject getRequestJson(WorkerContract workerContract,
            ProjectConfig projectConfig) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("projectCode", projectConfig.getProjectCode());
        JsonObject childJson = new JsonObject();
        childJson.addProperty("corpCode", workerContract.getCorpCode());
        childJson.addProperty("corpName", workerContract.getCorpName());
        childJson.addProperty("idCardType", workerContract.getIdcardType());

        workerContract.setProjectCode(projectConfig.getProjectCode());
        String encrypt = AesUtils.encrypt(workerContract.getIdcardNumber(),
            projectConfig.getSecret());
        childJson.addProperty("idCardNumber", encrypt);
        childJson.addProperty("contractPeriodType",
            workerContract.getContractPeriodType());
        childJson.addProperty("startDate", new SimpleDateFormat("yyyy-MM-dd")
            .format(workerContract.getStartDate()));
        childJson.addProperty("endDate", new SimpleDateFormat("yyyy-MM-dd")
            .format(workerContract.getEndDate()));
        if (workerContract.getUnit() != null) {
            childJson.addProperty("unit", workerContract.getUnit());
        }
        if (workerContract.getUnitPrice() != null) {
            childJson.addProperty("unitPrice", workerContract.getUnitPrice());
        }
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(childJson);
        jsonObject.add("contractList", jsonArray);
        return jsonObject;
    }

}
