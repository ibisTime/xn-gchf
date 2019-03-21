package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.ObjValidater;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631666Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 
 * @ClassName:  XN631666   
 * @Description:详细查项目班组
 * @author: Old3
 * @date:   2019年3月20日 下午2:05:12     
 * @Copyright:
 */
public class XN631666 extends AProcessor {

    private ITeamMasterAO teamMasterAO = SpringContextHolder
        .getBean(ITeamMasterAO.class);

    private XN631666Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        TeamMaster teamMasterInfo = new TeamMaster();
        teamMasterInfo.setCode(req.getCode());
        return teamMasterAO.queryTeamMasterList(teamMasterInfo);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631666Req.class);
        ObjValidater.validateReq(req);
    }

}
