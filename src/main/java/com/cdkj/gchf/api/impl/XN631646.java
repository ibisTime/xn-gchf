package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631646Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631646   
 * @Description:详细查参建单位
 * @author: Old3
 * @date:   2019年3月19日 下午10:43:18     
 * @Copyright:
 */
public class XN631646 extends AProcessor {
    private IProjectCorpInfoAO projectCorpInfoAO = SpringContextHolder
        .getBean(IProjectCorpInfoAO.class);

    private XN631646Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return projectCorpInfoAO.getProjectCorpInfo(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631646Req.class);
        ObjValidater.validateReq(req);
    }

}
