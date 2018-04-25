package com.cdkj.gchf.api.impl;

import com.cdkj.gchf.ao.ISYSRoleAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.domain.SYSRole;
import com.cdkj.gchf.dto.req.XN631046Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 角色-列表查询
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:24:46 
 * @history:
 */
public class XN631046 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN631046Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSRole condition = new SYSRole();
        condition.setName(req.getName());
        condition.setUpdater(req.getUpdater());
        return sysRoleAO.querySYSRoleList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631046Req.class);
    }
}
