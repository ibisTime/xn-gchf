package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631653Req;
import com.cdkj.gchf.dto.req.XN631653ReqData;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;

public interface ITeamMasterBO extends IPaginableBO<TeamMaster> {
    /**
     * 保存项目班组
     */
    public String saveTeamMaster(XN631650Req data, CorpBasicinfo corpBasicinfo);


    /**
     * 保存项目班组
     */
    String saveTeamMaster(XN631653ReqData data, String corpName,
                          XN631653Req req);

    /**
     * 批量插入班组
     * @param data data
     * @param corpName 企业名称list
     * @param req req
     */
    void batchSaveTeamMaster(User user, List<XN631653ReqData> data, List<String> corpName, XN631653Req req);

    /**
     * 假删除班组信息
     */
    void fakeDeleteTeamMaster(String projectCode, String corpCode);

    /**
     * 修改项目班组
     */
    void refreshTeamMaster(XN631652Req data);

    /**
     * 修改班组删除状态
     */
    void updateTeamMasterDeleteStatus(String code, String status);

    /**
     * 更新上传状态
     */
    public void refreshUploadStatus(String code, String uploadStatus);

    /**
     * 获取上传国家平台json
     */
    String getRequestJson(TeamMaster teamMaster, ProjectConfig projectConfig);

    /**
     * 向下更新班组名称
     */
    void refreshTeamMasterDown(String localTeamNO, String teamName);

    /**
     * 反射修改本地国家平台编号
     */
     void refreshTeamSysNoByLocal(String code, String teamSysNo);

    /**
     * code查
     */
     TeamMaster getTeamMaster(String code);

    /**
     * 条件查班组信息
     */
     List<TeamMaster> queryTeamMasterList(TeamMaster condition);


    /**
     * 查询项目班
     */
    TeamMaster getTeamMasterByProject(String ProjectCode, String corpCode,
                                      String TeamMasterName);

    /**
     * 根据项目编号查询班组
     */
    List<TeamMaster> queryTeamMasterByProject(String projectCode,
                                              String corpCode);

    /**
     * 条件查
     */
    TeamMaster getTeamMasterByCondition(TeamMaster condition);

    /****v200_国家平台接口****/
    public void doUpload(XN631908Req req, ProjectConfig projectConfig);

    public void doUpdate(XN631909Req req, ProjectConfig projectConfig);

    public Paginable<TeamMaster> doQuery(XN631910Req req,
                                         ProjectConfig projectConfig);

}
