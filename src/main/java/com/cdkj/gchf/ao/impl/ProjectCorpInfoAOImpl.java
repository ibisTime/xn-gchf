package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;

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

    @Override
    public String addProjectCorpInfo(XN631630Req data) {

        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfo(data.getCorpCode());
        if (null == corpBasicinfo) {
            throw new BizException("XN631630", "企业信息不存在不存在");
        }

        return projectCorpInfoBO.saveProjectCorpInfo(data);
    }

    @Override
    public void dropProjectCorpInfo(String code) {

        ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
            .getProjectCorpInfo(code);
        if (EUploadStatus.UPLOAD_EDITABLE.getCode()
            .equals(projectCorpInfo.getUploadStatus())) {
            throw new BizException("XN631631", "参建单位已上传，无法删除");
        }

        projectCorpInfoBO.removeProjectCorpInfo(code);

    }

    @Override
    @Transactional
    public void editProjectCorpInfo(XN631632Req req) {

        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfo(req.getCorpCode());
        if (null == corpBasicinfo) {
            throw new BizException("XN631630", "企业信息不存在不存在");
        }

        projectCorpInfoBO.refreshProjectCorpInfo(req);

        projectCorpInfoBO.refreshUploadStatus(req.getCode(),
            EUploadStatus.TO_UPLOAD.getCode());

        User briefUser = userBO.getBriefUser(req.getUserId());
        operateLogBO.saveOperateLog(
            EOperateLogRefType.ProjectCorpinfo.getCode(), req.getCode(),
            EOperateLogOperate.EditProjectCorpinfo.getValue(), briefUser,
            req.getRemark());

    }

    @Override
    @Transactional
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

            String encrypt = AesUtils.encrypt(
                projectCorpInfo.getPmIDCardNumber(), projectConfig.getSecret());
            projectCorpInfo.setPmIDCardNumber(encrypt);
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
