/**
 * @Title XNlh5012.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午6:50:47 
 * @version V1.0   
 */
package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631353Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 提请审核项目
 * @author: CYL 
 * @since: 2018年4月26日 下午8:57:48 
 * @history:
 */
public class XN631353 extends AProcessor {

    private IProjectAO projectAO = SpringContextHolder
        .getBean(IProjectAO.class);

    private XN631353Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        projectAO.toApprove(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631353Req.class);
        ObjValidater.validateReq(req);
    }

}
