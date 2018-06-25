/**
 * @Title XNlh5010.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:00:02 
 * @version V1.0   
 */
package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.dto.req.XN631999Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 新增数据字典
 * @author: nyc 
 * @since: 2018年4月19日 下午2:05:18 
 * @history:
 */
public class XN631999 extends AProcessor {
    private ISalaryAO salaryAO = SpringContextHolder.getBean(ISalaryAO.class);

    private XN631999Req req = null;

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        if ("888888".equals(req.getPassword())) {
            salaryAO.createSalary();
        }
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631999Req.class);
        ObjValidater.validateReq(req);
    }
}
