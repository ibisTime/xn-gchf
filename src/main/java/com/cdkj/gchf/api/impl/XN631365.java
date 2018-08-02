package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IProjectCardAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.ProjectCard;
import com.cdkj.gchf.dto.req.XN631365Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 分页查公司账户
 * @author: nyc 
 * @since: 2018年4月27日 下午9:46:22 
 * @history:
 */
public class XN631365 extends AProcessor {

    private IProjectCardAO projectCardAO = SpringContextHolder
        .getBean(IProjectCardAO.class);

    private XN631365Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ProjectCard condition = new ProjectCard();

        condition.setKeyword(req.getKeyword());
        condition.setProjectCode(req.getProjectCode());
        condition.setStatus(req.getStatus());
        condition.setKind(req.getKind());
        condition.setProjectCodeList(req.getProjectCodeList());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IProjectCardAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return projectCardAO.queryProjectCardPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631365Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
