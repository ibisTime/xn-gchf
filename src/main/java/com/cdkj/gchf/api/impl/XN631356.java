/**
 * @Title XNlh5013.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午7:40:47 
 * @version V1.0   
 */
package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631356Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/** 
 * 分页查询项目
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午7:40:47 
 * @history:
 */
public class XN631356 extends AProcessor {
    private IProjectAO projectAO = SpringContextHolder
        .getBean(IProjectAO.class);

    private XN631356Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Project condition = new Project();
        condition.setApprover(req.getApprover());
        condition.setUpdater(req.getUpdater());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setCompanyName(req.getCompanyName());
        condition.setStatus(req.getStatus());

        condition.setKeyword(req.getKeyword());
        condition.setCompanyCodeList(req.getCompanyCodeList());
        condition.setKind(req.getKind());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IProjectAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return projectAO.queryProjectPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631356Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
