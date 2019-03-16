package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectCorpInfoDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.dto.req.XN631905Req;
import com.cdkj.gchf.dto.req.XN631906Req;
import com.cdkj.gchf.dto.req.XN631907Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;

@Component
public class ProjectCorpInfoBOImpl extends PaginableBOImpl<ProjectCorpInfo>
        implements IProjectCorpInfoBO {

    @Autowired
    private IProjectCorpInfoDAO projectCorpInfoDAO;

    @Override
    public String saveProjectCorpInfo(ProjectCorpInfo data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.ProjectCorpInfo.getCode());
            data.setCode(code);
            projectCorpInfoDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeProjectCorpInfo(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ProjectCorpInfo data = new ProjectCorpInfo();
            data.setCode(code);
            count = projectCorpInfoDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshProjectCorpInfo(ProjectCorpInfo data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = projectCorpInfoDAO.update(data);
        }
        return count;
    }

    @Override
    public void doUpload(XN631905Req req, ProjectConfig projectConfig) {

        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        BeanUtils.copyProperties(req, projectCorpInfo);

        String data = JSONObject
            .toJSONStringWithDateFormat(projectCorpInfo, "yyyy-MM-dd HH:mm:ss")
            .toString();

        GovConnecter.getGovData("ProjectSubContractor.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public void doUpdate(XN631906Req req, ProjectConfig projectConfig) {
        ProjectCorpInfo projectCorpInfo = new ProjectCorpInfo();
        BeanUtils.copyProperties(req, projectCorpInfo);

        String data = JSONObject
            .toJSONStringWithDateFormat(projectCorpInfo, "yyyy-MM-dd HH:mm:ss")
            .toString();

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

}
