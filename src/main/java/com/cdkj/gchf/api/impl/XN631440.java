package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631440Req;
import com.cdkj.gchf.dto.res.XN631440Res;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 手动生成工资条
 * @author: silver 
 * @since: 2018年7月5日 下午2:36:21 
 * @history:
 */
public class XN631440 extends AProcessor {
    private ISalaryAO salaryAO = SpringContextHolder.getBean(ISalaryAO.class);

    private XN631440Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Integer salaryNumber = salaryAO.createSalaryManual(req.getProjectCode(),
            req.getMonth());
        return new XN631440Res(String.valueOf(salaryNumber));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631440Req.class);
        ObjValidater.validateReq(req);
    }

}
