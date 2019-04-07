package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
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
import com.cdkj.gchf.dto.req.XN631916Req;
import com.cdkj.gchf.dto.req.XN631916ReqContract;
import com.cdkj.gchf.dto.req.XN631917Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;

@Component
public class WorkerContractBOImpl extends PaginableBOImpl<WorkerContract>
        implements IWorkerContractBO {

    @Autowired
    private IWorkerContractDAO workerContractDAO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

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
        if (StringUtils.isNotBlank(req.getStartDate())) {
            Date startDate = DateUtil.strToDate(req.getStartDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            workerContract.setStartDate(startDate);
        }
        if (StringUtils.isNotBlank(req.getEndDate())) {
            Date endDate = DateUtil.strToDate(req.getEndDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            workerContract.setStartDate(endDate);
        }
        workerContract.setUnit(Integer.parseInt(req.getUnit()));
        workerContract.setUnitPrice(new BigDecimal(req.getUnitPrice()));
        workerContract.setContentPic(req.getContentPic());
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
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(req.getWorkerCode());
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerContract.getCode());
        WorkerContract workerContract = new WorkerContract();
        workerContract.setUnit(Integer.parseInt(req.getUnit()));
        workerContract.setContractPeriodType(
            Integer.parseInt(req.getContractPeriodType()));
        BeanUtils.copyProperties(projectWorker, workerContract);
        BeanUtils.copyProperties(req, workerContract);
        BigDecimal unitPrice = new BigDecimal(req.getUnitPrice());
        workerContract.setUnitPrice(unitPrice);
        workerContract.setContractPeriodType(
            Integer.parseInt(req.getContractPeriodType()));
        workerContract.setCode(code);
        Date startDate = DateUtil.strToDate(req.getStartDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        Date endDate = DateUtil.strToDate(req.getEndDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        workerContract.setStartDate(startDate);
        workerContract.setEndDate(endDate);
        workerContract.setUnitPrice(new BigDecimal(req.getUnitPrice()));
        workerContract.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
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

}
