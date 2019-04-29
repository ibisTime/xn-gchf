package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;

public interface IProjectBO extends IPaginableBO<Project> {

    public String saveProject(String name);

    public String saveProject(XN631600Req req,
            CorpBasicinfo contractorCorpInfo);

    public void refreshProject(XN631602Req req,
            CorpBasicinfo contractorCorpInfo);

    public void refreshSecretStatus(String code, String secretStatus);

    public List<Project> queryProjectList(Project condition);

    public Project getProject(String code);

    public Project getProjectByFullName(String fullName);

}
