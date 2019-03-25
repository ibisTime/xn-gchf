package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectWorkerDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631690Req;
import com.cdkj.gchf.dto.req.XN631692Req;
import com.cdkj.gchf.dto.req.XN631911Req;
import com.cdkj.gchf.dto.req.XN631911ReqWorker;
import com.cdkj.gchf.dto.req.XN631912Req;
import com.cdkj.gchf.dto.req.XN631913Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;

@Component
public class ProjectWorkerBOImpl extends PaginableBOImpl<ProjectWorker>
        implements IProjectWorkerBO {

    @Autowired
    private IProjectWorkerDAO projectWorkerDAO;

    @Override
    public String saveProjectWorker(XN631690Req data) {
        ProjectWorker projectWorkerInfo = new ProjectWorker();
        BeanUtils.copyProperties(data, projectWorkerInfo);
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.ProjectWorker.getCode());
        projectWorkerInfo.setCode(code);

        projectWorkerDAO.insert(projectWorkerInfo);
        return code;
    }

    @Override
    public void removeProjectWorker(String code) {
        ProjectWorker data = new ProjectWorker();

        data.setCode(code);

        projectWorkerDAO.delete(data);
    }

    @Override
    public void refreshProjectWorker(XN631692Req req) {
        ProjectWorker projectWorkerInfo = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorkerInfo);

        projectWorkerDAO.update(projectWorkerInfo);
    }

    @Override
    public void refreshTeamSysNoByLocal(String localTeamSysNo,
            String teamSysNo) {
        ProjectWorker projectWorker = new ProjectWorker();

        projectWorker.setLocalTeamSysNo(localTeamSysNo);
        projectWorker.setTeamSysNo(Integer.parseInt(teamSysNo));

        projectWorkerDAO.updateTeamSysNoByLocal(projectWorker);
    }

    @Override
    public void doUpload(XN631911Req req, ProjectConfig projectConfig) {

        List<XN631911ReqWorker> workerList = req.getWorkerList();
        for (XN631911ReqWorker worker : workerList) {
            worker.setIdCardNumber(AesUtils.encrypt(worker.getIdCardNumber(),
                projectConfig.getSecret()));

            if (StringUtils.isNotBlank(worker.getPayRollBankCardNumber())) {
                worker.setPayRollBankCardNumber(
                    AesUtils.encrypt(worker.getPayRollBankCardNumber(),
                        projectConfig.getSecret()));
            }
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        String resString = GovConnecter.getGovData("ProjectWorker.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public void doUpdate(XN631912Req req, ProjectConfig projectConfig) {

        req.setIdCardNumber(
            AesUtils.encrypt(req.getIdCardNumber(), projectConfig.getSecret()));

        if (StringUtils.isNotBlank(req.getPayRollBankCardNumber())) {
            req.setPayRollBankCardNumber(AesUtils.encrypt(
                req.getPayRollBankCardNumber(), projectConfig.getSecret()));
        }

        String data = JSONObject.toJSONStringWithDateFormat(req, "yyyy-MM-dd")
            .toString();

        GovConnecter.getGovData("ProjectWorker.Update", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public Paginable<ProjectWorker> doQuery(XN631913Req req,
            ProjectConfig projectConfig) {
        ProjectWorker projectWorker = new ProjectWorker();
        BeanUtils.copyProperties(req, projectWorker);

        String data = JSONObject.toJSON(projectWorker).toString();

        String queryString = GovConnecter.getGovData("ProjectWorker.Query",
            data, projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<ProjectWorker> page = GovUtil.parseGovPage(req.getPageIndex(),
            req.getPageSize(), queryString, replaceMap, ProjectWorker.class);

        return page;
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
