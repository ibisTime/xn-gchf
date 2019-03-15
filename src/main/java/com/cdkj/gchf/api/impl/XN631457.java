package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISalaryLogAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.dto.req.XN631457Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 详情查询工资条日志
 * @author: nyc 
 * @since: 2018年5月1日 上午11:48:15 
 * @history:
 */
public class XN631457 extends AProcessor {

    private ISalaryLogAO salaryAO = SpringContextHolder
        .getBean(ISalaryLogAO.class);

    private XN631457Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return salaryAO.getSalaryLog(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631457Req.class);
    }

}
