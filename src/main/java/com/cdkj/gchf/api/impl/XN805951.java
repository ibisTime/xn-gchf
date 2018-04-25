package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.common.QnTokenImpl;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN805951Req;
import com.cdkj.gchf.dto.res.XN805951Res;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 根据系统编号获取七牛uploadToken
 * @author: xieyj 
 * @since: 2016年10月11日 上午9:45:51 
 * @history:
 */
public class XN805951 extends AProcessor {
    private QnTokenImpl qnTokenImpl = SpringContextHolder
        .getBean(QnTokenImpl.class);

    private XN805951Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805951Res(qnTokenImpl.getUploadToken(req.getCompanyCode(),
            req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805951Req.class);
        StringValidater
            .validateBlank(req.getCompanyCode(), req.getSystemCode());
    }
}
