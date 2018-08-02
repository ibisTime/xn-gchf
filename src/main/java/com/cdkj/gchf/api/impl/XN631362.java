package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCardAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631362Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改公司账户
 * @author: nyc 
 * @since: 2018年4月27日 下午9:46:22 
 * @history:
 */
public class XN631362 extends AProcessor {

    private IProjectCardAO projectCardAO = SpringContextHolder
        .getBean(IProjectCardAO.class);

    private XN631362Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectCardAO.editProjectCard(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631362Req.class);
        ObjValidater.validateReq(req);
    }

}
