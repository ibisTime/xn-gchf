package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IWorkerContractAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.WorkerContract;
import com.cdkj.gchf.dto.req.XN631685Req;
import com.cdkj.gchf.enums.EContractPeriodType;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631685   
 * @Description:分页查询劳动合同
 * @author: Old3
 * @date:   2019年3月20日 下午7:36:27     
 * @Copyright:
 */
public class XN631685 extends AProcessor {

    private IWorkerContractAO workerContractAO = SpringContextHolder
        .getBean(IWorkerContractAO.class);

    private XN631685Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WorkerContract condition = new WorkerContract();
        BeanUtils.copyProperties(req, condition);
        condition.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        String column = req.getOrderColumn();
        if (req.getContractPeriodType() != null) {
            EContractPeriodType.checkExists(req.getContractPeriodType());
            int contractPeriod = Integer.parseInt(req.getContractPeriodType());
            condition.setContractPeriodType(contractPeriod);
        }
        if (StringUtils.isBlank(column)) {
            column = IWorkerContractAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return workerContractAO.queryWorkerContractPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631685Req.class);
        ObjValidater.validateReq(req);
    }

}
