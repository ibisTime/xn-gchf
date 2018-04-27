package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Project;

public interface IProjectBO extends IPaginableBO<Project> {

    public String saveProject(Project data);

    public Project getProject(String code);

    public int editProject(Project data);

    public List<Project> queryProject(Project condition);

}
