package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ITeamMasterDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;

@Component
public class TeamMasterBOImpl extends PaginableBOImpl<TeamMaster>
        implements ITeamMasterBO {

    @Autowired
    private ITeamMasterDAO teamMasterDAO;

    @Override
    public String saveTeamMaster(XN631650Req data) {
        TeamMaster teamMasterInfo = new TeamMaster();
        BeanUtils.copyProperties(data, teamMasterInfo);

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.TeamMaster.getCode());
        teamMasterInfo.setCode(code);
        teamMasterDAO.insert(teamMasterInfo);

        return code;
    }

    @Override
    public void removeTeamMaster(String code) {
        TeamMaster data = new TeamMaster();

        data.setCode(code);

        teamMasterDAO.delete(data);
    }

    @Override
    public void refreshTeamMaster(XN631652Req data) {
        TeamMaster teamMaster = new TeamMaster();
        BeanUtils.copyProperties(data, teamMaster);

        teamMasterDAO.update(teamMaster);
    }

    @Override
    public void refreshUploadStatus(String code, String uploadStatus) {
        TeamMaster teamMaster = new TeamMaster();

        teamMaster.setCode(code);
        teamMaster.setUploadStatus(uploadStatus);

        teamMasterDAO.updateUploadStatus(teamMaster);
    }

    @Override
    public void refreshTeamSysNoByLocal(String code, String teamSysNo) {
        TeamMaster teamMaster = new TeamMaster();

        teamMaster.setCode(code);
        teamMaster.setTeamSysNo(teamSysNo);

        teamMasterDAO.updateTeamSysNo(teamMaster);
    }

    @Override
    public void doUpload(XN631908Req req, ProjectConfig projectConfig) {

        if (StringUtils.isNotBlank(req.getResponsiblePersonIdNumber())) {
            req.setResponsiblePersonIdNumber(AesUtils.encrypt(
                req.getResponsiblePersonIdNumber(), projectConfig.getSecret()));
        }

        if (StringUtils.isNotBlank(req.getTeamLeaderIdNumber())) {
            req.setTeamLeaderIdNumber(AesUtils.encrypt(
                req.getTeamLeaderIdNumber(), projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("Team.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public void doUpdate(XN631909Req req, ProjectConfig projectConfig) {

        if (StringUtils.isNotBlank(req.getResponsiblePersonIdNumber())) {
            req.setResponsiblePersonIdNumber(AesUtils.encrypt(
                req.getResponsiblePersonIdNumber(), projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("Team.Update", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
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

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (TeamMaster master : page.getList()) {
                if (StringUtils
                    .isNotBlank(master.getResponsiblePersonIdNumber())) {
                    master.setResponsiblePersonIdNumber(
                        AesUtils.decrypt(master.getResponsiblePersonIdNumber(),
                            projectConfig.getSecret()));
                }
            }
        }

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

    @Override
    public TeamMaster getTeamMasterByCondition(TeamMaster condition) {
        return teamMasterDAO.select(condition);
    }

}
