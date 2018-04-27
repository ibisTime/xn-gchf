package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IProjectAO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631420Req;
import com.cdkj.gchf.dto.req.XN631422Req;

@Service
public class ProjectAOImpl implements IProjectAO {

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addProject(XN631420Req req) {
        Project project = new Project();
        project.setName(req.getName());
        project.setChargeUser(req.getChargeUser());
        project.setChargeMobile(req.getChargeMobile());
        project.setStartDatetime(req.getStartDatetime());
        project.setEndDatetime(req.getEndDatetime());
        project.setLongitude(req.getLongitude());
        project.setLatitude(req.getLatitude());
        project.setProvince(req.getProvince());
        project.setCity(req.getCity());
        project.setArea(req.getArea());
        project.setAddress(req.getAddress());
        project.setSalaryDatetime(req.getSalaryDatetime());
        project.setUpdater(req.getUpdater());
        project.setUpdate_datetime(new Date());
        return projectBO.saveProject(project);
    }

    @Override
    public void editProject(XN631422Req req) {
        Project project = projectBO.getProject(req.getCode());
        project.setName(req.getName());
        project.setChargeUser(req.getChargeUser());
        project.setChargeMobile(req.getChargeMobile());
        project.setStartDatetime(req.getStartDatetime());
        project.setEndDatetime(req.getEndDatetime());
        project.setLongitude(req.getLongitude());
        project.setLatitude(req.getLatitude());
        project.setProvince(req.getProvince());
        project.setCity(req.getCity());
        project.setArea(req.getArea());
        project.setAddress(req.getAddress());
        project.setSalaryDatetime(req.getSalaryDatetime());
        project.setUpdater(req.getUpdater());
        project.setUpdate_datetime(new Date());
        projectBO.editProject(project);
    }

    @Override
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition) {
        return projectBO.getPaginable(start, limit, condition);
    }

    @Override
    public Project getProject(String code) {
        return projectBO.getProject(code);
    }

    @Override
    public List<Project> queryProjectList(Project condition) {
        // TODO Auto-generated method stub
        return projectBO.queryProject(condition);
    }

}
