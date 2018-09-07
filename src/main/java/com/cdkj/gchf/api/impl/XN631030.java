package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IDepartmentAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631030Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 新增部门
 * @author: nyc 
 * @since: 2018年4月25日 下午9:00:02 
 * @history:
 */
public class XN631030 extends AProcessor {

    private IDepartmentAO departmentAO = SpringContextHolder
        .getBean(IDepartmentAO.class);

    private XN631030Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(departmentAO.addDepartment(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631030Req.class);
        ObjValidater.validateReq(req);
    }

}
