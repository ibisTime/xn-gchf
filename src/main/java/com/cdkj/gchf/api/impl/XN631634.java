package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631634Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631634   
 * @Description:上传参建设单位
 * @author: Old3
 * @date:   2019年3月25日 上午10:01:05     
 * @Copyright:
 */
public class XN631634 extends AProcessor {
    private IProjectCorpInfoAO projectCorpInfoAO = SpringContextHolder
        .getBean(IProjectCorpInfoAO.class);

    private XN631634Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectCorpInfoAO.uploadProjectCorpInfo(req.getUserId(),
            req.getCodeList());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631634Req.class);
        ObjValidater.validateReq(req);
    }

}
