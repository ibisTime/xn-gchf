package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IChannelBankAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.ChannelBank;
import com.cdkj.gchf.dto.req.XN631093Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 获取银行信息
 * @author: nyc 
 * @since: 2018年4月27日 下午8:53:36 
 * @history:
 */
public class XN631093 extends AProcessor {

    private IChannelBankAO channelBankAO = SpringContextHolder
        .getBean(IChannelBankAO.class);

    private XN631093Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ChannelBank condition = new ChannelBank();
        condition.setBankCode(req.getBankCode());
        condition.setBankName(req.getBankName());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IChannelBankAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return channelBankAO.queryChannelBankList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631093Req.class);
    }

}
