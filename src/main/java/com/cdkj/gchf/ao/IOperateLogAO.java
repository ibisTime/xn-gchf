package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.OperateLog;

/**
 * @author silver
 */
@Component
public interface IOperateLogAO {
    String DEFAULT_ORDER_COLUMN = "code";


    /**
     * 分页查操作日志
     * @param start 开始页数
     * @param limit 每页记录数
     * @param condition 条件
     * @return
     */
    Paginable<OperateLog> queryOperateLogPage(int start, int limit,
                                              OperateLog condition);

    /**
     * 按条件查操作日志
     * @param condition 条件
     * @return
     */
    List<OperateLog> queryOperateLogList(OperateLog condition);

    /**
     * @param code 根据code查询操作日志
     * @return
     */
    OperateLog getOperateLog(String code);

}
