package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.req.XN631633Req;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;

@Component
public interface IProjectCorpInfoAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProjectCorpInfo(XN631630Req data);

    public void dropProjectCorpInfo(String code);

    public void editProjectCorpInfo(XN631632Req req);

    public void uploadProjectCorpInfo(String userId, List<String> codes);

    public ProjectCorpInfo getProjectCorpInfo(String code);

    public Paginable<ProjectCorpInfo> queryProjectCorpInfoPage(int start,
            int limit, ProjectCorpInfo condition);

    public List<ProjectCorpInfo> queryProjectCorpInfoList(
            ProjectCorpInfo condition);

    public void importProjectCorpInfo(XN631633Req req);

    /****国家平台接口****/
    public void uploadProjectCorpInfo(XN631905Req data);

    public void updateProjectCorpInfo(XN631906Req req);

    public Paginable<ProjectCorpInfo> queryProjectCorpInfo(XN631907Req req);

}
