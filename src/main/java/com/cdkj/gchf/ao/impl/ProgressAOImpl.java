package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProgressAO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IProgressBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Progress;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631380Req;
import com.cdkj.gchf.dto.req.XN631382Req;

//CHECK ��鲢��ע�� 
@Service
public class ProgressAOImpl implements IProgressAO {

    @Autowired
    private IProgressBO progressBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IDepartmentBO departmentBO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String addProgress(XN631380Req req) {
        Progress data = new Progress();
        Project project = projectBO.getProject(req.getProjectCode());
        data.setCompanyCode(project.getCompanyCode());
        data.setCompanyName(project.getCompanyName());

        data.setProjectCode(req.getProjectCode());
        data.setProjectName(
            projectBO.getProject(req.getProjectCode()).getName());
        data.setDatetime(DateUtil.strToDate(req.getDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setDescription(req.getDescription());
        data.setPicture(req.getPicture());
        data.setUpdater(req.getUpdater());

        data.setRemark(req.getRemark());
        return progressBO.saveProgress(data);
    }

    @Override
    public int editProgress(XN631382Req req) {
        Progress data = progressBO.getProgress(req.getCode());
        data.setDatetime(DateUtil.strToDate(req.getDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setDescription(req.getDescription());
        data.setPicture(req.getPicture());
        data.setUpdater(req.getUpdater());

        data.setRemark(req.getRemark());
        return progressBO.refreshProgress(data);
    }

    @Override
    public Paginable<Progress> queryProgressPage(int start, int limit,
            Progress condition) {
        return progressBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Progress> queryProgressList(Progress condition) {
        return progressBO.queryProgressList(condition);
    }

    @Override
    public Progress getProgress(String code) {
        return progressBO.getProgress(code);
    }
}
