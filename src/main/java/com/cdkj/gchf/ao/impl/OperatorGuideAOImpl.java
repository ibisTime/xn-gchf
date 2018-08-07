package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IOperatorGuideAO;
import com.cdkj.gchf.bo.IOperatorGuideBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.OperatorGuide;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631120Req;
import com.cdkj.gchf.dto.req.XN631122Req;
import com.cdkj.gchf.enums.EUser;

/**
 * 操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午4:57:59 
 * @history:
 */
@Service
public class OperatorGuideAOImpl implements IOperatorGuideAO {

    @Autowired
    private IOperatorGuideBO operatorGuideBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addOperatorGuide(XN631120Req req) {
        OperatorGuide data = new OperatorGuide();
        data.setTitle(req.getTitle());
        data.setContent(req.getContent());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        data.setSystemCode(req.getSystemCode());
        data.setRemark(req.getRemark());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        return operatorGuideBO.saveOperatorGuide(data);
    }

    @Override
    public void editOperatorGuide(XN631122Req req) {
        OperatorGuide data = new OperatorGuide();
        data.setCode(req.getCode());
        data.setTitle(req.getTitle());
        data.setContent(req.getContent());
        data.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        data.setRemark(req.getRemark());

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        operatorGuideBO.refreshOperatorGuide(data);
    }

    @Override
    public void dropOperatorGuide(String code) {
        operatorGuideBO.removeOperatorGuide(code);
    }

    @Override
    public Paginable<OperatorGuide> queryOperatorGuidePage(int start, int limit,
            OperatorGuide condition) {
        Paginable<OperatorGuide> page = operatorGuideBO.getPaginable(start,
            limit, condition);
        List<OperatorGuide> list = page.getList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (OperatorGuide operatorGuide : list) {
                initOperatorGuide(operatorGuide);
            }
        }
        return page;
    }

    @Override
    public List<OperatorGuide> queryOperatorGuideList(OperatorGuide condition) {
        List<OperatorGuide> list = operatorGuideBO
            .queryOperatorGuideList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (OperatorGuide operatorGuide : list) {
                initOperatorGuide(operatorGuide);
            }
        }
        return list;
    }

    @Override
    public OperatorGuide getOperatorGuide(String code) {
        OperatorGuide data = operatorGuideBO.getOperatorGuide(code);
        initOperatorGuide(data);
        return data;
    }

    private void initOperatorGuide(OperatorGuide operatorGuide) {
        operatorGuide.setUpdaterUserName(getName(operatorGuide.getUpdater()));
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;

    }
}
