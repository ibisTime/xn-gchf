package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631665Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631665   
 * @Description:分页查询项目班组
 * @author: Old3
 * @date:   2019年3月20日 下午1:55:57     
 * @Copyright:
 */
public class XN631665 extends AProcessor {

    private ITeamMasterAO teamMasterAO = SpringContextHolder
        .getBean(ITeamMasterAO.class);

    private XN631665Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        TeamMaster condition = new TeamMaster();
        BeanUtils.copyProperties(req, condition);
        String column = req.getOrderColumn();
        // 默认列
        if (StringUtils.isBlank(column)) {
            column = ITeamMasterAO.DEFAULT_ORDER_COLUMN;
        }
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return teamMasterAO.queryTeamMasterPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631665Req.class);
        ObjValidater.validateReq(req);
    }

}
