/**
 * @Title XNlh5014.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午8:06:49 
 * @version V1.0   
 */
package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.ISYSDictAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.domain.SYSDict;
import com.cdkj.loan.dto.req.XN631006Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 列表查询数据字典
 * @author: nyc 
 * @since: 2018年4月25日 下午3:51:16 
 * @history:
 */
public class XN631006 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN631006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSDict condition = new SYSDict();

        condition.setType(req.getType());
        condition.setParentKey(req.getParentKey());
        condition.setDkey(req.getDkey());
        condition.setUpdater(req.getUpdater());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISYSDictAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, "asc");
        return sysDictAO.querySysDictList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631006Req.class);
    }
}
