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
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 修改项目
 * @author: CYL 
 * @since: 2018年4月26日 下午8:57:48 
 * @history:
 */
public class XN631352 extends AProcessor {
    private IProjectAO projectAO = SpringContextHolder
        .getBean(IProjectAO.class);

    private XN631352Req req = null;

    /** (StringValidater.toLong(req.getId()),
            req.getDvalue(), req.getUpdater(), req.getRemark());
     * @see com.cdkj.gchf.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        projectAO.editProject(req);
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631352Req.class);
        ObjValidater.validateReq(req);
    }

}
