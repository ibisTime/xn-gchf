package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectWorkerEntryExitHistoryDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631730Req;
import com.cdkj.gchf.dto.req.XN631732Req;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;

@Component
public class ProjectWorkerEntryExitHistoryBOImpl
        extends PaginableBOImpl<ProjectWorkerEntryExitHistory>
        implements IProjectWorkerEntryExitHistoryBO {

    @Autowired
    private IProjectWorkerEntryExitHistoryDAO projectWorkerEntryExitHistoryDAO;

    @Override
    public String saveProjectWorkerEntryExitHistory(XN631730Req req) {
        ProjectWorkerEntryExitHistory data = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, data);
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorkerEntryExitHistory.getCode());
        data.setCode(code);
        projectWorkerEntryExitHistoryDAO.insert(data);
        return code;
    }

    @Override
    public void removeProjectWorkerEntryExitHistory(String code) {
        ProjectWorkerEntryExitHistory data = new ProjectWorkerEntryExitHistory();
        data.setCode(code);
        projectWorkerEntryExitHistoryDAO.delete(data);
    }

    @Override
    public void refreshProjectWorkerEntryExitHistory(XN631732Req req) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, projectWorkerEntryExitHistory);
        projectWorkerEntryExitHistoryDAO.update(projectWorkerEntryExitHistory);
    }

    @Override
    public void doUpload(XN631914Req req, ProjectConfig projectConfig) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, projectWorkerEntryExitHistory);

        String data = JSONObject.toJSONStringWithDateFormat(
            projectWorkerEntryExitHistory, "yyyy-MM-dd").toString();

        GovConnecter.getGovData("WorkerEntryExit.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public Paginable<ProjectWorkerEntryExitHistory> doQuery(XN631915Req req,
            ProjectConfig projectConfig) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, projectWorkerEntryExitHistory);

        String data = JSONObject.toJSON(projectWorkerEntryExitHistory)
            .toString();

        String queryString = GovConnecter.getGovData("WorkerEntryExit.Query",
            data, projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<ProjectWorkerEntryExitHistory> page = GovUtil.parseGovPage(
            req.getPageIndex(), req.getPageSize(), queryString, replaceMap,
            ProjectWorkerEntryExitHistory.class);

        return page;
    }

    @Override
    public List<ProjectWorkerEntryExitHistory> queryProjectWorkerEntryExitHistoryList(
            ProjectWorkerEntryExitHistory condition) {
        return projectWorkerEntryExitHistoryDAO.selectList(condition);
    }

    @Override
    public ProjectWorkerEntryExitHistory getProjectWorkerEntryExitHistory(
            String code) {
        ProjectWorkerEntryExitHistory data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
            condition.setCode(code);
            data = projectWorkerEntryExitHistoryDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "人员进退场编号不存在");
            }
        }
        return data;
    }

    @Override
    public Object queryProjectWorkerEntryExitHistory(String code) {
        ProjectWorkerEntryExitHistory condition = new ProjectWorkerEntryExitHistory();
        condition.setCode(code);
        return projectWorkerEntryExitHistoryDAO.select(condition);
    }

}
