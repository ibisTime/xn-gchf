package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectWorkerDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;

@Component
public class ProjectWorkerBOImpl extends PaginableBOImpl<ProjectWorker>
        implements IProjectWorkerBO {

    @Autowired
    private IProjectWorkerDAO projectWorkerDAO;

    @Override
    public String saveProjectWorker(ProjectWorker data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.ProjectWorker.getCode());
            data.setCode(code);
            projectWorkerDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeProjectWorker(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            ProjectWorker data = new ProjectWorker();
            data.setCode(code);
            count = projectWorkerDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshProjectWorker(ProjectWorker data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = projectWorkerDAO.update(data);
        }
        return count;
    }

    @Override
    public void doUpload(XN631911Req req, ProjectConfig projectConfig) {
        ProjectWorker projectWorker = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorker);

        String data = JSONObject.toJSON(projectWorker).toString();

        GovConnecter.getGovData("ProjectWorker.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public void doUpdate(XN631912Req req, ProjectConfig projectConfig) {
        ProjectWorker projectWorker = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorker);

        String data = JSONObject.toJSON(projectWorker).toString();

        GovConnecter.getGovData("ProjectWorker.Update", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public void doQuery(XN631913Req req, ProjectConfig projectConfig) {
        ProjectWorker projectWorker = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorker);

        String data = JSONObject.toJSON(projectWorker).toString();

        GovConnecter.getGovData("ProjectWorker.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public List<ProjectWorker> queryProjectWorkerList(ProjectWorker condition) {
        return projectWorkerDAO.selectList(condition);
    }

    @Override
    public ProjectWorker getProjectWorker(String code) {
        ProjectWorker data = null;
        if (StringUtils.isNotBlank(code)) {
            ProjectWorker condition = new ProjectWorker();
            condition.setCode(code);
            data = projectWorkerDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "项目人员不存在");
            }
        }
        return data;
    }

}
