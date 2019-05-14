package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.req.XN631633ReqList;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;

public interface IProjectCorpInfoBO extends IPaginableBO<ProjectCorpInfo> {
    /**
     * 保存参建单位 
     */
    public String saveProjectCorpInfo(XN631630Req req, Project project);

    /**
     * 保存参建单位 
     */
    public String saveProjectCorpInfo(Project projectConfig,
            XN631633ReqList req);

    /**
     * 添加项目生成关联 的参建单位
     */
    String addProjectCorpInfo(CorpBasicinfo corpbasic, Project project);

    /**
     * 删除参建单位 
     */
    public void removeProjectCorpInfo(String code);

    /**
     * 修改参建单位删除状态 
     */
    void updateProjectCorpInfoDeleteStatus(String code, String status);

    /**
     * 修改参建单位 
     */
    public void refreshProjectCorpInfo(XN631632Req req, String projectName);

    /**
     * 修改上传状态 
     */
    public void refreshUploadStatus(String code, String uploadStatus);

    public List<ProjectCorpInfo> queryProjectCorpInfoList(
            ProjectCorpInfo condition);

    /**
     * 根据code 查询参建单位 
     */
    public ProjectCorpInfo getProjectCorpInfo(String code);

    /**
     * 查询参建单位 
     */
    public ProjectCorpInfo getProjectCorpInfo(String projectCode,
            String corpCode, String corpType);

    /****国家平台接口****/
    public void doUpload(XN631905Req req, ProjectConfig projectConfig);

    /**
     * 修改国家平台
     */
    public void doUpdate(XN631906Req req, ProjectConfig projectConfig);

    /**
     * 查询国家平台参建单位 
     */
    public Paginable<ProjectCorpInfo> doQuery(XN631907Req req,
            ProjectConfig projectConfig);

    /**
     * 按条件查询参建单位 
     */
    public ProjectCorpInfo getProjectCorpInfo(ProjectCorpInfo condition);

    /**
     * 根据项目编号 列表查询参建单位 
     */
    List<ProjectCorpInfo> getProjectCorpInfoList(String projectCode);

    /**
     * 查询参建单位 
     */
    public ProjectCorpInfo getProjectCorpInfo(String projectCode,
            String corpCode);

}
