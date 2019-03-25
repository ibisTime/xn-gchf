package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerInfoDAO;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class WorkerInfoBOImpl extends PaginableBOImpl<WorkerInfo>
        implements IWorkerInfoBO {

    @Autowired
    private IWorkerInfoDAO WorkerInfoDAO;

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
    public String saveWorkerInfo(WorkerInfo data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerInfo.getCode());
            data.setCode(code);
            WorkerInfoDAO.insert(data);
        }
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
    public int refreshWorkerInfo(WorkerInfo data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = WorkerInfoDAO.updateWorkerInfo(data);
        }
        return count;
    }

    @Override
    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition) {
        return WorkerInfoDAO.selectList(condition);
    }

    @Override
    public WorkerInfo getWorkerInfo(String code) {
        WorkerInfo data = null;
        if (StringUtils.isNotBlank(code)) {
            WorkerInfo condition = new WorkerInfo();
            condition.setCode(code);
            data = WorkerInfoDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }

    @Override
    public WorkerInfo getWorkerInfoByCondition(WorkerInfo workerInfo) {
        return WorkerInfoDAO.select(workerInfo);
    }
}
