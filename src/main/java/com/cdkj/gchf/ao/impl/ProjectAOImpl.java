package com.cdkj.gchf.ao.impl;

import com.cdkj.gchf.common.PhoneUtil;
import java.util.List;
import java.util.Map;

import com.cdkj.gchf.bo.*;
import com.cdkj.gchf.domain.*;
import com.cdkj.gchf.dto.res.XN631618Res;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.StringUtil;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;
import com.cdkj.gchf.enums.EProjectCorpType;
import com.cdkj.gchf.enums.EProjectCorpUploadStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class ProjectAOImpl implements IProjectAO {

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IProjectBuilderLicenseBO projectBuilderLicenseBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private IWorkerEntryExitRecordBO workerEntryExitRecordBO;

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Override
    @Transactional
    public String addProject(XN631600Req req) {

        if (StringUtils.isNotBlank(req.getBuildingArea())
                && !StringUtil.isNumber(req.getBuildingArea())) {
            throw new BizException("XN631600", "【总面积】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getBuildingLength())
                && !StringUtil.isNumber(req.getBuildingLength())) {
            throw new BizException("XN631600", "【总长度】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getInvest())
                && !StringUtil.isNumber(req.getInvest())) {
            throw new BizException("XN631600", "【总投资】为数字类型，请重新填写");
        }
        if (StringUtils.isNotBlank(req.getLat())
                && !StringUtil.isNumber(req.getLat())) {
            throw new BizException("XN631600", "【经度】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getLng())
                && !StringUtil.isNumber(req.getLng())) {
            throw new BizException("XN631600", "【纬度】为数字类型，请重新填写");
        }

        Project preProject = projectBO.getProjectByFullName(req.getName());
        if (null != preProject) {
            throw new BizException("XN631600", "项目名称已存在，请重新输入");
        }

        CorpBasicinfo contractorCorpInfo = null;
        if (StringUtils.isNotBlank(req.getContractorCorpCode())) {
            contractorCorpInfo = corpBasicinfoBO
                    .getCorpBasicinfoByCorp(req.getContractorCorpCode());
        }

        // 添加项目
        Project project = projectBO.saveProject(req, contractorCorpInfo);

        if (null != contractorCorpInfo) {
            // 生成对应的参建单位
            projectCorpInfoBO.addProjectCorpInfo(contractorCorpInfo, project);
        }

        // 添加项目管理员
        userBO.saveProjectAdmin(project.getCode(), req);

        // 添加施工许可证
        projectBuilderLicenseBO.saveProjectBuilderLicense(project.getCode(),
                req.getBuilderLicenses());

        // 发送短信
//        smsOutBO.sendSmsOut(req.getLinkPhone(),
//                "尊敬的" + PhoneUtil.hideMobile(req.getLinkPhone()) + "，"
//                        + req.getName() + "项目管理员账户已开设，登录名："
//                        + req.getName().concat("管理员") + "，密码：888888"
//                        + "，请登录鲸目项目端查看。",
//                "804080");

        return project.getCode();
    }

    @Override
    @Transactional
    public void editProject(XN631602Req req) {

        if (StringUtils.isNotBlank(req.getBuildingArea())
                && !StringUtil.isNumber(req.getBuildingArea())) {
            throw new BizException("XN631600", "【总面积】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getBuildingLength())
                && !StringUtil.isNumber(req.getBuildingLength())) {
            throw new BizException("XN631600", "【总长度】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getInvest())
                && !StringUtil.isNumber(req.getInvest())) {
            throw new BizException("XN631600", "【总投资】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getLat())
                && !StringUtil.isNumber(req.getLat())) {
            throw new BizException("XN631600", "【经度】为数字类型，请重新填写");
        }

        if (StringUtils.isNotBlank(req.getLng())
                && !StringUtil.isNumber(req.getLng())) {
            throw new BizException("XN631600", "【纬度】为数字类型，请重新填写");
        }

        CorpBasicinfo contractorCorpInfo = corpBasicinfoBO
                .getCorpBasicinfoByCorp(req.getContractorCorpCode());
        if (null == contractorCorpInfo) {
            throw new BizException("XN631600", "总承包单位不存在");
        }

        Project project = projectBO.getProject(req.getCode());
        if (!req.getName().equals(project.getName())) {
            Project preProject = projectBO.getProjectByFullName(req.getName());
            if (null != preProject) {
                throw new BizException("XN631600", "项目名称已存在，请重新输入");
            }
        }

        if (!project.getContractorCorpCode()
                .equals(req.getContractorCorpCode())) {

            ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                    .getProjectCorpInfo(req.getCode(),
                            project.getContractorCorpCode(),
                            EProjectCorpType.ZONGCHENGBAO.getCode());

            if (null != projectCorpInfo) {
                if (!EProjectCorpUploadStatus.UPLOAD_FAIL.getCode()
                        .equals(projectCorpInfo.getUploadStatus())
                        && !EProjectCorpUploadStatus.TO_UPLOAD.getCode()
                        .equals(projectCorpInfo.getUploadStatus())) {
                    throw new BizException("XN631600", "项目总承包单位已上传，无法修改");
                }

                projectCorpInfoBO
                        .removeProjectCorpInfo(projectCorpInfo.getCode());

                projectCorpInfoBO.addProjectCorpInfo(contractorCorpInfo,
                        project);
            } else {
                projectCorpInfoBO.addProjectCorpInfo(contractorCorpInfo,
                        project);
            }

        }

        // 删除旧的施工许可证
        projectBuilderLicenseBO.removeByProject(req.getCode());

        // 添加施工许可证
        projectBuilderLicenseBO.saveProjectBuilderLicense(req.getCode(),
                req.getBuilderLicenses());

        projectBO.refreshProject(req, contractorCorpInfo);
    }

    @Override
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition) {

        Paginable<Project> page = projectBO.getPaginable(start, limit,
                condition);

        return page;
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        return projectBO.queryProjectList(condition);
    }

    @Override
    public Project getProject(String code) {
        Project project = projectBO.getProject(code);
        // 查询项目施工许可证
        List<ProjectBuilderLicense> projectBuilderLicenseList = projectBuilderLicenseBO
                .queryProjectBuilderLicenseList(code);
        project.setBuilderLicenses(projectBuilderLicenseList);
        // 项目配置
        ProjectConfig projectConfig = projectConfigBO
                .getProjectConfigByLocal(project.getCode());
        project.setProjectConfig(projectConfig);

        return project;
    }

    @Override
    public Object queryProjectInfo_led(String userId) {

        List<Map> projectInfoList_led = projectBO.getProjectInfoList_led(userId);
        Integer totalCount = workerAttendanceBO.selectWorkerAttendanceToday(userId);

        XN631618Res xn631618Res = new XN631618Res();
        xn631618Res.setData(projectInfoList_led);
        if (null == totalCount) {
            xn631618Res.setTotalCount(0);
        } else {
            xn631618Res.setTotalCount(totalCount);
        }
        return xn631618Res;
    }
}
