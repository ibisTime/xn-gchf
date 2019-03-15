package com.cdkj.gchf.api.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.Progress;
import com.cdkj.gchf.dto.req.XN631386Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 工程进度列表查询
 * @author: CYL 
 * @since: 2018年4月28日 下午4:48:10 
 * @history:
 */
public class XN631386 extends AProcessor {
    private IProgressAO progressAO = SpringContextHolder
        .getBean(IProgressAO.class);

    private XN631386Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Progress condition = new Progress();
        condition.setProjectCode(req.getProjectCode());
        condition.setUpdater(req.getUpdater());
        condition.setUpdateDatetime(new Date());
        condition.setKeyword(req.getKeyword());

        condition.setKind(req.getKind());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProgressAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return progressAO.queryProgressList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631386Req.class);
    }

}
