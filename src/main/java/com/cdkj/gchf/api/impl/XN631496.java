package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IQueryLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.QueryLog;
import com.cdkj.gchf.dto.req.XN631496Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查询记录
 * @author: nyc 
 * @since: 2018年5月21日 下午4:29:35 
 * @history:
 */
public class XN631496 extends AProcessor {

    private IQueryLogAO queryLogAO = SpringContextHolder
        .getBean(IQueryLogAO.class);

    private XN631496Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        QueryLog condition = new QueryLog();
        condition.setStaffName(req.getStaffName());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IQueryLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return queryLogAO.queryQueryLogList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631496Req.class);
    }

}
