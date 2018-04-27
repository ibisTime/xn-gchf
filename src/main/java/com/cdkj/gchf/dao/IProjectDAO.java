package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Project;

public interface IProjectDAO extends IBaseDAO<Project> {

    String NAMESPACE = IProjectDAO.class.getName().concat(".");

    // 修改项目
    public int update(Project data);

    // 审核项目
    public int auditProject(Project data);

}
