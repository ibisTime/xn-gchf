package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectConfig;

public interface IProjectConfigDAO extends IBaseDAO<ProjectConfig> {
    String NAMESPACE = IProjectConfigDAO.class.getName().concat(".");

    int update(ProjectConfig projectConfig);

    ProjectConfig selectDefaultProjectConfig(ProjectConfig projectConfig);
}
