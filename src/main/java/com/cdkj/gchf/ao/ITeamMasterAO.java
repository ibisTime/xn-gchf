package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631651Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631653Req;
import com.cdkj.gchf.dto.req.XN631655Req;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;

@Component
public interface ITeamMasterAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addTeamMaster(XN631650Req data);

    public void dropTeamMaster(XN631651Req req);

    public void editTeamMaster(XN631652Req data);

    public void uploadTeamMaster(List<String> codeList, String userId);

    void updatePlantformTeamMaster(XN631655Req req);

    public Paginable<TeamMaster> queryTeamMasterPage(int start, int limit,
            TeamMaster condition);

    public List<TeamMaster> queryTeamMasterList(TeamMaster condition);

    public TeamMaster getTeamMaster(String code);

    public void importTeamMaster(XN631653Req req);

    /****国家平台接口****/
    public void uploadTeamMaster(XN631908Req data);

    public void updateTeamMaster(XN631909Req req);

    public Paginable<TeamMaster> queryTeamMaster(XN631910Req req);
}
