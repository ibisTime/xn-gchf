package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.exception.BizException;

@Service
public class TeamMasterAOImpl implements ITeamMasterAO {

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    public String addTeamMaster(XN631650Req data) {
        return teamMasterBO.saveTeamMaster(data);
    }

    @Override
    public void editTeamMaster(XN631652Req data) {
        teamMasterBO.refreshTeamMaster(data);
    }

    @Override
    public void dropTeamMaster(String code) {
        teamMasterBO.removeTeamMaster(code);
    }

    @Override
    public void uploadTeamMaster(XN631908Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631908", "该项目未配置，无法上传");
        }

        teamMasterBO.doUpload(req, projectConfig);
    }

    @Override
    public void updateTeamMaster(XN631909Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631909", "该项目未配置，无法修改");
        }

        teamMasterBO.doUpdate(req, projectConfig);
    }

    @Override
    public Paginable<TeamMaster> queryTeamMaster(XN631910Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631910", "该项目未配置，无法查询");
        }

        return teamMasterBO.doQuery(req, projectConfig);

    }

    @Override
    public Paginable<TeamMaster> queryTeamMasterPage(int start, int limit,
            TeamMaster condition) {
        return teamMasterBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<TeamMaster> queryTeamMasterList(TeamMaster condition) {
        return teamMasterBO.queryTeamMasterList(condition);
    }

    @Override
    public TeamMaster getTeamMaster(String code) {
        return teamMasterBO.getTeamMaster(code);
    }

}
