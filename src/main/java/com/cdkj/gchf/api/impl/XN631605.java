package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631605Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631605   
 * @Description:分页查询项目人员
 * @author: Old3
 * @date:   2019年3月20日 下午6:45:54     
 * @Copyright:
 */
public class XN631605 extends AProcessor {

    private IProjectWorkerAO projectWorkerAO = SpringContextHolder
        .getBean(IProjectWorkerAO.class);

    private XN631605Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProjectWorker projectWorkerInfo = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorkerInfo);
        projectWorkerInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProjectWorkerAO.DEFAULT_ORDER_COLUMN;
        }
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        projectWorkerInfo.setOrder(column, req.getOrderDir());

        return projectWorkerAO.queryProjectWorkerPage(start, limit,
            projectWorkerInfo);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631605Req.class);
        ObjValidater.validateReq(req);
    }

}
