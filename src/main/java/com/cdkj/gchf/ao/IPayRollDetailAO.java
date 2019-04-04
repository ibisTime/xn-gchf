package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631810Req;

@Component
public interface IPayRollDetailAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPayRollDetail(PayRollDetail data);

    public int dropPayRollDetail(String code);

    public int editPayRollDetail(XN631810Req req);

    public Paginable<PayRollDetail> queryPayRollDetailPage(int start, int limit,
            PayRollDetail condition);

    public List<PayRollDetail> queryPayRollDetailList(PayRollDetail condition);

    public PayRollDetail getPayDetailRoll(String code);

}
