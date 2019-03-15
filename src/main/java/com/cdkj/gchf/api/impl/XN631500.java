package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISkillAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631500Req;
import com.cdkj.gchf.dto.res.PKCodeRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 添加技能
 * @author: silver 
 * @since: 2018年7月12日 下午3:24:47 
 * @history:
 */
public class XN631500 extends AProcessor {

    private ISkillAO skillAO = SpringContextHolder.getBean(ISkillAO.class);

    private XN631500Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(skillAO.saveSkill(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631500Req.class);
        ObjValidater.validateReq(req);
    }

}
