package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.IdCardChecker;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.dto.req.XN631793Req;
import com.cdkj.gchf.enums.ECultureLevelType;
import com.cdkj.gchf.enums.EGender;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EPoliticsType;
import com.cdkj.gchf.exception.BizException;

@Service
public class WorkerInfoAOImpl implements IWorkerInfoAO {

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Override
    public String addWorkerInfo(XN631790Req req) {
        // 数据字典校验
        EPoliticsType.checkExists(req.getPoliticsType());
        ECultureLevelType.checkExists(req.getCultureLevelType());
        EIdCardType.checkExists(req.getIdCardType());
        req.setGender(EGender.checkExists(req.getGender()));

        IdCardChecker idCardChecker = new IdCardChecker(req.getIdCardNumber());
        if (!idCardChecker.validate()) {
            throw new BizException("XN631790", "身份证信息错误");
        }

        if (StringUtils.isNotBlank(req.getIdCardNumber())) {
            WorkerInfo workerInfoByIdCardNumber = workerInfoBO
                .getWorkerInfoByIdCardNumber(req.getIdCardNumber());
            if (workerInfoByIdCardNumber != null) {
                throw new BizException("XN631790", "建档失败,人员实名制信息已存在");
            }
        }

        return workerInfoBO.saveWorkerInfo(req);
    }

    @Override
    public int dropWorkerInfo(String code) {
        if (!workerInfoBO.isWorkerInfoExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return workerInfoBO.removeWorkerInfo(code);
    }

    @Override
    public Paginable<WorkerInfo> queryWorkerInfoPage(int start, int limit,
            WorkerInfo condition) {
        return workerInfoBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition) {
        return workerInfoBO.queryWorkerInfoList(condition);
    }

    @Override
    public WorkerInfo getWorkerInfo(String code) {
        return workerInfoBO.getWorkerInfo(code);
    }

    @Override
    public int addWorkerInfoIdCardInfo(XN631791Req req) {
        return workerInfoBO.refreshWorkerInfo(req);
    }

    @Override
    public int addWorkerInfoContact(XN631792Req req) {
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(req.getCode());
        if (workerInfo.getCellPhone() != null) {
            throw new BizException("XN631792", "手机号已录入");
        }
        return workerInfoBO.refreshWorkerInfo(req);
    }

    @Override
    public void readdWorkerInfo(XN631793Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        workerInfoBO.refreshWorkerInfo(req);
        operateLogBO.saveOperateLog(EOperateLogRefType.WorkerInfo.getCode(),
            req.getCode(), "重新建档人员实名制信息", user, null);
    }

}
