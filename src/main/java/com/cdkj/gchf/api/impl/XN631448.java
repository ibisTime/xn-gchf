package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.dto.req.XN631448Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 查询项目每月累计薪资
 * @author: silver 
 * @since: 2018年6月28日 下午6:00:14 
 * @history:
 */
public class XN631448 extends AProcessor {

    private ISalaryAO salaryAO = SpringContextHolder.getBean(ISalaryAO.class);

    private XN631448Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return salaryAO.getMohtnSalarySumByProject(req.getProjectCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631448Req.class);
    }

}
