package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.dto.req.XN631616Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详细查项目基本信息
 * @author: silver 
 * @since: Mar 12, 2019 5:10:46 PM 
 * @history:
 */
public class XN631616 extends AProcessor {
    private IProjectAO projectAO = SpringContextHolder
        .getBean(IProjectAO.class);

    private XN631616Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return projectAO.getProjectAndLicense(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631616Req.class);
    }
}
