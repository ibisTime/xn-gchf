package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISkillAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631502Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改技能
 * @author: silver 
 * @since: 2018年7月12日 下午3:36:05 
 * @history:
 */
public class XN631502 extends AProcessor {

    private ISkillAO skillAO = SpringContextHolder.getBean(ISkillAO.class);

    private XN631502Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        skillAO.editSkill(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631502Req.class);
        ObjValidater.validateReq(req);
    }

}
