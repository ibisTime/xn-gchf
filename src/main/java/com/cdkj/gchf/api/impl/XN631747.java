package com.cdkj.gchf.api.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IProjectWorkerEntryExitHistoryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631747Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631747   
 * @Description:列表查人员进退场
 * @author: Old3
 * @date:   2019年3月28日 下午10:50:19     
 * @Copyright:
 */
public class XN631747 extends AProcessor {
    private IProjectWorkerEntryExitHistoryAO projectWorkerEntryExitHistoryAO = SpringContextHolder
        .getBean(IProjectWorkerEntryExitHistoryAO.class);

    private XN631747Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, condition);
        String column = null;
        if (StringUtils.isBlank(column)) {
            column = IProjectWorkerEntryExitHistoryAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        projectWorkerEntryExitHistoryAO
            .queryProjectWorkerEntryExitHistoryList(condition);
        return null;
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631747Req.class);
        ObjValidater.validateReq(req);
    }

}
