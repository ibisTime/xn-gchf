package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerInfoDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;

@Component
public class WorkerInfoBOImpl extends PaginableBOImpl<WorkerInfo>
        implements IWorkerInfoBO {

    @Autowired
    private IWorkerInfoDAO WorkerInfoDAO;

    @Autowired
    private IOperateLogBO operateLog;

    @Autowired
    private IUserBO userBO;

    @Override
    public boolean isWorkerInfoExist(String code) {
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(code);
        if (WorkerInfoDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveWorkerInfo(XN631790Req req) {
        WorkerInfo data = new WorkerInfo();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerInfo.getCode());
        WorkerInfo workerInfo = new WorkerInfo();
        BeanUtils.copyProperties(data, workerInfo);
        data.setCode(code);
        WorkerInfoDAO.insert(data);
        return code;
    }

    @Override
    public int removeWorkerInfo(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            WorkerInfo data = new WorkerInfo();
            data.setCode(code);
            count = WorkerInfoDAO.delete(data);
        }
        return count;
    }

    @Override
    public Paginable<WorkerInfo> doQuery(String idCardNumber,
            ProjectConfig projectConfig) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setIdCardType("01");
        workerInfo.setIdCardNumber(
            AesUtils.encrypt(idCardNumber, projectConfig.getSecret()));

        String data = JSONObject.toJSON(workerInfo).toString();

        String queryString = GovConnecter.getGovData("Worker.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<WorkerInfo> page = GovUtil.parseGovPage(0, 1, queryString,
            replaceMap, WorkerInfo.class);

        return page;
    }

    @Override
    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition) {
        return WorkerInfoDAO.selectList(condition);
    }

    @Override
    public WorkerInfo getWorkerInfo(String code) {
        WorkerInfo data = null;
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(code);
        data = WorkerInfoDAO.select(condition);
        if (data == null) {
            throw new BizException("XN631806", "信息不存在");
        }
        return data;
    }

    @Override
    public WorkerInfo getWorkerInfoByCondition(WorkerInfo workerInfo) {
        return WorkerInfoDAO.select(workerInfo);
    }

    @Override
    public WorkerInfo getWorkerInfoByCelephone(String phone) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setCellPhone(phone);
        return WorkerInfoDAO.select(workerInfo);
    }

    @Override
    public WorkerInfo getWorkerInfoByIdCardNumber(String idCardNumber) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setIdCardNumber(idCardNumber);
        return WorkerInfoDAO.select(workerInfo);
    }

    @Override
    public String saveWorkerInfo(WorkerInfo workerInfo) {
        String code = null;
        int insert = WorkerInfoDAO.insert(workerInfo);
        if (insert != 1) {
            throw new BizException("人员实名制基本信息保存失败");
        }
        code = OrderNoGenerater.generate(EGeneratePrefix.WorkerInfo.getValue());
        return code;
    }

    @Override
    public int refreshWorkerInfo(XN631791Req req) {
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(req.getCode());
        BeanUtils.copyProperties(req, condition);
        return WorkerInfoDAO.updateWorkerInfoAboutIdcard(condition);
    }

    @Override
    public int refreshWorkerInfo(XN631792Req req) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setCode(req.getCode());
        BeanUtils.copyProperties(req, workerInfo);
        return WorkerInfoDAO.updateWorkerInfoAboutPhone(workerInfo);
    }

}
