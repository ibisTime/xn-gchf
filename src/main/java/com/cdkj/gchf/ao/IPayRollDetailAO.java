package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRoll;

@Component
public interface IPayRollDetailAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addPayRollDetail(PayRoll data);

    public int dropPayRollDetail(String code);

    public int editPayRollDetail(PayRoll data);

    public Paginable<PayRoll> queryPayRollDetailPage(int start, int limit,
            PayRoll condition);

    public List<PayRoll> queryPayRollDetailList(PayRoll condition);

    public PayRoll getPayDetailRoll(String code);

}
