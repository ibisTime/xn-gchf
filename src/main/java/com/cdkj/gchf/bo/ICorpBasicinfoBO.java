package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;
import com.cdkj.gchf.dto.req.XN631900Req;
import com.cdkj.gchf.dto.req.XN631901Req;

public interface ICorpBasicinfoBO extends IPaginableBO<CorpBasicinfo> {

    public String saveCorpBasicinfo(XN631250Req req);

    public int removeCorpBasicinfo(String code);

    public void refreshCorpBasicinfo(XN631251Req data);

    public List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition);

    public CorpBasicinfo getCorpBasicinfo(String code);

    public CorpBasicinfo getCorpBasicinfoByCorp(String corpCode);

    /****国家平台接口****/
    public void doUpload(XN631900Req req, ProjectConfig projectConfig);

    public void doQuery(XN631901Req req, ProjectConfig projectConfig);
}
