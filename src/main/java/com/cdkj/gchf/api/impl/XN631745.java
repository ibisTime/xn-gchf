package com.cdkj.gchf.api.impl;

import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631745Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631745   
 * @Description:分页查询进退场人员
 * @author: Old3
 * @date:   2019年3月21日 下午4:18:12     
 * @Copyright:
 */
public class XN631745 extends AProcessor {
    private IProjectWorkerEntryExitHistoryAO projectWorkerEntryExitHistoryAO = SpringContextHolder
        .getBean(IProjectWorkerEntryExitHistoryAO.class);

    private XN631745Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, condition);
        String column = null;
        if (org.apache.commons.lang3.StringUtils.isBlank(column)) {
            column = IProjectWorkerEntryExitHistoryAO.DEFAULT_ORDER_COLUMN;
        }
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        condition.setOrder(column, req.getOrderDir());
        return projectWorkerEntryExitHistoryAO
            .queryProjectWorkerEntryExitHistoryPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631745Req.class);
        ObjValidater.validateReq(req);
    }

}
