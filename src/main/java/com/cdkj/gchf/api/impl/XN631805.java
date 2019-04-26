package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631805Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.http.JsonUtils;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631805   
 * @Description: 分页查实名认证信息
 * @author: Old3
 * @date:   2019年3月25日 下午4:36:43     
 * @Copyright:
 */
public class XN631805 extends AProcessor {
    private IWorkerInfoAO workerInfoAO = SpringContextHolder
        .getBean(IWorkerInfoAO.class);

    private XN631805Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        WorkerInfo workerInfo = new WorkerInfo();
        BeanUtils.copyProperties(req, workerInfo);

        if (StringUtils.isNotBlank(req.getCreateDatetimeStart())) {
            workerInfo.setCreateDatetimeStart(
                DateUtil.strToDate(req.getCreateDatetimeStart(),
                    DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(req.getCreateDatetimeEnd())) {
            workerInfo.setCreateDatetimeEnd(DateUtil.strToDate(
                req.getCreateDatetimeEnd(), DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        String orderColumn = req.getOrderColumn();
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IWorkerInfoAO.DEFAULT_ORDER_COLUMN;
        }
        workerInfo.setOrder(orderColumn, req.getOrderDir());

        return workerInfoAO.queryWorkerInfoPage(req.getUserId(), start, limit,
            workerInfo);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtils.json2Bean(inputparams, XN631805Req.class);
        ObjValidater.validateReq(req);
    }

}
