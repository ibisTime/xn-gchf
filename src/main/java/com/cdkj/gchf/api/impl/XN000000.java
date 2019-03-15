package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 测试接口
 * @author: silver 
 * @since: Nov 19, 2018 11:05:26 AM 
 * @history:
 */
public class XN000000 extends AProcessor {
    private ISalaryAO salaryAO = SpringContextHolder.getBean(ISalaryAO.class);

    @Override
    public Object doBusiness() throws BizException {
        salaryAO.doDelaySalaryDaily();

        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
    }
}
