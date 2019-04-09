package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerAttendance;
import com.cdkj.gchf.dto.req.XN631710Req;
import com.cdkj.gchf.dto.req.XN631712Req;
import com.cdkj.gchf.dto.req.XN631713Req;
import com.cdkj.gchf.dto.req.XN631713ReqData;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.dto.req.XN631918Req;
import com.cdkj.gchf.dto.req.XN631919Req;
import com.cdkj.gchf.enums.EDirectionType;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class WorkerAttendanceAOImpl implements IWorkerAttendanceAO {

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addWorkerAttendance(XN631710Req data) {
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(data.getTeamSysNo());
        if (teamMaster == null) {
            throw new BizException("XN631710", "班组信息不存在");
        }
        ProjectWorker projectWorker = projectWorkerBO
            .getProjectWorker(data.getWorkerCode());
        if (projectWorker == null) {
            throw new BizException("XN631710", "项目人员不存在");
        }
        return workerAttendanceBO.saveWorkerAttendance(data);
    }

    @Override
    public void editWorkerAttendance(XN631712Req data) {
        if (workerAttendanceBO.getWorkerAttendance(data.getCode())
            .getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631712", "人员考勤已上传,无法修改");
        }
        workerAttendanceBO.refreshWorkerAttendance(data);
    }

    @Override
    public int dropWorkerAttendance(String code) {
        WorkerAttendance workerAttendance = workerAttendanceBO
            .getWorkerAttendance(code);
        if (!workerAttendance.getUploadStatus()
            .equals(EUploadStatus.TO_UPLOAD.getCode())) {
            throw new BizException("XN631711", "人员考勤已上传，不可删除");
        }
        return workerAttendanceBO.removeWorkerAttendance(code);
    }

    @Override
    public void uploadWorkerAttendance(XN631918Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631918", "该项目未配置，无法上传");
        }

        workerAttendanceBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<WorkerAttendance> queryWorkerAttendance(XN631919Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631919", "该项目未配置，无法查询");
        }

        Paginable<WorkerAttendance> page = workerAttendanceBO.doQuery(req,
            projectConfig);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (WorkerAttendance workerAttendance : page.getList()) {

                String idcardNumber = AesUtils.decrypt(
                    workerAttendance.getIdCardNumber(),
                    projectConfig.getSecret());

                XN631913Req workerReq = new XN631913Req(req.getProjectCode(),
                    null, idcardNumber);
                workerReq.setPageIndex(0);
                workerReq.setPageSize(1);
                Paginable<ProjectWorker> projectWorker = projectWorkerBO
                    .doQuery(workerReq, projectConfig);

                if (null != projectWorker && CollectionUtils
                    .isNotEmpty(projectWorker.getList())) {
                    workerAttendance.setCorpName(
                        projectWorker.getList().get(0).getCorpName());
                    workerAttendance.setTeamName(
                        projectWorker.getList().get(0).getTeamName());
                    workerAttendance.setWorkerName(
                        projectWorker.getList().get(0).getWorkerName());
                }

                workerAttendance.setIdCardNumber(idcardNumber);
            }
        }

        return page;
    }

    @Override
    public Paginable<WorkerAttendance> queryWorkerAttendancePage(int start,
            int limit, WorkerAttendance condition) {
        return workerAttendanceBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WorkerAttendance> queryWorkerAttendanceList(
            WorkerAttendance condition) {
        return workerAttendanceBO.queryWorkerAttendanceList(condition);
    }

    @Override
    public WorkerAttendance getWorkerAttendance(String code) {
        return workerAttendanceBO.getWorkerAttendance(code);
    }

    @Override
    public void uploadWorkerAttendanceList(String userId,
            List<String> codeList) {
        for (String code : codeList) {
            WorkerAttendance workerAttendance = workerAttendanceBO
                .getWorkerAttendance(code);
            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(workerAttendance.getProjectCode());
            if (projectConfigByLocal == null) {
                throw new BizException("XN631714", "该项目未配置，无法查询");
            }
        }
        workerAttendanceBO.saveWorkerAttendanceToPlantform(userId, codeList);
    }

    @Override
    public void importWorkerAttendanceList(XN631713Req req) {

        User user = userBO.getBriefUser(req.getUpdater());
        List<XN631713ReqData> workerAttendanceList = req.getDateList();
        List<String> errorCode = new ArrayList<>();
        for (XN631713ReqData xn631713ReqData : workerAttendanceList) {
            // 校验数据字典数据
            EDirectionType.checkExists(xn631713ReqData.getDirection());
            EIdCardType.checkExists(xn631713ReqData.getIdCardType());

            // 核实企业信息
            ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
            projectCorpInfo.setCorpCode(xn631713ReqData.getCorpCode());
            projectCorpInfo.setProjectCode(req.getProjectCode());
            ProjectCorpInfo corpInfoByCorpCode = projectCorpInfoBO
                .getProjectCorpInfo(projectCorpInfo);
            if (corpInfoByCorpCode == null) {
                errorCode.add("企业信息不存在" + xn631713ReqData.getCorpCode());
                continue;
            }
            // 核实身份信息
            String idcardNumber = xn631713ReqData.getIdCardNumber();
            ProjectWorker workerByIdCardNumber = projectWorkerBO
                .getProjectWorkerByIdCardNumber(idcardNumber);
            if (workerByIdCardNumber == null) {
                errorCode.add("员工信息不存在" + idcardNumber);
                continue;
            }
            // 录入数据
            WorkerAttendance workerAttendance = new WorkerAttendance();
            BeanUtils.copyProperties(xn631713ReqData, workerAttendance);
            BeanUtils.copyProperties(workerByIdCardNumber, workerAttendance);
            TeamMaster condition = new TeamMaster();
            condition.setCorpCode(xn631713ReqData.getCorpCode());
            condition.setTeamName(xn631713ReqData.getTeamName());
            TeamMaster masterByCondition = teamMasterBO
                .getTeamMasterByCondition(condition);
            workerAttendance.setTeamSysNo(masterByCondition.getCode());
            if (StringUtils.isNotBlank(xn631713ReqData.getDate())) {
                Date date = DateUtil.strToDate(xn631713ReqData.getDate(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                workerAttendance.setDate(date);
            }

            workerAttendance.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
            String code = workerAttendanceBO
                .saveWorkerAttendance(workerAttendance);
            operateLogBO.saveOperateLog(
                EOperateLogRefType.WorkAttendance.getCode(), code, "导入人员考勤",
                user, null);
        }
        if (CollectionUtils.isNotEmpty(errorCode)) {
            throw new BizException("XN631713", errorCode.toString());
        }
    }

}
