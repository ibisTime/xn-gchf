/**
 * @Title XN805920.java 
 * @Package com.std.user.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月22日 下午2:35:40 
 * @version V1.0   
 */
package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBlacklistAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN805200Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/** 
 * 将用户拉入黑名单
 * @author: haiqingzheng 
 * @since: 2017年2月22日 下午2:35:40 
 * @history:
 */
public class XN805200 extends AProcessor {
    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805200Req req = null;

    /** 
     * @see com.std.user.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        blacklistAO.addBlacklist(req.getUserId(), req.getType(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.std.user.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805200Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getType(),
            req.getUpdater());
    }

}
