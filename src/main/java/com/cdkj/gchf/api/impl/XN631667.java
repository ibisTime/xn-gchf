package com.cdkj.gchf.api.impl;

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

public class XN631667 extends AProcessor {
    private ITeamMasterAO teamMasterAO = SpringContextHolder
        .getBean(ITeamMasterAO.class);

    private XN631667Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        req.getOrderColumn();
        TeamMaster teamMasterInfo = new TeamMaster();
        BeanUtils.copyProperties(req, teamMasterInfo);
        String column = req.getOrderColumn();
        if (column == null) {
            column = ITeamMasterAO.DEFAULT_ORDER_COLUMN;
        }
        return teamMasterAO.queryTeamMasterList(teamMasterInfo);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631667Req.class);
        ObjValidater.validateReq(req);
    }

}
