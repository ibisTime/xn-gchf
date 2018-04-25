/**
 * @Title XNlh5014.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午8:06:49 
 * @version V1.0   
 */
package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ISYSDictAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.SYSDict;
import com.cdkj.gchf.dto.req.XN631006Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/** 
 * 列表查询数据字典
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午8:06:49 
 * @history:
 */
public class XN631006 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN631006Req req = null;

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        SYSDict condition = new SYSDict();

        condition.setType(req.getType());
        condition.setParentKey(req.getParentKey());
        condition.setDkey(req.getDkey());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISYSDictAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, "asc");
        return sysDictAO.querySysDictList(condition);
    }

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631006Req.class);
    }
}
