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
import com.cdkj.gchf.dto.req.XN631355Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/** 
 * 分页查询数据字典
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午7:40:47 
 * @history:
 */
public class XN631355 extends AProcessor {
    private IProjectAO projectAO = SpringContextHolder
        .getBean(IProjectAO.class);

    private XN631355Req req = null;

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Project project = new Project();
        project.setStartDatetime(req.getStartDatetime());
        project.setEndDatetime(req.getEndDatetime());
        project.setProvince(req.getProvince());
        project.setCity(req.getCity());
        project.setArea(req.getArea());
        project.setAuditor(req.getAuditor());
        project.setStatus(req.getStart());
        project.setKeyword(req.getKeyword());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IProjectAO.DEFAULT_ORDER_COLUMN;
        }
        project.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return projectAO.queryProjectPage(start, limit, project);
    }

    /** 
     * @see com.cdkj.gchf.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631355Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
