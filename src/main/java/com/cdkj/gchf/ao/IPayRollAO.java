package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;

@Component
public interface IPayRollAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPayRoll(PayRoll data);

    public int dropPayRoll(String code);

    public int editPayRoll(PayRoll data);

    public Paginable<PayRoll> queryPayRollPage(int start, int limit,
            PayRoll condition);

    public List<PayRoll> queryPayRollList(PayRoll condition);

    public PayRoll getPayRoll(String code);

    /****国家平台接口****/
    public void uploadPayRoll(XN631920Req data);

    public Paginable<PayRoll> queryPayRoll(XN631921Req req);
}
