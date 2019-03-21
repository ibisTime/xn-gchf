package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IProjectWorkerAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631607Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631607   
 * @Description:列表查询项目人员
 * @author: Old3
 * @date:   2019年3月20日 下午5:28:58     
 * @Copyright:
 */
public class XN631607 extends AProcessor {
    private IProjectWorkerAO projectWorkerAO = SpringContextHolder
        .getBean(IProjectWorkerAO.class);

    private XN631607Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProjectWorker projectWorker = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorker);
        String order = req.getOrderColumn();
        if (StringUtils.isBlank(order)) {
            order = IProjectWorkerAO.DEFAULT_ORDER_COLUMN;
        }
        projectWorker.setOrder(order, true);
        return projectWorkerAO.queryProjectWorkerList(projectWorker);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631607Req.class);
        ObjValidater.validateReq(req);
    }

}
