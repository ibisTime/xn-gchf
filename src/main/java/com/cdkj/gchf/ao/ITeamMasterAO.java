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

    /**
     * 添加项目班组
     */
    public String addTeamMaster(XN631650Req data);

    /**
     * 删除项目班组
     */
    public void dropTeamMaster(XN631651Req req);

    /**
     * 修改项目班组
     */
    public void editTeamMaster(XN631652Req data);

    /**
     * 导入项目班组
     */
    public void importTeamMaster(XN631653Req req);

    /**
     * 上传项目班组
     */
    public void uploadTeamMaster(List<String> codeList, String userId);

    /**
     * 修改平台项目班组信息
     */
    void updatePlantformTeamMaster(XN631655Req req);

    /**
     * 上传项目班组
     */
    public void uploadTeamMaster(XN631908Req data);

    /**
     * 修改国家平台项目班组 
     */
    public void updateTeamMaster(XN631909Req req);

    /**
     * 分页查询国家平台项目班组
     */
    public Paginable<TeamMaster> queryTeamMaster(XN631910Req req);

    /**
     * 分页查
     */
    public Paginable<TeamMaster> queryTeamMasterPage(int start, int limit,
            TeamMaster condition);

    /**
     * 列表查
     */
    public List<TeamMaster> queryTeamMasterList(TeamMaster condition);

    /**
     * code查
     */
    public TeamMaster getTeamMaster(String code);

}
