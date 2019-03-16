package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IProjectWorkerEntryExitHistoryBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectWorkerEntryExitHistoryDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorkerEntryExitHistory;
import com.cdkj.gchf.dto.req.XN631914Req;
import com.cdkj.gchf.dto.req.XN631915Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;

@Component
public class ProjectWorkerEntryExitHistoryBOImpl
        extends PaginableBOImpl<ProjectWorkerEntryExitHistory>
        implements IProjectWorkerEntryExitHistoryBO {

    @Autowired
    private IProjectWorkerEntryExitHistoryDAO projectWorkerEntryExitHistoryDAO;

    @Override
    public String saveProjectWorkerEntryExitHistory(
            ProjectWorkerEntryExitHistory data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(
                EGeneratePrefix.ProjectWorkerEntryExitHistory.getCode());
            data.setCode(code);
            projectWorkerEntryExitHistoryDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeProjectWorkerEntryExitHistory(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ProjectWorkerEntryExitHistory data = new ProjectWorkerEntryExitHistory();
            data.setCode(code);
            count = projectWorkerEntryExitHistoryDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshProjectWorkerEntryExitHistory(
            ProjectWorkerEntryExitHistory data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = projectWorkerEntryExitHistoryDAO.update(data);
        }
        return count;
    }

    @Override
    public void doUpload(XN631914Req req, ProjectConfig projectConfig) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, projectWorkerEntryExitHistory);

        String data = JSONObject.toJSON(projectWorkerEntryExitHistory)
            .toString();

        GovConnecter.getGovData("WorkerEntryExit.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public void doQuery(XN631915Req req, ProjectConfig projectConfig) {
        ProjectWorkerEntryExitHistory projectWorkerEntryExitHistory = new ProjectWorkerEntryExitHistory();
        BeanUtils.copyProperties(req, projectWorkerEntryExitHistory);

        String data = JSONObject.toJSON(projectWorkerEntryExitHistory)
            .toString();

        GovConnecter.getGovData("WorkerEntryExit.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
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

}
