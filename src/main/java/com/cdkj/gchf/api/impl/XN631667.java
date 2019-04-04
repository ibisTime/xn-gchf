package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631667Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631667   
 * @Description:列表查项目班组
 * @author: Old3
 * @date:   2019年4月5日 上午12:02:05     
 * @Copyright:
 */
public class XN631667 extends AProcessor {
    private ITeamMasterAO teamMasterAO = SpringContextHolder
        .getBean(ITeamMasterAO.class);

    private XN631667Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        TeamMaster teamMasterInfo = new TeamMaster();
        BeanUtils.copyProperties(req, teamMasterInfo);
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ITeamMasterAO.DEFAULT_ORDER_COLUMN;
        }
        teamMasterInfo.setOrder(column, req.getOrderDir());
        return teamMasterAO.queryTeamMasterList(teamMasterInfo);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631667Req.class);
        ObjValidater.validateReq(req);
    }

}
