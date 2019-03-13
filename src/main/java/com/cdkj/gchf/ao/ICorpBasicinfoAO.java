package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;

@Component
public interface ICorpBasicinfoAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCorpBasicinfo(XN631250Req req);

    public int dropCorpBasicinfo(String code);

    public void editCorpBasicinfo(XN631251Req data);

    void uploadCorpBasicinfo(String code);

    void syncCorpBasicinfo(String code);

    public Paginable<CorpBasicinfo> queryCorpBasicinfoPage(int start, int limit,
            CorpBasicinfo condition);

    public List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition);

    public CorpBasicinfo getCorpBasicinfo(String code);

}
