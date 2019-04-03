package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.IdCardChecker;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.enums.ECultureLevelType;
import com.cdkj.gchf.enums.EGender;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EPoliticsType;
import com.cdkj.gchf.exception.BizException;

@Service
public class WorkerInfoAOImpl implements IWorkerInfoAO {

    @Autowired
    private IWorkerInfoBO WorkerInfoBO;

    @Override
    public String addWorkerInfo(XN631790Req req) {
        if (req.getBirthday() == null) {
            throw new BizException("XN631790", "出生日期不能为空");
        }
        if (req.getStartDate() == null) {
            throw new BizException("XN631790", "有效期开始日期不能为空");
        }
        if (req.getExpiryDate() == null) {
            throw new BizException("XN631790", "有效期结束日期不能为空");
        }
        // 数据字典校验
        String politicsType = EPoliticsType
            .getPoliticsTypeCode(req.getPoliticsType());
        String gender = EGender.checkDictValue(req.getGender());
        String cultureLevel = ECultureLevelType
            .getCultureLevelType(req.getCultureLevelType());
        String idCardType = EIdCardType.checkDictValue(req.getIdCardType());
        req.setGender(gender);
        req.setPoliticsType(politicsType);
        req.setCultureLevelType(cultureLevel);
        req.setIdCardType(idCardType);

        IdCardChecker idCardChecker = new IdCardChecker(req.getIdCardNumber());
        if (!idCardChecker.validate()) {
            throw new BizException("XN631790", "身份证信息错误");
        }
        return WorkerInfoBO.saveWorkerInfo(req);
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

    @Override
    public int addWorkerInfoIdCardInfo(XN631791Req req) {
        return WorkerInfoBO.refreshWorkerInfo(req);
    }

    @Override
    public int addWorkerInfoContact(XN631792Req req) {
        return WorkerInfoBO.refreshWorkerInfo(req);
    }

}
