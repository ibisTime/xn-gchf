package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IOperateLogAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.OperateLog;

@Service
public class OperateLogAOImpl implements IOperateLogAO {

    @Autowired
    private IOperateLogBO operateLogBO;

    @Override
    public Paginable<OperateLog> queryOperateLogPage(int start, int limit,
            OperateLog condition) {
        return operateLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<OperateLog> queryOperateLogList(OperateLog condition) {
        return operateLogBO.queryOperateLogList(condition);
    }

    @Override
    public OperateLog getOperateLog(String code) {
        return operateLogBO.getOperateLog(code);
    }
}
