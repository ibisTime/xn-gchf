package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;

public interface IProjectCorpInfoBO extends IPaginableBO<ProjectCorpInfo> {

    public String saveProjectCorpInfo(ProjectCorpInfo data);

    public int removeProjectCorpInfo(String code);

    public int refreshProjectCorpInfo(ProjectCorpInfo data);

    public List<ProjectCorpInfo> queryProjectCorpInfoList(
            ProjectCorpInfo condition);

    public ProjectCorpInfo getProjectCorpInfo(String code);

    /****国家平台接口****/
    public void doUpload(XN631905Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631906Req req, ProjectConfig projectConfig);

    public Paginable<ProjectCorpInfo> doQuery(XN631907Req req,
            ProjectConfig projectConfig);
}
