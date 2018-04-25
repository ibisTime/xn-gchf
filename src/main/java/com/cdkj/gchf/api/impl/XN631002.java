/**
 * @Title XNlh5012.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午6:50:47 
 * @version V1.0   
 */
package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISYSDictAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.dto.req.XN631002Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/** 
 * 修改数据字典
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午6:50:47 
 * @history:
 */
public class XN631002 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN631002Req req = null;

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        sysDictAO.editSYSDict(StringValidater.toLong(req.getId()),
            req.getDvalue(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631002Req.class);
        ObjValidater.validateReq(req);
    }

}
