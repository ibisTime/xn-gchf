package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;

public class XNOther extends AProcessor {

    @Override
    public Object doBusiness() throws BizException {
        throw new BizException("702xxx", "无效API功能号");
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        throw new ParaException("702xxx", "无效API功能号");

    }

}
