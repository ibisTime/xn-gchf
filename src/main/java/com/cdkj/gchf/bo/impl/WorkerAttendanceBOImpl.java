package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerAttendanceDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631918ReqData;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;

@Component
public class WorkerAttendanceBOImpl extends PaginableBOImpl<WorkerAttendance>
        implements IWorkerAttendanceBO {

    @Autowired
    private IWorkerAttendanceDAO workerAttendanceDAO;

    @Override
    public String saveWorkerAttendance(XN631710Req data) {
        String code = null;
        WorkerAttendance workerAttendance = new WorkerAttendance();
        BeanUtils.copyProperties(data, workerAttendance);
        code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerAttendance.getCode());
        workerAttendance.setCode(code);
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
        WorkerAttendance workerAttendance = new WorkerAttendance();
        BeanUtils.copyProperties(data, workerAttendance);
        workerAttendanceDAO.update(workerAttendance);
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
        WorkerAttendance data = null;
        WorkerAttendance condition = new WorkerAttendance();
        condition.setCode(code);
        data = workerAttendanceDAO.select(condition);
        return data;
    }

}
