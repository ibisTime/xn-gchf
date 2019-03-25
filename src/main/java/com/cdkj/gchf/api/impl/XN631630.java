package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * @ClassName:  XN631630   
 * @Description:添加参建单位
 * @author: Old3
 * @date:   2019年3月19日 下午10:43:43     
 * @Copyright:
 */
public class XN631630 extends AProcessor {
    private IProjectCorpInfoAO projectCorpInfoAO = SpringContextHolder
        .getBean(IProjectCorpInfoAO.class);

    private XN631630Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(projectCorpInfoAO.addProjectCorpInfo(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631630Req.class);
        ObjValidater.validateReq(req);
    }

}
