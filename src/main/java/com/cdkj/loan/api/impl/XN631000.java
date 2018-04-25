/**
 * @Title XNlh5010.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:00:02 
 * @version V1.0   
 */
package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ISYSDictAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN631000Req;
import com.cdkj.loan.dto.res.PKIdRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 新增数据字典
 * @author: nyc 
 * @since: 2018年4月19日 下午2:05:18 
 * @history:
 */
public class XN631000 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN631000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKIdRes(sysDictAO.addSecondDict(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631000Req.class);
        ObjValidater.validateReq(req);
    }
}
