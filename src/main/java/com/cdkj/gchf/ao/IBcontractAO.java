package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Bcontract;
import com.cdkj.gchf.dto.req.XN631370Req;
import com.cdkj.gchf.dto.req.XN631372Req;

@Component
public interface IBcontractAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addBcontract(XN631370Req req);

    public int editBcontract(XN631372Req req);

    public Paginable<Bcontract> queryBcontractPage(int start, int limit,
            Bcontract condition);

    public List<Bcontract> queryBcontractList(Bcontract condition);

    public Bcontract getBcontract(String code);

}
