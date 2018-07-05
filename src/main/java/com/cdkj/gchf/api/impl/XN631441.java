package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631441Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 批量删除工资条
 * @author: silver 
 * @since: 2018年7月5日 下午2:36:13 
 * @history:
 */
public class XN631441 extends AProcessor {
    private ISalaryAO salaryAO = SpringContextHolder.getBean(ISalaryAO.class);

    private XN631441Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        salaryAO.dropSalaryList(req.getSalaryCodeList());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631441Req.class);
        ObjValidater.validateReq(req);
    }
}
