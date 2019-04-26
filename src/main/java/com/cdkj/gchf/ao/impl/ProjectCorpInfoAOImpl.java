package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.req.XN631633Req;
import com.cdkj.gchf.dto.req.XN631633ReqList;
import com.cdkj.gchf.dto.req.XN631635Req;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectCorpType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;

@Service
public class ProjectCorpInfoAOImpl implements IProjectCorpInfoAO {

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IProjectWorkerEntryExitHistoryBO projectWorkerEntryExitHistoryBO;

    @Autowired
    private IPayRollBO payRollBO;

    @Autowired
    private IPayRollDetailBO payRollDetailBO;

    @Autowired
    private IWorkerContractBO workerContractBO;

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addProjectCorpInfo(XN631630Req data) {

        if (projectBO.getProject(data.getProjectCode()) == null) {
            throw new BizException("XN631630", "请选择项目");
        }
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());
        if (null == corpBasicinfo) {
            throw new BizException("XN631630", "企业信息不存在");
        }

        ProjectCorpInfo corpInfoByCorpCode = projectCorpInfoBO
            .getProjectCorpInfo(data.getProjectCode(), data.getCorpCode());
        if (corpInfoByCorpCode != null) {
            throw new BizException("XN631630", "参建单位已添加");
        }

        Project project = projectBO.getProject(data.getProjectCode());
        if (project == null) {
            throw new BizException("XN631630", "请选择项目");
        }

        return projectCorpInfoBO.saveProjectCorpInfo(data, project.getName());
    }

    @Transactional
    @Override
    public void dropProjectCorpInfo(List<String> codeList) {
        for (String code : codeList) {

            ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                .getProjectCorpInfo(code);
            if (EUploadStatus.UPLOAD_UPDATE.getCode()
                .equals(projectCorpInfo.getUploadStatus())
                    || EUploadStatus.UPLOAD_UNUPDATE.getCode()
                        .equals(projectCorpInfo.getUploadStatus())) {
                throw new BizException("XN631631", "参建单位已上传，无法删除");
            }

            String corpCode = projectCorpInfo.getCorpCode();
            String projectCode = projectCorpInfo.getProjectCode();

            projectCorpInfoBO.updateProjectCorpInfoDeleteStatus(code,
                EDeleteStatus.DELETED.getCode());

            projectWorkerBO.fakeDeleteProjectWorker(projectCode);

            projectWorkerEntryExitHistoryBO
                .fakeDeleteProjectWorkerEntryHistoryByProject(projectCode);

            workerAttendanceBO.fakeDeleteWorkAttendanceByProject(projectCode);

            TeamMaster condition = new TeamMaster();
            condition.setCorpCode(corpCode);
            condition.setProjectCode(projectCode);
            for (TeamMaster teamMaster : teamMasterBO
                .queryTeamMasterList(condition)) {

                PayRoll payRollCondition = new PayRoll();
                payRollCondition.setCorpCode(corpCode);
                payRollCondition.setTeamSysNo(teamMaster.getCode());
                payRollCondition.setProjectCode(projectCode);

                List<PayRoll> queryPayRollList = payRollBO
                    .queryPayRollList(payRollCondition);
                for (PayRoll payRoll : queryPayRollList) {
                    payRollDetailBO.FakeDeletePayRollDetailByPayRollCode(
                        payRoll.getCode());
                }
                payRollBO.updatePayRollDeleteStatus(projectCode,
                    teamMaster.getCode(), corpCode);
            }
            teamMasterBO.fakeDeleteTeamMaster(projectCode, corpCode);
            workerContractBO.fakeDeleteWorkerContractByProjectCode(projectCode);

        }

    }

    @Override
    @Transactional
    public void editProjectCorpInfo(XN631632Req req) {
        if (projectBO.getProject(req.getProjectCode()) == null) {
            throw new BizException("XN631630", "请选择项目");
        }
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(req.getCorpCode());
        if (null == corpBasicinfo) {
            throw new BizException("XN631630", "企业信息不存在");
        }
        EProjectCorpType.checkExists(req.getCorpType());
        if (StringUtils.isNotBlank(req.getPmIDCardType())) {
            EIdCardType.checkExists(req.getPmIDCardType());
        }
        Project project = projectBO.getProject(req.getProjectCode());

        // 项目中不存在 要修改的参建单位
        List<ProjectCorpInfo> projectCorpInfoList = projectCorpInfoBO
            .getProjectCorpInfoList(req.getProjectCode());
        for (ProjectCorpInfo projectCorpInfo : projectCorpInfoList) {
            if (projectCorpInfo.getCode().equals(req.getCode())) {
                continue;
            }
            if (projectCorpInfo.getCorpCode().equals(req.getCorpCode())) {
                throw new BizException("XN631630", "该项目下已存在该参建单位");
            }
        }
        // 修改
        projectCorpInfoBO.refreshProjectCorpInfo(req, project.getName());
        // 保存日志
        User briefUser = userBO.getBriefUser(req.getUserId());
        operateLogBO.saveOperateLog(
            EOperateLogRefType.ProjectCorpinfo.getCode(), req.getCode(),
            EOperateLogOperate.EditProjectCorpinfo.getValue(), briefUser,
            req.getRemark());

    }

    @Override
    @Transactional
    public void uploadProjectCorpInfo(String userId, List<String> codes) {
        projectCorpInfoBO.uploadProjectCorpInfo(userId, codes);
    }

    /**
     * 
     * <p>Title: updatePlantformProjectCorpInfo</p>   
     * <p>Description: 修改国家平台接口</p>   
     */
    @Transactional
    @Override
    public void updatePlantformProjectCorpInfo(XN631635Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        List<String> codeList = req.getCodeList();
        for (String code : codeList) {
            ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                .getProjectCorpInfo(code);
            if (projectCorpInfo.getUploadStatus()
                .equals(EUploadStatus.TO_UPLOAD.getCode())) {
                throw new BizException("XN631635", "参建单位未上传,无法修改平台信息");
            }
            ProjectConfig configByLocal = projectConfigBO
                .getProjectConfigByLocal(projectCorpInfo.getProjectCode());
            if (configByLocal == null) {
                throw new BizException("XN631635", "项目未配置");
            }
            // 请求国家平台刷新接口
            XN631906Req xn631906Req = new XN631906Req();
            BeanUtils.copyProperties(projectCorpInfo, xn631906Req);
            xn631906Req.setProjectCode(configByLocal.getProjectCode());
            if (projectCorpInfo.getEntryTime() != null) {
                xn631906Req.setEntryTime(projectCorpInfo.getEntryTime());
            }
            if (projectCorpInfo.getExitTime() != null) {
                xn631906Req.setExitTime(projectCorpInfo.getExitTime());
            }
            projectCorpInfoBO.doUpdate(xn631906Req, configByLocal);
            // 更新本地上传状态
            projectCorpInfoBO.refreshUploadStatus(code,
                EUploadStatus.UPLOAD_UPDATE.getCode());
            // 更新操作日志
            operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectCorpinfo.getCode(), code,
                EOperateLogOperate.UpdateProjectCorpInfo.getValue(), user,
                null);
        }

    }

    @Override
    public void uploadProjectCorpInfo(XN631905Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());
        if (null == projectConfig) {
            throw new BizException("XN631905", "该项目未配置，无法上传");
        }
        projectCorpInfoBO.doUpload(req, projectConfig);
    }

    @Override
    public void updateProjectCorpInfo(XN631906Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631906", "该项目未配置，无法修改");
        }

        projectCorpInfoBO.doUpdate(req, projectConfig);
    }

    @Override
    @Transactional
    public void importProjectCorpInfo(XN631633Req req) {
        List<XN631633ReqList> dateList = req.getDateList();
        if (projectBO.getProject(req.getProjectCode()) == null) {
            throw new BizException("XN631600", "请选择项目");
        }
        for (XN631633ReqList projectCourpInfoReq : dateList) {

            EProjectCorpType.checkExists(projectCourpInfoReq.getCorpType());
            EIdCardType.checkExists(projectCourpInfoReq.getPmIDCardType());

            if (corpBasicinfoBO.getCorpBasicinfoByCorp(
                projectCourpInfoReq.getCorpCode()) == null) {
                throw new BizException("XN631600",
                    "企业信息不存在" + projectCourpInfoReq.getCorpCode());
            }

            ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                .getProjectCorpInfo(req.getProjectCode(),
                    projectCourpInfoReq.getCorpCode());
            if (projectCorpInfo != null) {
                throw new BizException("XN631630",
                    "参建单位【" + projectCorpInfo.getCorpName() + "】已添加");
            }
        }

        User user = userBO.getBriefUser(req.getUserId());
        for (XN631633ReqList data : req.getDateList()) {
            Project project = projectBO.getProject(req.getProjectCode());
            String projectCorpInfoCode = projectCorpInfoBO
                .saveProjectCorpInfo(project, data);

            // 操作日志
            operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectCorpinfo.getCode(),
                projectCorpInfoCode,
                EOperateLogOperate.ImportProjectCorpInfo.getValue(), user,
                "批量导入参建单位信息" + projectCorpInfoCode);
        }

    }

    @Override
    public Paginable<ProjectCorpInfo> queryProjectCorpInfo(XN631907Req req) {
        if (StringUtils.isEmpty(req.getProjectCode())) {
            throw new BizException("XN63190", "请选择对应项目");
        }

        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631906", "该项目未配置，无法修改");
        }

        return projectCorpInfoBO.doQuery(req, projectConfig);
    }

    @Override
    public Paginable<ProjectCorpInfo> queryProjectCorpInfoPage(int start,
            int limit, ProjectCorpInfo condition) {

        User user = userBO.getBriefUser(condition.getUserId());
        if (EUserKind.Owner.getCode().equals(user.getType())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        return projectCorpInfoBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ProjectCorpInfo> queryProjectCorpInfoList(
            ProjectCorpInfo condition) {
        return projectCorpInfoBO.queryProjectCorpInfoList(condition);
    }

    @Override
    public ProjectCorpInfo getProjectCorpInfo(String code) {
        return projectCorpInfoBO.getProjectCorpInfo(code);
    }

}
