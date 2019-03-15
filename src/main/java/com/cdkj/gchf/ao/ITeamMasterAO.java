package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;

@Component
public interface ITeamMasterAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addTeamMaster(TeamMaster data);

    public int dropTeamMaster(String code);

    public int editTeamMaster(TeamMaster data);

    public Paginable<TeamMaster> queryTeamMasterPage(int start, int limit,
            TeamMaster condition);

    public List<TeamMaster> queryTeamMasterList(TeamMaster condition);

    public TeamMaster getTeamMaster(String code);

    /****国家平台接口****/
    public void uploadTeamMaster(XN631908Req data);

    public void updateTeamMaster(XN631909Req req);

    public Paginable<TeamMaster> queryTeamMaster(XN631910Req req);
}
