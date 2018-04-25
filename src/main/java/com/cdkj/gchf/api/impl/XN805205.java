/**
 * @Title XN805205.java 
 * @Package com.std.user.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月23日 下午2:53:59 
 * @version V1.0   
 */
package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IBlacklistAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Blacklist;
import com.cdkj.gchf.dto.req.XN805205Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/** 
 * 分页查询黑名单
 * @author: haiqingzheng 
 * @since: 2017年8月23日 下午2:53:59 
 * @history:
 */
public class XN805205 extends AProcessor {

    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805205Req req = null;

    /** 
     * @see com.std.user.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Blacklist condition = new Blacklist();
        condition.setUserId(req.getUserId());
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setSystemCode(req.getSystemCode());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return blacklistAO.queryBlacklistPage(start, limit, condition);
    }

    /** 
     * @see com.std.user.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805205Req.class);
        StringValidater.validateBlank(req.getStart(), req.getLimit());
    }

}
