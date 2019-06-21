package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCameraAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.ProjectCamera;
import com.cdkj.gchf.dto.req.XN631857Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;
import com.sun.org.apache.bcel.internal.generic.LOR;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.BeanUtils;

/**
 * @author : old3
 * @since : 2019-06-21 1:59
 */
public class XN631857 extends AProcessor {

    private XN631857Req req = null;
    private IProjectCameraAO projectCameraAO = SpringContextHolder.getBean(IProjectCameraAO.class);

    @Override
    public Object doBusiness() throws BizException {
        ProjectCamera projectCamera = new ProjectCamera();
        BeanUtils.copyProperties(req, projectCamera);
        String order = req.getOrderColumn();
        if (StringUtils.isBlank(order)) {
            order = IProjectCameraAO.DEFAULT_ORDER_COLUMN;
        }
        projectCamera.setOrder(order, req.getOrderDir());
        return projectCameraAO.queryProjectCameraList(projectCamera);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631857Req.class);
        ObjValidater.validateReq(req);
    }
}

    
    