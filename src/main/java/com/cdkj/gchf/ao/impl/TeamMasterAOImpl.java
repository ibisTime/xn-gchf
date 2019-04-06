package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631651Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631653Req;
import com.cdkj.gchf.dto.req.XN631653ReqData;
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

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Override
    public String addTeamMaster(XN631650Req data) {

        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByLocal(data.getProjectCode());
        if (projectConfig == null) {
            throw new BizException("XN631650", "项目信息不存在,请检查项目编号");
        }
        CorpBasicinfo corpBasicinfoByCorp = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());
        if (corpBasicinfoByCorp == null) {
            throw new BizException("XN631650", "企业信息不存在");
        }

        return teamMasterBO.saveTeamMaster(data, corpBasicinfoByCorp);
    }

    @Override
    public void dropTeamMaster(XN631651Req req) {
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(req.getCode());
        if (teamMaster.getUploadStatus() != null & teamMaster.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631651", "班组信息已上传,无法删除");
        }
        teamMasterBO.removeTeamMaster(req.getUserId(), req.getCode());
    }

    @Override
    public void editTeamMaster(XN631652Req data) {
        User user = userBO.getBriefUser(data.getUserId());

        TeamMaster teamMaster = teamMasterBO.getTeamMaster(data.getCode());
        if (teamMaster.getUploadStatus() != null & teamMaster.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631652", "班组信息已上传,不可修改");
        }
        teamMasterBO.refreshTeamMaster(data);
        operateLogBO.saveOperateLog(EOperateLogRefType.TeamMaster.getCode(),
            data.getCode(), "修改项目班组", user, null);
    }

    @Override
    public void uploadTeamMaster(List<String> codeList, String userId) {
        User operator = userBO.getBriefUser(userId);
        for (String code : codeList) {
            TeamMaster teamMaster = getTeamMaster(code);
            if (EUploadStatus.UPLOAD_EDITABLE.getCode()
                .equals(teamMaster.getUploadStatus())) {
                continue;
            }
            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(teamMaster.getProjectCode());
            if (null == projectConfig) {
                throw new BizException("XN631253", "不存在已配置的项目，无法上传");
            }
            // 处理需要加密的信息
            if (StringUtils
                .isNotBlank(teamMaster.getResponsiblePersonIdNumber())) {
                String encryptIdCardNumber = AesUtils.encrypt(
                    teamMaster.getResponsiblePersonIdNumber(),
                    projectConfig.getSecret());
                teamMaster.setResponsiblePersonIdNumber(encryptIdCardNumber);
            }
            teamMaster.setProjectCode(projectConfig.getProjectCode());

            // 上传班组信息
            String teamMasterInfo = JSONObject
                .toJSONStringWithDateFormat(teamMaster, "yyyy-MM-dd");
            String resString = GovConnecter.getGovData("Team.Add",
                teamMasterInfo, projectConfig.getProjectCode(),
                projectConfig.getSecret());

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

    @Override
    public void importTeamMaster(XN631653Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        ProjectConfig projectConfigByProject = projectConfigBO
            .getProjectConfigByLocal(req.getProjectCode());
        if (projectConfigByProject == null) {
            throw new BizException("XN631654", "项目不存在" + req.getProjectCode());
        }
        // 根据corpCode获取企业信息
        List<XN631653ReqData> dateList = req.getDateList();
        for (XN631653ReqData xn631653ReqData : dateList) {
            // ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
            // .getProjectCorpInfoByCorpCode(xn631653ReqData.getCorpCode());
            ProjectCorpInfo condition = new ProjectCorpInfo();
            condition.setProjectCode(req.getProjectCode());
            condition.setCorpCode(xn631653ReqData.getCorpCode());
            ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                .getProjectCorpInfo(condition);
            if (projectCorpInfo == null) {
                throw new BizException("XN631653", "企业信息不存在");
            }
            TeamMaster teamMaster = new TeamMaster();
            BeanUtils.copyProperties(xn631653ReqData, teamMaster);
            if (StringUtils.isNotBlank(xn631653ReqData.getEntryTime())) {
                Date entryTime = DateUtil.strToDate(
                    xn631653ReqData.getEntryTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                teamMaster.setEntryTime(entryTime);
            }
            if (StringUtils.isNotBlank(xn631653ReqData.getExitTime())) {
                Date exitTime = DateUtil.strToDate(
                    xn631653ReqData.getExitTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                teamMaster.setEntryTime(exitTime);
            }
            teamMaster.setProjectCode(req.getProjectCode());
            teamMaster.setCorpName(projectCorpInfo.getCorpName());
            String code = teamMasterBO.saveTeamMaster(teamMaster);
            operateLogBO.saveOperateLog(EOperateLogRefType.TeamMaster.getCode(),
                code, "导入班组信息", user, null);
        }

    }

}
