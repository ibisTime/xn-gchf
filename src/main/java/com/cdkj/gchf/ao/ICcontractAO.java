package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Ccontract;
import com.cdkj.gchf.dto.req.XN631400Req;
import com.cdkj.gchf.dto.req.XN631402Req;

@Component
public interface ICcontractAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCcontract(XN631400Req req);

    public void dropCcontract(String code);

    public void editCcontract(XN631402Req req);

    public Paginable<Ccontract> queryCcontractPage(int start, int limit,
            Ccontract condition);

    public List<Ccontract> queryCcontractList(Ccontract condition);

    public Ccontract getCcontract(String code);

}
