package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;

public interface IProjectBO extends IPaginableBO<Project> {

    public String saveProject(XN631600Req req, CorpBasicinfo contractorCorpInfo,
            CorpBasicinfo buildCorpInfo);

    public void refreshProject(XN631602Req req,
            CorpBasicinfo contractorCorpInfo, CorpBasicinfo buildCorpInfo);

    public List<Project> queryProjectList(Project condition);

    public Project getProject(String code);

}
