package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631653Req;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;

public interface ITeamMasterBO extends IPaginableBO<TeamMaster> {

    public String saveTeamMaster(XN631650Req data, CorpBasicinfo corpBasicinfo);

    public String saveTeamMaster(TeamMaster teamMaster);

    public void removeTeamMaster(String userId, String code);

    public void refreshTeamMaster(XN631652Req data);

    void updateTeamMasterDeleteStatus(String code, String status);

    public void refreshUploadStatus(String code, String uploadStatus);

    public void refreshTeamSysNoByLocal(String code, String teamSysNo);

    public List<TeamMaster> queryTeamMasterList(TeamMaster condition);

    String getTeamMasterNameByTeamMasterSysNo(String teamMasterSysNo);

    TeamMaster getTeamMasterByProjectCodeAndTeamMasterNameAndCorpCode(
            String ProjectCode, String TeamMasterName, String CorpCode);

    public TeamMaster getTeamMaster(String code);

    public TeamMaster getTeamMasterByCondition(TeamMaster condition);

    public void saveTeamMasterByImport(XN631653Req req);

    /****国家平台接口****/
    public void doUpload(XN631908Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631909Req req, ProjectConfig projectConfig);

    public Paginable<TeamMaster> doQuery(XN631910Req req,
            ProjectConfig projectConfig);

}
