package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectCorpInfoDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.req.XN631633ReqList;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectCorpType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;

@Component
public class ProjectCorpInfoBOImpl extends PaginableBOImpl<ProjectCorpInfo>
        implements IProjectCorpInfoBO {

    @Autowired
    private IProjectCorpInfoDAO projectCorpInfoDAO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Override
    public String saveProjectCorpInfo(XN631630Req req, String projectName) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        EProjectCorpType.checkExists(req.getCorpType());
        BeanUtils.copyProperties(req, projectCorpInfo);
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(req.getCorpCode());
        projectCorpInfo.setCorpName(corpBasicinfo.getCorpName());
        if (StringUtils.isNotBlank(req.getPmIDCardType())) {
            EIdCardType.checkExists(req.getPmIDCardType());
        }
        // ProjectConfig configByLocal = projectConfigBO
        // .getProjectConfigByLocal(req.getProjectCode());
        // if (configByLocal == null) {
        // throw new BizException("XN631630", "项目不存在");
        // }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectCorpInfo.getCode());
        projectCorpInfo.setCode(code);
        projectCorpInfo.setProjectName(projectName);
        projectCorpInfo.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        projectCorpInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        projectCorpInfoDAO.insert(projectCorpInfo);

        return code;
    }

    @Override
    public void removeProjectCorpInfo(String code) {
        if (StringUtils.isNotBlank(code)) {
            ProjectCorpInfo data = new ProjectCorpInfo();
            data.setCode(code);
            projectCorpInfoDAO.delete(data);
        }
    }

    @Override
    public void refreshProjectCorpInfo(XN631632Req req, String projectName) {
        ProjectCorpInfo condition = new ProjectCorpInfo();
        condition.setCode(req.getCode());
        BeanUtils.copyProperties(req, condition);
        condition.setProjectName(projectName);
        projectCorpInfoDAO.update(condition);
    }

    @Override
    public void refreshUploadStatus(String code, String uploadStatus) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();

        projectCorpInfo.setCode(code);
        projectCorpInfo.setUploadStatus(uploadStatus);

        projectCorpInfoDAO.updateUploadStatus(projectCorpInfo);
    }

    @Override
    public void doUpload(XN631905Req req, ProjectConfig projectConfig) {

        if (StringUtils.isNotBlank(req.getPmIdcardNumber())) {
            req.setPmIdcardNumber(AesUtils.encrypt(req.getPmIdcardNumber(),
                projectConfig.getSecret()));
        }

        String data = JSONObject
            .toJSONStringWithDateFormat(req, "yyyy-MM-dd HH:mm:ss").toString();

        GovConnecter.getGovData("ProjectSubContractor.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public void doUpdate(XN631906Req req, ProjectConfig projectConfig) {

        if (StringUtils.isNotBlank(req.getPmIdcardNumber())) {
            req.setPmIdcardNumber(AesUtils.encrypt(req.getPmIdcardNumber(),
                projectConfig.getSecret()));
        }

        String data = JSONObject
            .toJSONStringWithDateFormat(req, "yyyy-MM-dd HH:mm:ss").toString();

        GovConnecter.getGovData("ProjectSubContractor.Update", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public Paginable<ProjectCorpInfo> doQuery(XN631907Req req,
            ProjectConfig projectConfig) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        BeanUtils.copyProperties(req, projectCorpInfo);

        String data = JSONObject.toJSON(projectCorpInfo).toString();

        String queryString = GovConnecter.getGovData(
            "ProjectSubContractor.Query", data, projectConfig.getProjectCode(),
            projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<ProjectCorpInfo> page = GovUtil.parseGovPage(
            req.getPageIndex(), req.getPageSize(), queryString, replaceMap,
            ProjectCorpInfo.class);

        return page;
    }

    @Override
    public List<ProjectCorpInfo> queryProjectCorpInfoList(
            ProjectCorpInfo condition) {
        return projectCorpInfoDAO.selectList(condition);
    }

    @Override
    public ProjectCorpInfo getProjectCorpInfo(String code) {
        ProjectCorpInfo data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectCorpInfo condition = new ProjectCorpInfo();
            condition.setCode(code);
            data = projectCorpInfoDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "项目参建单位不存在");
            }
        }
        return data;
    }

    @Override
    public String saveProjectCorpInfo(ProjectConfig projectConfig,
            XN631633ReqList req) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectCorpInfo.getCode());
        projectCorpInfo.setCode(code);

        BeanUtils.copyProperties(req, projectCorpInfo);
        projectCorpInfo.setCorpCode(req.getCorpCode());
        projectCorpInfo.setCorpName(req.getCorpName());
        projectCorpInfo.setProjectCode(projectConfig.getLocalProjectCode());
        projectCorpInfo.setProjectName(projectConfig.getProjectName());
        projectCorpInfo.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        projectCorpInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        if (StringUtils.isNotBlank(req.getEntryTime())) {
            Date entryTime = DateUtil.strToDate(req.getEntryTime(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            projectCorpInfo.setEntryTime(entryTime);
        }
        if (StringUtils.isNotBlank(req.getExitTime())) {
            Date exitTime = DateUtil.strToDate(req.getExitTime(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            projectCorpInfo.setEntryTime(exitTime);
        }

        projectCorpInfoDAO.insert(projectCorpInfo);

        return code;
    }

    @Override
    public ProjectCorpInfo getProjectCorpInfo(ProjectCorpInfo condition) {
        return projectCorpInfoDAO.select(condition);
    }

    @Override
    public void uploadProjectCorpInfo(String userId, List<String> codes) {
        User briefUser = userBO.getBriefUser(userId);
        for (String code : codes) {
            ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                .getProjectCorpInfo(code);
            if (EUploadStatus.UPLOAD_EDITABLE.getCode()
                .equals(projectCorpInfo.getUploadStatus()))
                continue;
            // 调用国家平台上传数据
            ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(projectCorpInfo.getProjectCode());
            if (null == projectConfig) {
                throw new BizException("XN631634", "不存在已配置的项目，无法上传");
            }

            if (StringUtils.isNotBlank(projectCorpInfo.getPmIDCardNumber())) {
                String pmIDCardNumber = AesUtils.encrypt(
                    projectCorpInfo.getPmIDCardNumber(),
                    projectConfig.getSecret());
                projectCorpInfo.setPmIDCardNumber(pmIDCardNumber);
            }

            projectCorpInfo.setProjectCode(projectConfig.getProjectCode());
            String json = JSONObject.toJSONStringWithDateFormat(projectCorpInfo,
                "yyyy-MM-dd HH:mm:ss").toString();

            String resString = GovConnecter.getGovData(
                "ProjectSubContractor.Add", json,
                projectConfig.getProjectCode(), projectConfig.getSecret());

            // 保存操作日志
            String saveOperateLog = operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectCorpinfo.getCode(), code,
                EOperateLogOperate.UploadProjectCorpinfo.getValue(), briefUser,
                null);

            // 状态消息队列更新数据库状态
            AsyncQueueHolder.addSerial(resString, projectConfig,
                "projectCorpInfoBO", code,
                EUploadStatus.UPLOAD_EDITABLE.getCode(), saveOperateLog);
        }
    }

    @Override
    public ProjectCorpInfo getProjectCorpInfo(String projectCode,
            String corpCode) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();

        projectCorpInfo.setProjectCode(projectCode);
        projectCorpInfo.setCorpCode(corpCode);
        projectCorpInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        return getProjectCorpInfo(projectCorpInfo);
    }

    @Override
    public void updateProjectCorpInfoDeleteStatus(String code, String status) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        projectCorpInfo.setDeleteStatus(status);
        projectCorpInfo.setCode(code);
        projectCorpInfoDAO.updateDeleteStatus(projectCorpInfo);
    }

    @Override
    public void removeProjectCorpInfoDeleteStatus(String projectCode,
            String corpCode) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        projectCorpInfo.setCorpCode(corpCode);
        projectCorpInfo.setProjectCode(projectCode);
        projectCorpInfo.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        projectCorpInfoDAO.updateDeleteStatus(projectCorpInfo);
    }
}
