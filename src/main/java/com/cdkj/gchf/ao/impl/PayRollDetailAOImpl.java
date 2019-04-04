package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IPayRollDetailAO;
import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631810Req;

@Service
public class PayRollDetailAOImpl implements IPayRollDetailAO {
    @Autowired
    private IPayRollDetailBO payRollDetailBO;

    @Override
    public String addPayRollDetail(PayRollDetail data) {
        return null;
    }

    @Override
    public int dropPayRollDetail(String code) {
        return 0;
    }

    @Override
    public PayRollDetail getPayDetailRoll(String code) {
        return payRollDetailBO.getPayRollDetail(code);
    }

    @Override
    public Paginable<PayRollDetail> queryPayRollDetailPage(int start, int limit,
            PayRollDetail condition) {
        Paginable<PayRollDetail> page = payRollDetailBO.getPaginable(start,
            limit, condition);
        return page;
    }

    @Override
    public List<PayRollDetail> queryPayRollDetailList(PayRollDetail condition) {
        return payRollDetailBO.queryList(condition);
    }

    @Override
    public int editPayRollDetail(XN631810Req req) {
        return payRollDetailBO.updatePayRollDetail(req);
    }

}
