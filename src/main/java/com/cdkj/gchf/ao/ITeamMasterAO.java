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

/**
 * @author old3
 */
@Component
public interface ITeamMasterAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加项目班组
     */
    String addTeamMaster(XN631650Req data);

    /**
     * 删除项目班组
     */
    void dropTeamMaster(XN631651Req req);

    /**
     * 修改项目班组
     */
    void editTeamMaster(XN631652Req data);

    /**
     * 导入项目班组
     */
    void importTeamMaster(XN631653Req req);

    /**
     * 上传项目班组
     */
    void uploadTeamMaster(List<String> codeList, String userId);

    /**
     * 修改平台项目班组信息
     */
    void updatePlantformTeamMaster(XN631655Req req);

    /**
     * 上传项目班组
     */
    void uploadTeamMaster(XN631908Req data);

    /**
     * 修改国家平台项目班组 
     */
    void updateTeamMaster(XN631909Req req);

    /**
     * 分页查询国家平台项目班组
     */
    Paginable<TeamMaster> queryTeamMaster(XN631910Req req);

    /**
     * 分页查
     */
    Paginable<TeamMaster> queryTeamMasterPage(int start, int limit,
                                              TeamMaster condition);

    /**
     * 列表查
     */
    List<TeamMaster> queryTeamMasterList(TeamMaster condition);

    /**
     * code查
     */
    TeamMaster getTeamMaster(String code);

}
