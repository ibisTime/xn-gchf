package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN631387Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 工程进度详情查询
 * @author: CYL 
 * @since: 2018年4月28日 上午10:57:42 
 * @history:
 */
public class XN631387 extends AProcessor {
    private IProgressAO progressAO = SpringContextHolder
        .getBean(IProgressAO.class);

    private XN631387Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return progressAO.getProgress(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631387Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
