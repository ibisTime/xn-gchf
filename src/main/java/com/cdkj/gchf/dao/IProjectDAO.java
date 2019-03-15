package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Project;

public interface IProjectDAO extends IBaseDAO<Project> {

    String NAMESPACE = IProjectDAO.class.getName().concat(".");

    // 编辑项目
    public int updateEditProject(Project data);

    // 项目开工
    public int updateStartProject(Project data);

    // 项目停工
    public void updatePauseProject(Project data);

    // 重新开工
    public void updateRestartProject(Project data);

    // 项目结束
    public void updateEndProject(Project data);

    // 更新项目发放薪资可延迟天数
    public int updateSalaryDelayDays(Project data);

}
