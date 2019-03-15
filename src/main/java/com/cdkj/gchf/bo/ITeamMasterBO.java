package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;

public interface ITeamMasterBO extends IPaginableBO<TeamMaster> {

    public String saveTeamMaster(TeamMaster data);

    public int removeTeamMaster(String code);

    public int refreshTeamMaster(TeamMaster data);

    public List<TeamMaster> queryTeamMasterList(TeamMaster condition);

    public TeamMaster getTeamMaster(String code);

    /****国家平台接口****/
    public void doUpload(XN631908Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631909Req req, ProjectConfig projectConfig);

    public void doQuery(XN631910Req req, ProjectConfig projectConfig);
}
