package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ITeamMasterDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;

@Component
public class TeamMasterBOImpl extends PaginableBOImpl<TeamMaster>
        implements ITeamMasterBO {

    @Autowired
    private ITeamMasterDAO teamMasterDAO;

    @Override
    public String saveTeamMaster(TeamMaster data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.TeamMaster.getCode());
            data.setCode(code);
            teamMasterDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeTeamMaster(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            TeamMaster data = new TeamMaster();
            data.setCode(code);
            count = teamMasterDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshTeamMaster(TeamMaster data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = teamMasterDAO.update(data);
        }
        return count;
    }

    @Override
    public void doUpload(XN631908Req req, ProjectConfig projectConfig) {

        TeamMaster teamMaster = new TeamMaster();
        BeanUtils.copyProperties(req, teamMaster);

        String data = JSONObject.toJSON(teamMaster).toString();

        GovConnecter.getGovData("Team.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public void doUpdate(XN631909Req req, ProjectConfig projectConfig) {
        TeamMaster teamMaster = new TeamMaster();
        BeanUtils.copyProperties(req, teamMaster);

        String data = JSONObject.toJSON(teamMaster).toString();

        GovConnecter.getGovData("Team.Update", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public Paginable<TeamMaster> doQuery(XN631910Req req,
            ProjectConfig projectConfig) {
        TeamMaster teamMaster = new TeamMaster();
        BeanUtils.copyProperties(req, teamMaster);

        String data = JSONObject.toJSON(teamMaster).toString();

        String queryString = GovConnecter.getGovData("Team.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<TeamMaster> page = GovUtil.parseGovPage(req.getPageIndex(),
            req.getPageSize(), queryString, replaceMap, TeamMaster.class);

        return page;
    }

    @Override
    public List<TeamMaster> queryTeamMasterList(TeamMaster condition) {
        return teamMasterDAO.selectList(condition);
    }

    @Override
    public TeamMaster getTeamMaster(String code) {
        TeamMaster data = null;
        if (StringUtils.isNotBlank(code)) {
            TeamMaster condition = new TeamMaster();
            condition.setCode(code);
            data = teamMasterDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "班组编号不存在");
            }
        }
        return data;
    }

}