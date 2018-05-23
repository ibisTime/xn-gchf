package com.cdkj.gchf.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.gchf.ao.ISkillAO;
import com.cdkj.gchf.api.AProcessor;
import com.cdkj.gchf.common.JsonUtil;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Skill;
import com.cdkj.gchf.dto.req.XN631506Req;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.ParaException;
import com.cdkj.gchf.spring.SpringContextHolder;

/**
 * 列表查询技能
 * @author: nyc 
 * @since: 2018年5月23日 下午4:45:53 
 * @history:
 */
public class XN631506 extends AProcessor {

    private ISkillAO skillAO = SpringContextHolder.getBean(ISkillAO.class);

    private XN631506Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Skill condition = new Skill();
        condition.setStaffCode(req.getStaffCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISkillAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return skillAO.querySalaryLogList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN631506Req.class);
        StringValidater.validateBlank(req.getStaffCode());
    }

}
