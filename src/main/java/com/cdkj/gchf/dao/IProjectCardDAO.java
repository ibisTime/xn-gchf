package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectCard;

//dao层 
public interface IProjectCardDAO extends IBaseDAO<ProjectCard> {
    String NAMESPACE = IProjectCardDAO.class.getName().concat(".");

    void update(ProjectCard data);
}
