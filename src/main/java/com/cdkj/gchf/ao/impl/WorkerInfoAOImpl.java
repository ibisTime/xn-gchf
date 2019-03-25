package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.IdCardChecker;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.exception.BizException;

@Service
public class WorkerInfoAOImpl implements IWorkerInfoAO {

    @Autowired
    private IWorkerInfoBO WorkerInfoBO;

    @Override
    public String addWorkerInfo(XN631790Req req) {

        IdCardChecker idCardChecker = new IdCardChecker(req.getIdCardNumber());
        if (!idCardChecker.validate()) {
            throw new BizException("XN631790", "身份信息格式错误");
        }

        WorkerInfo workerInfo = new WorkerInfo();
        BeanUtils.copyProperties(req, workerInfo);
        WorkerInfo workerInfoByCondition = WorkerInfoBO
            .getWorkerInfoByCondition(workerInfo);
        if (workerInfoByCondition != null) {
            if (workerInfoByCondition.getCellPhone() != null) {
                throw new BizException("XN631790", "该手机号录入");
            }
            if (workerInfoByCondition.getIdCardNumber() != null) {
                throw new BizException("XN631790", "该身份信息已录入");
            }
        }

        return WorkerInfoBO.saveWorkerInfo(workerInfo);
    }

    @Override
    public int editWorkerInfo(XN631791Req req) {
        return WorkerInfoBO.refreshWorkerInfo(req);
    }

    @Override
    public int dropWorkerInfo(String code) {
        if (!WorkerInfoBO.isWorkerInfoExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return WorkerInfoBO.removeWorkerInfo(code);
    }

    @Override
    public Paginable<WorkerInfo> queryWorkerInfoPage(int start, int limit,
            WorkerInfo condition) {
        return WorkerInfoBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition) {
        return WorkerInfoBO.queryWorkerInfoList(condition);
    }

    @Override
    public WorkerInfo getWorkerInfo(String code) {
        return WorkerInfoBO.getWorkerInfo(code);
    }
}
