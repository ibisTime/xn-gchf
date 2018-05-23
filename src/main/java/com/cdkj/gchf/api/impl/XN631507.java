package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISkillAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631507Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询技能
 * @author: nyc 
 * @since: 2018年5月23日 下午4:45:53 
 * @history:
 */
public class XN631507 extends AProcessor {

    private ISkillAO skillAO = SpringContextHolder.getBean(ISkillAO.class);

    private XN631507Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return skillAO.getSalaryLog(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631507Req.class);
        ObjValidater.validateReq(req);
    }

}
