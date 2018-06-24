package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.dto.req.XN631093Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.BizConnecter;

/**
 * 获取人脸特征值
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN631093 extends AProcessor {

    private XN631093Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String result = BizConnecter.getFeat(req.getPict1());

        return BizConnecter.getFeat(req.getPict1());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631093Req.class);
    }

}
