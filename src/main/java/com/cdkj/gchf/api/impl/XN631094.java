package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.dto.req.XN631094Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 更新人脸特征值
 * @author: silver 
 * @since: 2018年8月26日 下午5:18:34 
 * @history:
 */
public class XN631094 extends AProcessor {
    private IStaffAO staffAO = SpringContextHolder.getBean(IStaffAO.class);

    private XN631094Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return staffAO.refreshFeat(req.getProjectCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631094Req.class);
    }

}
