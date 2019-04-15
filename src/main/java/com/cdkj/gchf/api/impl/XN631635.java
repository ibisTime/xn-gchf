package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631635Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631635   
 * @Description:修改国家平台参建单位信息
 * @author: Old3
 * @date:   2019年4月15日 下午1:59:07     
 * @Copyright:
 */
public class XN631635 extends AProcessor {
    private IProjectCorpInfoAO projectCorpInfoAO = SpringContextHolder
        .getBean(IProjectCorpInfoAO.class);

    private XN631635Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectCorpInfoAO.updatePlantformProjectCorpInfo(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631635Req.class);
        ObjValidater.validateReq(req);
    }

}
