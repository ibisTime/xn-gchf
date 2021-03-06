package com.cdkj.gchf.bo.impl;

import java.util.*;

import com.cdkj.gchf.bo.*;
import com.cdkj.gchf.enums.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.IdCardChecker;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectCorpInfoDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631630Req;
import com.cdkj.gchf.dto.req.XN631632Req;
import com.cdkj.gchf.dto.req.XN631633ReqList;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;
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
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String saveProjectCorpInfo(XN631630Req req, Project project) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        EProjectCorpType.checkExists(req.getCorpType());
        BeanUtils.copyProperties(req, projectCorpInfo);
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(req.getCorpCode());
        if (corpBasicinfo == null) {
            throw new BizException("XN0000", "企业信用编码无效,请检查");
        }
        projectCorpInfo.setCorpName(corpBasicinfo.getCorpName());
        if (StringUtils.isNotBlank(req.getPmIDCardType())) {
            EIdCardType.checkExists(req.getPmIDCardType());
        }

        String code = OrderNoGenerater
                .generate(EGeneratePrefix.ProjectCorpInfo.getCode());
        projectCorpInfo.setCode(code);
        projectCorpInfo.setProjectName(project.getName());
        projectCorpInfo
                .setUploadStatus(EProjectCorpUploadStatus.TO_UPLOAD.getCode());
        projectCorpInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        projectCorpInfoDAO.insert(projectCorpInfo);

        return code;
    }

    @Override
    public String addProjectCorpInfo(CorpBasicinfo corpbasic, Project project) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        projectCorpInfo.setCorpCode(corpbasic.getCorpCode());
        projectCorpInfo.setCorpType(corpbasic.getCorpType());
        String code = OrderNoGenerater
                .generate(EGeneratePrefix.ProjectCorpInfo.getCode());
        projectCorpInfo.setCorpName(corpbasic.getCorpName());
        projectCorpInfo.setProjectCode(project.getCode());
        projectCorpInfo.setProjectName(project.getName());
        projectCorpInfo.setCode(code);
        projectCorpInfo.setCorpType(EProjectCorpType.ZONGCHENGBAO.getCode());
        projectCorpInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        projectCorpInfo
                .setUploadStatus(EProjectCorpUploadStatus.TO_UPLOAD.getCode());
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

    // 刷新上传状态
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
        User user = userBO.getBriefUser(req.getUserId());
        if (StringUtils.isNotBlank(req.getPmIdcardNumber())) {
            req.setPmIdcardNumber(AesUtils.encrypt(req.getPmIdcardNumber(),
                    projectConfig.getSecret()));
        }

        String data = JSONObject
                .toJSONStringWithDateFormat(req, "yyyy-MM-dd HH:mm:ss").toString();
        System.out.println("===" + data);

        String resString = null;
        try {
            resString = GovConnecter.getGovData("ProjectSubContractor.Update",
                    data, projectConfig.getProjectCode(),
                    projectConfig.getSecret());
        } catch (BizException e) {
            projectCorpInfoBO.refreshUploadStatus(req.getCode(),
                    EProjectCorpUploadStatus.UPLOAD_UNUPDATE.getCode());
            e.printStackTrace();
            throw e;
        }
        String operateLog = operateLogBO.saveOperateLog(
                EOperateLogRefType.ProjectCorpinfo.getCode(), req.getCode(),
                "修改平台参建单位信息", user, "修改平台参建单位信息");
        AsyncQueueHolder.addSerial(resString, projectConfig,
                "projectCorpInfoBO", req.getCode(),
                EProjectCorpUploadStatus.UPLOAD_UPDATE.getCode(), operateLog,
                req.getUserId());
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
        }
        return data;
    }

    @Override
    public void batchSaveProjectCorpInfo(User user, List<XN631633ReqList> datas, List<String> projectCodes) {
        List<ProjectCorpInfo> projectCorpInfoList = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
            XN631633ReqList req = datas.get(i);
            Project project = projectBO.getProject(projectCodes.get(i));
            String code;
            Date entryDate = null;
            Date exitDate = null;
            try {
                code = OrderNoGenerater
                        .generate(EGeneratePrefix.ProjectCorpInfo.getCode());
                projectCorpInfo.setCode(code);

                BeanUtils.copyProperties(req, projectCorpInfo);
                projectCorpInfo.setCorpCode(req.getCorpCode());
                projectCorpInfo.setCorpName(req.getCorpName());
                projectCorpInfo.setProjectCode(project.getCode());
                projectCorpInfo.setProjectName(project.getName());
                projectCorpInfo
                        .setUploadStatus(EProjectCorpUploadStatus.TO_UPLOAD.getCode());
                projectCorpInfo.setPmIDCardType("01");
                projectCorpInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
                IdCardChecker idCardChecker = new IdCardChecker(
                        req.getPmIDCardNumber());
                if (StringUtils.isNotBlank(req.getPmIDCardNumber())
                        && !idCardChecker.validate()) {
                    throw new BizException("XN631633",
                            "项目经理证件信息不正确" + req.getPmIDCardNumber());
                }
                if (StringUtils.isNotBlank(req.getEntryTime())) {
                    entryDate = DateUtil.strToDate(req.getEntryTime(),
                            DateUtil.FRONT_DATE_FORMAT_STRING);
                    projectCorpInfo.setEntryTime(entryDate);
                }
                if (StringUtils.isNotBlank(req.getExitTime())) {
                    exitDate = DateUtil.strToDate(req.getExitTime(),
                            DateUtil.FRONT_DATE_FORMAT_STRING);
                    projectCorpInfo.setExitTime(exitDate);
                }
                projectCorpInfoList.add(projectCorpInfo);
                //保存操作日志
                operateLogBO.saveOperateLog(EOperateLogRefType.ProjectCorpinfo.getCode(), code, EOperateLogOperate.ImportProjectCorpInfo.getCode(), user, null);
            } catch (BeansException e) {
                e.printStackTrace();
                if (entryDate == null || exitDate == null) {
                    throw new BizException("XN631805", "请检查excel进退场日期数据格式");
                }
            }
        }
        projectCorpInfoDAO.batchInsert(projectCorpInfoList);
    }

    @Override
    public String saveProjectCorpInfo(Project project, XN631633ReqList req) {
        String code;
        Date entryDate = null;
        Date exitDate = null;
        try {
            ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();

            code = OrderNoGenerater
                    .generate(EGeneratePrefix.ProjectCorpInfo.getCode());
            projectCorpInfo.setCode(code);

            BeanUtils.copyProperties(req, projectCorpInfo);
            projectCorpInfo.setCorpCode(req.getCorpCode());
            projectCorpInfo.setCorpName(req.getCorpName());
            projectCorpInfo.setProjectCode(project.getCode());
            projectCorpInfo.setProjectName(project.getName());
            projectCorpInfo
                    .setUploadStatus(EProjectCorpUploadStatus.TO_UPLOAD.getCode());
            projectCorpInfo.setPmIDCardType("01");
            projectCorpInfo.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
            IdCardChecker idCardChecker = new IdCardChecker(
                    req.getPmIDCardNumber());
            if (StringUtils.isNotBlank(req.getPmIDCardNumber())
                    && !idCardChecker.validate()) {
                throw new BizException("XN631633",
                        "项目经理证件信息不正确" + req.getPmIDCardNumber());
            }
            if (StringUtils.isNotBlank(req.getEntryTime())) {
                entryDate = DateUtil.strToDate(req.getEntryTime(),
                        DateUtil.FRONT_DATE_FORMAT_STRING);
                projectCorpInfo.setEntryTime(entryDate);
            }
            if (StringUtils.isNotBlank(req.getExitTime())) {
                exitDate = DateUtil.strToDate(req.getExitTime(),
                        DateUtil.FRONT_DATE_FORMAT_STRING);
                projectCorpInfo.setExitTime(exitDate);
            }

            projectCorpInfoDAO.insert(projectCorpInfo);
            return code;
        } catch (BeansException e) {
            e.printStackTrace();
            if (entryDate == null || exitDate == null) {
                throw new BizException("XN631805", "请检查excel进退场日期数据格式");
            }

        }
        return null;

    }

    @Override
    public ProjectCorpInfo getProjectCorpInfo(ProjectCorpInfo condition) {
        return projectCorpInfoDAO.select(condition);
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
    public ProjectCorpInfo getProjectCorpInfo(String projectCode,
                                              String corpCode, String corpType) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        projectCorpInfo.setProjectCode(projectCode);
        projectCorpInfo.setCorpCode(corpCode);
        projectCorpInfo.setCorpType(corpType);
        List<ProjectCorpInfo> projectCorpInfos = projectCorpInfoDAO
                .selectList(projectCorpInfo);
        if (CollectionUtils.isNotEmpty(projectCorpInfos)) {
            return projectCorpInfos.get(0);
        }
        return null;

    }
}
