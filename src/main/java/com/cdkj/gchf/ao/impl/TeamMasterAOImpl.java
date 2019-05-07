package com.cdkj.gchf.ao.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.ITeamMasterAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.IWorkerContractBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.ImportUtil;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631650Req;
import com.cdkj.gchf.dto.req.XN631651Req;
import com.cdkj.gchf.dto.req.XN631652Req;
import com.cdkj.gchf.dto.req.XN631653Req;
import com.cdkj.gchf.dto.req.XN631653ReqData;
import com.cdkj.gchf.dto.req.XN631655Req;
import com.cdkj.gchf.dto.req.XN631908Req;
import com.cdkj.gchf.dto.req.XN631909Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.ETeamMasterUploadStatus;
import com.cdkj.gchf.enums.EUserKind;
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
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IWorkerContractBO workerContractBO;

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IPayRollBO payRollBO;

    @Autowired
    private IPayRollDetailBO payRollDetailBO;

    @Autowired
    private IProjectWorkerEntryExitHistoryBO projectWorkerEntryExitHistoryBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addTeamMaster(XN631650Req data) {
        Project project = projectBO.getProject(data.getProjectCode());
        if (project == null) {
            throw new BizException("XN631650", "请选择项目");
        }
        CorpBasicinfo corpBasicinfoByCorp = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());
        if (corpBasicinfoByCorp == null) {
            throw new BizException("XN631650", "企业信息不存在");
        }

        TeamMaster teamMaster = teamMasterBO.getTeamMasterByProject(
            data.getProjectCode(), data.getCorpCode(), data.getTeamName());
        if (null != teamMaster) {
            throw new BizException("XN631650", "班组名称已存在，请重新输入");
        }

        return teamMasterBO.saveTeamMaster(data, corpBasicinfoByCorp);
    }

    /**
     * 
     * <p>Title: dropTeamMaster</p>   
     * <p>Description: 假删除班组及向下的数据</p>   
     */
    @Transactional
    @Override
    public void dropTeamMaster(XN631651Req req) {
        List<String> codeList = req.getCodeList();
        for (String code : codeList) {
            // 校验是否上传
            TeamMaster teamMaster = teamMasterBO.getTeamMaster(code);
            if (teamMaster.getUploadStatus()
                .equals(ETeamMasterUploadStatus.UPLOAD_UPDATE.getCode())
                    || teamMaster.getUploadStatus().equals(
                        ETeamMasterUploadStatus.UPLOAD_UNUPDATE.getCode())) {
                throw new BizException("XN631651", "班组信息已上传,无法删除");
            }
            // 向下假删除所有的数据
            teamMasterBO.updateTeamMasterDeleteStatus(code,
                EDeleteStatus.DELETED.getCode());

            projectWorkerBO.fakeDeleteProjectWorkerByTeamNo(
                teamMaster.getProjectCode(), teamMaster.getCode());

            projectWorkerEntryExitHistoryBO
                .fakeDeleteProjectWorkerEntryHistoryByTeamMaster(
                    teamMaster.getCode());

            workerAttendanceBO
                .deleteWorkAttendanceByTeamMaster(teamMaster.getCode());

            ProjectWorker condition = new ProjectWorker();
            condition.setTeamSysNo(code);
            List<ProjectWorker> queryProjectWorkerList = projectWorkerBO
                .queryProjectWorkerList(condition);
            for (ProjectWorker projectWorker : queryProjectWorkerList) {
                workerContractBO
                    .fakeDeleteWorkerContract(projectWorker.getCode());
                payRollDetailBO.fakeDeletePayRollDetail(
                    projectWorker.getIdcardType(),
                    projectWorker.getIdcardNumber(),
                    teamMaster.getProjectCode());
            }
            payRollBO.updatePayRollDeleteStatus(teamMaster.getProjectCode(),
                code, teamMaster.getCorpCode());
        }

    }

    /**
     * <p>Title: editTeamMaster</p>   
     * <p>Description: </p>   
     */
    @Override
    @Transactional
    public void editTeamMaster(XN631652Req data) {
        User user = userBO.getBriefUser(data.getUserId());
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(data.getCode());
        // 上传中 或者同步中
        if (teamMaster.getUploadStatus()
            .equals(ETeamMasterUploadStatus.UPLOADING.getCode())
                || teamMaster.getUploadStatus()
                    .equals(ETeamMasterUploadStatus.UPDATEING.getCode())) {
            throw new BizException("XN631650", "该操作不支持并发、请等待上次结束后修改");
        }

        if (!data.getTeamName().equals(teamMaster.getTeamName())) {
            TeamMaster tempTeamMaster = teamMasterBO.getTeamMasterByProject(
                teamMaster.getProjectCode(), teamMaster.getCorpCode(),
                data.getTeamName());
            if (null != tempTeamMaster) {
                throw new BizException("XN631650", "班组名称已存在，请重新输入");
            }
        }

        if (teamMaster.getUploadStatus()
            .equals(ETeamMasterUploadStatus.UPLOAD_UNUPDATE.getCode())
                || teamMaster.getUploadStatus()
                    .equals(ETeamMasterUploadStatus.UPLOAD_UPDATE.getCode())) {
            // 修改本地 再修改平台
            teamMasterBO.refreshTeamMaster(data);
            operateLogBO.saveOperateLog(EOperateLogRefType.TeamMaster.getCode(),
                data.getCode(), EOperateLogOperate.EditTeamMaster.getValue(),
                user, "修改本地班组信息");

            teamMasterBO.refreshUploadStatus(data.getCode(),
                ETeamMasterUploadStatus.UPDATEING.getCode());
            XN631655Req editReq = new XN631655Req();
            BeanUtils.copyProperties(data, editReq);
            editReq.setUserId(data.getUserId());
            editReq.setCodeList(Arrays.asList(data.getCode()));
            updatePlantformTeamMaster(editReq);

        } else {
            teamMasterBO.refreshTeamMaster(data);

            operateLogBO.saveOperateLog(EOperateLogRefType.TeamMaster.getCode(),
                data.getCode(), EOperateLogOperate.EditTeamMaster.getValue(),
                user, "修改班组信息");
        }

        if (!teamMaster.getTeamName().equals(data.getTeamName())) {
            // 班组名称修改了 向下刷新所有数据班组名称
            teamMasterBO.refreshTeamMasterDown(data.getCode(),
                data.getTeamName());
        }

    }

    /**
     * 
     * <p>Title: uploadTeamMaster</p>   
     * <p>Description:上传本地数据到国家平台 </p>   
     */
    @Transactional
    @Override
    public void uploadTeamMaster(List<String> codeList, String userId) {
        User operator = userBO.getBriefUser(userId);
        for (String code : codeList) {

            TeamMaster teamMaster = getTeamMaster(code);
            if (ETeamMasterUploadStatus.UPLOAD_UPDATE.getCode()
                .equals(teamMaster.getUploadStatus())
                    || teamMaster.getUploadStatus().equals(
                        ETeamMasterUploadStatus.UPLOAD_UNUPDATE.getCode())) {
                continue;
            }
            Project project = projectBO.getProject(teamMaster.getProjectCode());
            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(teamMaster.getProjectCode());
            if (null == projectConfig) {
                throw new BizException("XN631253",
                    "项目【" + project.getName() + "】未配置,无法上传");
            }
            // 获取请求json
            String requestJson = teamMasterBO.getRequestJson(teamMaster,
                projectConfig);
            // 更新状态为上传中
            teamMasterBO.refreshUploadStatus(teamMaster.getCode(),
                ETeamMasterUploadStatus.UPLOADING.getCode());
            String resString = null;
            try {
                // 捕捉国家平台异常
                resString = GovConnecter.getGovData("Team.Add", requestJson,
                    projectConfig.getProjectCode(), projectConfig.getSecret());
            } catch (BizException e) {
                // 国家平台抛出的异常 更改为上传失败状态
                teamMasterBO.refreshUploadStatus(code,
                    ETeamMasterUploadStatus.UPLOAD_FAIL.getCode());
                e.printStackTrace();
                throw e;
            }

            // 添加操作日志
            String logCode = operateLogBO.saveOperateLog(
                EOperateLogRefType.TeamMaster.getCode(), code,
                EOperateLogOperate.UploadTeamMaster.getValue(), operator, null);

            // 添加到上传状态更新队列
            AsyncQueueHolder.addSerial(resString, projectConfig, "teamMasterBO",
                code, ETeamMasterUploadStatus.UPLOAD_UPDATE.getCode(), logCode,
                userId);
        }
    }

    /**
     * 
     * <p>Title: updatePlantformTeamMaster</p>   
     * <p>Description: 修改国家平台班组信息接口</p>   
     */
    @Override
    public void updatePlantformTeamMaster(XN631655Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        List<String> codeList = req.getCodeList();
        for (String code : codeList) {
            TeamMaster teamMaster = teamMasterBO.getTeamMaster(code);
            ProjectConfig configByLocal = projectConfigBO
                .getProjectConfigByLocal(teamMaster.getProjectCode());

            if (null == configByLocal) {

                configByLocal = projectConfigBO
                    .getProjectConfigByProject(teamMaster.getProjectCode());
                if (configByLocal == null) {
                    throw new BizException("XN631655",
                        "项目【" + teamMaster.getProjectName() + "】未配置");
                }

            }
            if (teamMaster.getUploadStatus()
                .equals(ETeamMasterUploadStatus.TO_UPLOAD.getCode())) {
                throw new BizException("XN631655", "班组信息未上传,无法修改国家平台班组信息");
            }

            XN631909Req xn631909Req = new XN631909Req();
            xn631909Req.setTeamName(teamMaster.getTeamName());
            xn631909Req
                .setTeamSysNo(Integer.parseInt(teamMaster.getTeamSysNo()));
            if (teamMaster.getEntryTime() != null) {
                xn631909Req.setEntryTime(teamMaster.getEntryTime());
            }
            if (teamMaster.getExitTime() != null) {
                xn631909Req.setExitTime(teamMaster.getExitTime());
            }
            if (StringUtils
                .isNotBlank(teamMaster.getResponsiblePersonIdcardType())) {
                xn631909Req.setResponsiblePersonIDCardType(
                    teamMaster.getResponsiblePersonIdcardType());
            }
            if (StringUtils
                .isNotBlank(teamMaster.getResponsiblePersonIdNumber())) {
                xn631909Req.setResponsiblePersonIDNumber(
                    teamMaster.getResponsiblePersonIdNumber());
            }
            if (StringUtils.isNotBlank(teamMaster.getResponsiblePersonName())) {
                xn631909Req.setResponsiblePersonName(
                    teamMaster.getResponsiblePersonName());
            }
            if (StringUtils
                .isNotBlank(teamMaster.getResponsiblePersonPhone())) {
                xn631909Req.setResponsiblePersonPhone(
                    teamMaster.getResponsiblePersonPhone());
            }
            if (StringUtils.isNotBlank(teamMaster.getRemark())) {
                xn631909Req.setRemark(teamMaster.getRemark());
            }
            if (StringUtils.isNotBlank(teamMaster.getCorpCode())) {
                xn631909Req.setCorpCode(teamMaster.getCorpCode());
            }
            if (StringUtils.isNotBlank(teamMaster.getCorpName())) {
                xn631909Req.setCorpName(teamMaster.getCorpName());
            }
            // 更新状态-同步中 修改平台信息、保存日志
            teamMasterBO.refreshUploadStatus(code,
                ETeamMasterUploadStatus.UPDATEING.getCode());
            xn631909Req.setCode(code);
            xn631909Req.setUserId(req.getUserId());
            teamMasterBO.doUpdate(xn631909Req, configByLocal);

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

        User user = userBO.getBriefUser(condition.getUserId());
        if (EUserKind.Owner.getCode().equals(user.getType())) {
            condition.setProjectCode(user.getOrganizationCode());
        }

        Paginable<TeamMaster> page = teamMasterBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (TeamMaster teamMaster : page.getList()) {
                Project project = projectBO
                    .getProject(teamMaster.getProjectCode());
                teamMaster.setProjectName(project.getName());
            }
        }

        return page;
    }

    @Override
    public List<TeamMaster> queryTeamMasterList(TeamMaster condition) {
        return teamMasterBO.queryTeamMasterList(condition);
    }

    @Override
    public TeamMaster getTeamMaster(String code) {
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(code);

        Project project = projectBO.getProject(teamMaster.getProjectCode());
        if (null != project) {
            teamMaster.setProjectName(project.getName());
        }

        return teamMaster;
    }

    /**
     * 
     * <p>Title: importTeamMaster</p>   
     * <p>Description: 导入班组信息</p>   
     */
    @Override
    public void importTeamMaster(XN631653Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        if (projectBO.getProject(req.getProjectCode()) == null) {
            throw new BizException("XN631650", "请选择项目");
        }

        String repeatTeamName = ImportUtil.checkRepeat(req.getDateList(),
            "teamName");
        if (StringUtils.isNotBlank(repeatTeamName)) {
            throw new BizException("XN631793",
                "导入数据中班组名称【" + repeatTeamName + "】存在重复");
        }

        for (XN631653ReqData reqData : req.getDateList()) {

            CorpBasicinfo corpBasicinfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(reqData.getCorpCode());
            if (corpBasicinfo == null) {
                throw new BizException("XN631650",
                    "企业信息【" + reqData.getCorpName() + "】不存在");
            }

            TeamMaster preTeamMaster = teamMasterBO.getTeamMasterByProject(
                req.getProjectCode(), reqData.getCorpCode(),
                reqData.getTeamName());
            if (null != preTeamMaster) {
                throw new BizException("XN631650",
                    "班组名称【" + reqData.getTeamName() + "】已存在，请重新输入");
            }

            TeamMaster teamMaster = new TeamMaster();
            BeanUtils.copyProperties(reqData, teamMaster);
            if (StringUtils.isNotBlank(reqData.getEntryTime())) {
                Date strToDate = DateUtil.strToDate(reqData.getEntryTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                teamMaster.setEntryTime(strToDate);
            }
            if (StringUtils.isNotBlank(reqData.getExitTime())) {
                Date strToDate = DateUtil.strToDate(reqData.getExitTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                teamMaster.setExitTime(strToDate);
            }
            teamMaster.setProjectCode(req.getProjectCode());
            teamMaster.setCorpName(corpBasicinfo.getCorpName());
            teamMaster.setResponsiblePersonIdcardType("01");
            teamMaster
                .setUploadStatus(ETeamMasterUploadStatus.TO_UPLOAD.getCode());
            teamMaster.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
            String code = teamMasterBO.saveTeamMaster(teamMaster);
            operateLogBO.saveOperateLog(EOperateLogRefType.TeamMaster.getCode(),
                code, "导入班组信息", user, null);
        }

    }

}
