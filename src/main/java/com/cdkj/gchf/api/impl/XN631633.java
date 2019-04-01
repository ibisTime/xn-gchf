package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631633Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631633   
 * @Description: 批量导入参建单位
 * @author: Old3
 * @date:   2019年3月27日 下午1:26:57     
 * @Copyright:
 */
public class XN631633 extends AProcessor {
    private IProjectCorpInfoAO projectCorpInfoAO = SpringContextHolder
        .getBean(IProjectCorpInfoAO.class);

    private XN631633Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectCorpInfoAO.importProjectCorpInfo(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631633Req.class);
        ObjValidater.validateReq(req);
    }

}
