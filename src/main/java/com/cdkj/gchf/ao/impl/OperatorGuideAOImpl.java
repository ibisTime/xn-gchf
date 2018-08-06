package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IOperatorGuideAO;
import com.cdkj.gchf.bo.IOperatorGuideBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.OperatorGuide;
import com.cdkj.gchf.dto.req.XN631120Req;
import com.cdkj.gchf.dto.req.XN631122Req;

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

    @Override
    public String addOperatorGuide(XN631120Req req) {
        OperatorGuide data = new OperatorGuide();
        data.setTitle(req.getTitle());
        data.setContent(req.getContent());
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
        return operatorGuideBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<OperatorGuide> queryOperatorGuideList(OperatorGuide condition) {
        return operatorGuideBO.queryOperatorGuideList(condition);
    }

    @Override
    public OperatorGuide getOperatorGuide(String code) {
        return operatorGuideBO.getOperatorGuide(code);
    }
}
