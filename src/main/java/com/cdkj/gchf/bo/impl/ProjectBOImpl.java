package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IProjectDAO;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class ProjectBOImpl implements IProjectBO {

    @Autowired
    private IProjectDAO projectDAO;

    @Override
    public long getTotalCount(Project condition) {
        return projectDAO.selectTotalCount(condition);
    }

    @Override
    public Paginable<Project> getPaginable(int start, Project condition) {
        return null;
    }

    @Override
    public Paginable<Project> getPaginable(int start, int pageSize,
            Project condition) {
        return null;
    }

    @Override
    public String saveProject(Project data) {
        String code = null;
        if (data != null) {
            if (data.getCode() == null) {
                code = OrderNoGenerater
                    .generate(EGeneratePrefix.Project.getCode());
                data.setCode(code);
            }
            projectDAO.insert(data);
        }
        return code;
    }

    @Override
    public Project getProject(String code) {
        Project data = null;
        if (StringUtils.isNotBlank(code)) {
            Project condition = new Project();
            condition.setCode(code);
            data = projectDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该编号不存在");
            }
        }
        return data;
    }

    @Override
    public int editProject(Project data) {
        return projectDAO.update(data);
    }

    @Override
    public List<Project> queryProject(Project condition) {
        return projectDAO.selectList(condition);
    }

}
