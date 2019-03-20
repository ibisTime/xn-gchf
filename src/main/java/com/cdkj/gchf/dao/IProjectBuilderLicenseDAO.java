package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectBuilderLicense;

public interface IProjectBuilderLicenseDAO
        extends IBaseDAO<ProjectBuilderLicense> {
    String NAMESPACE = IProjectBuilderLicenseDAO.class.getName().concat(".");

    int deleteByProject(ProjectBuilderLicense condition);

}
