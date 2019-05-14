package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;

public interface IProjectBO extends IPaginableBO<Project> {
    /**
     * 新增项目 
     */
    public Project saveProject(XN631600Req req,
            CorpBasicinfo contractorCorpInfo);

    /**
     * 修改项目 
     */
    public void refreshProject(XN631602Req req,
            CorpBasicinfo contractorCorpInfo);

    /**
     * 修改总承包商
     */
    public void refreshContractorCorp(String code, String contractorCorpCode,
            String contractorCorpName);

    /**
     * 修改项目密钥状态 
     */
    public void refreshSecretStatus(String code, String secretStatus);

    public List<Project> queryProjectList(Project condition);

    public Project getProject(String code);

    /**
     * @Description: 根据名称详细查询项目信息
     */
    public Project getProjectByFullName(String fullName);

}
