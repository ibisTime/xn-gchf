package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCameraAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.ProjectCamera;
import com.cdkj.gchf.dto.req.XN631853Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author : old3
 * @since : 2019-06-21 2:05
 */
public class XN631853 extends AProcessor {

    private XN631853Req req = null;

    private IProjectCameraAO projectCameraAO = SpringContextHolder.getBean(IProjectCameraAO.class);

    @Override
    public Object doBusiness() throws BizException {
        ProjectCamera condition = new ProjectCamera();
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        BeanUtils.copyProperties(req, condition);

        String order = req.getOrderColumn();

        if (StringUtils.isBlank(order)) {
            order = IProjectCameraAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(order, req.getOrderDir());

        return projectCameraAO.getAllCameraRtspAddr(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631853Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    