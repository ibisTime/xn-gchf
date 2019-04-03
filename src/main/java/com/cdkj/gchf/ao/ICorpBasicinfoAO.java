package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;
import com.cdkj.gchf.dto.req.XN631900Req;
import com.cdkj.gchf.dto.req.XN631901Req;

@Component
public interface ICorpBasicinfoAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCorpBasicinfo(XN631250Req req);

    public int dropCorpBasicinfo(String code);

    public void editCorpBasicinfo(XN631251Req data);

    void uploadCorpBasicinfo(List<String> codeList, String userId);

    public Paginable<CorpBasicinfo> queryCorpBasicinfoPage(int start, int limit,
            CorpBasicinfo condition);

    public List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition);

    public CorpBasicinfo getCorpBasicinfo(String code);

    /****国家平台接口****/
    public void uploadCorpBasicinfo(XN631900Req data);

    public Paginable<CorpBasicinfo> queryCorpBasicinfo(XN631901Req req);
}
