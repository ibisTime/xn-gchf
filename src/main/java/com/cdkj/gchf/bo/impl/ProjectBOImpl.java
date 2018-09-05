package com.cdkj.gchf.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectDAO;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class ProjectBOImpl extends PaginableBOImpl<Project>
        implements IProjectBO {

    @Autowired
    private IProjectDAO projectDAO;

    @Override
    public String saveProject(String name, String updater, String remark) {

        Project data = new Project();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Project.getCode());
        data.setCode(code);

        data.setName(name);
        data.setUpdater(updater);
        data.setRemark(remark);
        data.setStatus(EProjectStatus.To_Edit.getCode());
        data.setUpdateDatetime(new Date());

        projectDAO.insert(data);

        return code;
    }

    @Override
    public int editProject(Project data) {
        return projectDAO.updateEditProject(data);
    }

    @Override
    public void startProject(String code, String approver, String approveNote) {
        Project data = new Project();

        data.setCode(code);
        data.setStatus(EProjectStatus.Building.getCode());
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(approveNote);

        projectDAO.updateStartProject(data);
    }

    @Override
    public void pauseProject(String code, String updater, String remark) {
        Project data = new Project();
        data.setCode(code);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setStatus(EProjectStatus.Pause.getCode());
        data.setRemark(remark);
        projectDAO.updatePauseProject(data);
    }

    @Override
    public void restartProject(String code, String updater, String remark) {
        Project data = new Project();
        data.setCode(code);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setStatus(EProjectStatus.Building.getCode());
        data.setRemark(remark);
        projectDAO.updateRestartProject(data);
    }

    @Override
    public void endProject(String code, String updater, String remark) {
        Project data = new Project();
        data.setCode(code);
        data.setEndDatetime(new Date());
        data.setStatus(EProjectStatus.End.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        projectDAO.updateEndProject(data);
    }

    @Override
    public void editSalaryDelayDays(String code, Integer salaryDelayDays) {
        Project data = new Project();
        data.setCode(code);
        data.setSalaryDelayDays(salaryDelayDays);
        projectDAO.updateSalaryDelayDays(data);
    }

    @Override
    public List<String> queryProjectCodeList(String province, String city,
            String area) {
        List<String> projectCodeList = new ArrayList<String>();
        Project condition = new Project();
        condition.setProvince(province);
        condition.setCity(city);
        condition.setArea(area);

        List<Project> list = projectDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Project project : list) {
                projectCodeList.add(project.getCode());
            }
        }
        return projectCodeList;
    }

    @Override
    public Project getProject(String code) {
        Project data = null;
        if (StringUtils.isNotBlank(code)) {
            Project condition = new Project();
            condition.setCode(code);
            data = projectDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该项目不存在");
            }
        }
        return data;
    }

    @Override
    public List<Project> queryProject(Project condition) {
        return projectDAO.selectList(condition);
    }

    @Override
    public long getTotalCount(Project condition) {
        return projectDAO.selectTotalCount(condition);
    }
}
