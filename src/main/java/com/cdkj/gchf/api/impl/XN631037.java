package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IDepartmentAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631037Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询部门
 * @author: nyc 
 * @since: 2018年4月25日 下午9:00:02 
 * @history:
 */
public class XN631037 extends AProcessor {

    private IDepartmentAO departmentAO = SpringContextHolder
        .getBean(IDepartmentAO.class);

    private XN631037Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return departmentAO.getDepartment(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631037Req.class);
        ObjValidater.validateReq(req);
    }

}
