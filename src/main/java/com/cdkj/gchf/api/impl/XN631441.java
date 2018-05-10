package com.cdkj.gchf.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.dto.req.XN631441Req;
import com.cdkj.gchf.dto.res.BooleanRes;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 补充扣款金
 * @author: nyc 
 * @since: 2018年5月3日 下午7:37:02 
 * @history:
 */
public class XN631441 extends AProcessor {

    private ISalaryAO salayAO = SpringContextHolder.getBean(ISalaryAO.class);

    private XN631441Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        salayAO.cutAmount(req.getList());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631441Req.class);
        if (CollectionUtils.isEmpty(req.getList())) {
            throw new BizException("xn00000", "工资条不能为空");
        }
    }

}
