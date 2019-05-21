package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Project;

public interface IProjectDAO extends IBaseDAO<Project> {
    String NAMESPACE = IProjectDAO.class.getName().concat(".");

    void update(Project project);

    void updateSecretStatus(Project project);

    void updateContractorCorp(Project project);

    void updateUsedOcrCount(Project project);
}
