package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerAttendanceDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;

@Component
public class WorkerAttendanceBOImpl extends PaginableBOImpl<WorkerAttendance>
        implements IWorkerAttendanceBO {

    @Autowired
    private IWorkerAttendanceDAO workerAttendanceDAO;

    @Override
    public String saveWorkerAttendance(WorkerAttendance data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerAttendance.getCode());
            data.setCode(code);
            workerAttendanceDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeWorkerAttendance(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            WorkerAttendance data = new WorkerAttendance();
            data.setCode(code);
            count = workerAttendanceDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshWorkerAttendance(WorkerAttendance data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = workerAttendanceDAO.update(data);
        }
        return count;
    }

    @Override
    public void doUpload(XN631918Req req, ProjectConfig projectConfig) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        BeanUtils.copyProperties(req, workerAttendance);

        String data = JSONObject.toJSON(workerAttendance).toString();

        GovConnecter.getGovData("WorkerAttendance.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public void doQuery(XN631919Req req, ProjectConfig projectConfig) {
        WorkerAttendance workerAttendance = new WorkerAttendance();
        BeanUtils.copyProperties(req, workerAttendance);

        String data = JSONObject.toJSON(workerAttendance).toString();

        GovConnecter.getGovData("WorkerAttendance.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition) {
        return workerAttendanceDAO.selectList(condition);
    }

    @Override
    public WorkerAttendance getWorkerAttendance(String code) {
        WorkerAttendance data = null;
        if (StringUtils.isNotBlank(code)) {
            WorkerAttendance condition = new WorkerAttendance();
            condition.setCode(code);
            data = workerAttendanceDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "员工考勤");
            }
        }
        return data;
    }

}
