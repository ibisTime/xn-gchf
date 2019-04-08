package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectCorpInfoAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.req.XN631633Req;
import com.cdkj.gchf.dto.req.XN631633ReqList;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EProjectCorpType;
import com.cdkj.gchf.enums.EUploadStatus;
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

    @Override
    public String addProjectCorpInfo(XN631630Req data) {

        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());
        if (null == corpBasicinfo) {
            throw new BizException("XN631630", "企业信息不存在");
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
            .getCorpBasicinfoByCorp(req.getCorpCode());
        if (null == corpBasicinfo) {
            throw new BizException("XN631630", "企业信息不存在");
        }
        EProjectCorpType.checkExists(req.getCorpType());
        if (StringUtils.isNotBlank(req.getPmIDCardType())) {
            EIdCardType.checkExists(req.getPmIDCardType());
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
        projectCorpInfoBO.uploadProjectCorpInfo(userId, codes);
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

    @Transactional
    @Override
    public void importProjectCorpInfo(XN631633Req req) {
        List<XN631633ReqList> projectCorpInfos = req.getDateList();

        for (XN631633ReqList requestProjectCourpInfo : projectCorpInfos) {

            EProjectCorpType.checkExists(requestProjectCourpInfo.getCorpType());
            if (StringUtils
                .isNotBlank(requestProjectCourpInfo.getPmIDCardType())) {
                EIdCardType
                    .checkExists(requestProjectCourpInfo.getPmIDCardType());
            }

            if (corpBasicinfoBO.getCorpBasicinfoByCorp(
                requestProjectCourpInfo.getCorpCode()) == null) {
                throw new BizException("XN631633",
                    "企业信息不存在" + requestProjectCourpInfo.getCorpCode());
            }
        }
        User user = userBO.getBriefUser(req.getUserId());
        for (XN631633ReqList data : req.getDateList()) {
            String code = null;
            // 拼数据
            ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
            ProjectConfig configByLocal = projectConfigBO
                .getProjectConfigByLocal(req.getProjectCode());
            BeanUtils.copyProperties(data, projectCorpInfo);
            projectCorpInfo.setCorpCode(data.getCorpCode());
            projectCorpInfo.setCorpName(data.getCorpName());
            projectCorpInfo.setProjectCode(configByLocal.getProjectCode());
            projectCorpInfo.setProjectName(configByLocal.getProjectName());
            if (StringUtils.isNotBlank(data.getEntryTime())) {
                Date entryTime = DateUtil.strToDate(data.getEntryTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                projectCorpInfo.setEntryTime(entryTime);
            }
            if (StringUtils.isNotBlank(data.getExitTime())) {
                Date exitTime = DateUtil.strToDate(data.getExitTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                projectCorpInfo.setEntryTime(exitTime);
            }

            // 操作日志
            code = OrderNoGenerater
                .generate(EGeneratePrefix.ProjectCorpInfo.getCode());
            projectCorpInfo.setCode(code);
            projectCorpInfo.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
            projectCorpInfoBO.saveProjectCorpInfo(projectCorpInfo);
            operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectCorpinfo.getCode(), code,
                EOperateLogOperate.UploadCorpBasicinfo.getValue(), user,
                "批量导入参建单位信息" + code);
        }
    }

}
