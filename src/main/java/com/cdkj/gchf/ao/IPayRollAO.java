package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;

@Component
public interface IPayRollAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPayRoll(XN631770Req req);

    public int dropPayRoll(String code);

    public int editPayRoll(XN631772Req req);

    public Paginable<PayRoll> queryPayRollPage(int start, int limit,
            PayRoll condition);

    public List<PayRoll> queryPayRollList(PayRoll condition);

    public PayRoll getPayRoll(String code);

    public void uploadPayRollList(String userId, List<String> codeList);

    public void refreshPayRollCodeByLocal(String code, String payRollCode);

    /****国家平台接口****/
    public void uploadPayRoll(XN631920Req data);

    public Paginable<PayRollDetail> queryPayRoll(XN631921Req req);
}
