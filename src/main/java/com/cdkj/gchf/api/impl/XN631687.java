package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631687Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631687   
 * @Description:列表查询劳动合同
 * @author: Old3
 * @date:   2019年3月20日 下午7:42:16     
 * @Copyright:
 */
public class XN631687 extends AProcessor {
    private IWorkerContractAO workerContractAO = SpringContextHolder
        .getBean(IWorkerContractAO.class);

    private XN631687Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WorkerContract condition = new WorkerContract();
        BeanUtils.copyProperties(req, condition);
        String column = null;
        if (StringUtils.isBlank(column)) {
            column = IWorkerContractAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderColumn());
        return workerContractAO.queryWorkerContractList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631687Req.class);
        ObjValidater.validateReq(req);
    }

}
