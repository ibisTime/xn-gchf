package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Project;

import java.util.List;
import java.util.Map;

public interface IProjectDAO extends IBaseDAO<Project> {
    String NAMESPACE = IProjectDAO.class.getName().concat(".");

    void update(Project project);

    void updateSecretStatus(Project project);

    void updateContractorCorp(Project project);

    /**
     * 查询项目下各个班组 及人员
     *
     * @param userId id
     * @return
     */
    List<Map> queryProjectInfo_led(String userId);
}
