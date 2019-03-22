package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.OperateLog;

@Component
public interface IOperateLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<OperateLog> queryOperateLogPage(int start, int limit,
            OperateLog condition);

    public List<OperateLog> queryOperateLogList(OperateLog condition);

    public OperateLog getOperateLog(String code);

}
