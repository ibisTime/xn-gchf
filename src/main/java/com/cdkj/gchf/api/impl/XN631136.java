package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IOperateLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.dto.req.XN631136Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查操作日志
 * @author: silver 
 * @since: Mar 12, 2019 5:10:46 PM 
 * @history:
 */
public class XN631136 extends AProcessor {
    private IOperateLogAO operateLogAO = SpringContextHolder
        .getBean(IOperateLogAO.class);

    private XN631136Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return operateLogAO.getOperateLog(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631136Req.class);
    }
}
