package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.dto.req.XN631765Req;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631765   
 * @Description:分页查询银行卡
 * @author: Old3
 * @date:   2019年3月22日 下午1:20:28     
 * @Copyright:
 */
public class XN631765 extends AProcessor {
    private IBankCardInfoAO bankCardInfoAO = SpringContextHolder
        .getBean(IBankCardInfoAO.class);

    private XN631765Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BankCardInfo condition = new BankCardInfo();
        BeanUtils.copyProperties(req, condition);
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        String column = null;
        if (org.apache.commons.lang3.StringUtils.isBlank(column)) {
            column = IBankCardInfoAO.DEFAULT_ORDER_COLUMN;
        }
        if (StringUtils.isNotBlank(req.getStatus())) {
            EBankCardStatus.checkExists(req.getStatus());
        }
        if (StringUtils.isNotBlank(req.getUploadStatus())) {
            EUploadStatus.checkExists(req.getUploadStatus());
        }
        condition.setOrder(column, req.getOrderDir());
        return bankCardInfoAO.queryBankCardInfoPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631765Req.class);
        ObjValidater.validateReq(req);
    }

}
