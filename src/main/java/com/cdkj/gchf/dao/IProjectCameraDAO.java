package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectCamera;

//dao层 
public interface IProjectCameraDAO extends IBaseDAO<ProjectCamera> {

    String NAMESPACE = IProjectCameraDAO.class.getName().concat(".");

    int update(ProjectCamera projectCamera);
}