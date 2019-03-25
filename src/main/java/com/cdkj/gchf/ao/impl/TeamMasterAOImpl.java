package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;

@Service
public class TeamMasterAOImpl implements ITeamMasterAO {

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Override
    public String addTeamMaster(XN631650Req data) {
        return teamMasterBO.saveTeamMaster(data);
    }

    @Override
    public void dropTeamMaster(String code) {
        teamMasterBO.removeTeamMaster(code);
    }

    @Override
    public void editTeamMaster(XN631652Req data) {
        teamMasterBO.refreshTeamMaster(data);
    }

    @Override
    public void uploadTeamMaster(List<String> codeList, String userId) {

        User operator = userBO.getBriefUser(userId);

        for (String code : codeList) {
            TeamMaster teamMaster = teamMasterBO.getTeamMaster(code);

            if (EUploadStatus.UPLOAD_EDITABLE.getCode()
                .equals(teamMaster.getUploadStatus()))
                continue;

            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(teamMaster.getProjectCode());
            if (null == projectConfig) {
                throw new BizException("XN631253", "不存在已配置的项目，无法上传");
            }

            // 上传班组信息
            String resString = GovConnecter.getGovData("Team.Add",
                JSONObject.toJSONString(teamMaster),
                projectConfig.getProjectCode(), projectConfig.getSecret());

            // 添加操作日志
            String logCode = operateLogBO.saveOperateLog(
                EOperateLogRefType.TeamMaster.getCode(), code,
                EOperateLogOperate.UploadTeamMaster.getValue(), operator, null);

            // 添加到上传状态更新队列
            AsyncQueueHolder.addSerial(resString, projectConfig, "teamMasterBO",
                code, EUploadStatus.UPLOAD_EDITABLE.getCode(), logCode);

        }
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
