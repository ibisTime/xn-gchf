package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IProjectDAO;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.exception.BizException;

@Component
public class ProjectBOImpl extends PaginableBOImpl<Project>
        implements IProjectBO {

    @Autowired
    private IProjectDAO projectDAO;

    @Override
    public long getTotalCount(Project condition) {
        return projectDAO.selectTotalCount(condition);
    }

    @Override
    public void saveProject(Project data) {

        projectDAO.insert(data);

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

    @Override
    public List<Project> queryProjectpig(int start, int pageSize,
            Project condition) {
        return projectDAO.selectList(condition, start, pageSize);
    }

    @Override
    public void auditProject(Project data) {
        projectDAO.auditProject(data);
    }

}
