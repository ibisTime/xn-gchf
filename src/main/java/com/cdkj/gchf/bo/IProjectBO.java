package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Project;

public interface IProjectBO extends IPaginableBO<Project> {

    public void saveProject(Project data);

    public Project getProject(String code);

    public int editProject(Project data);

    public void approveProject(Project data);

    public List<Project> queryProject(Project condition);

    public List<Project> queryProjectpig(int start, int pageSize,
            Project condition);

    public void toApprove(Project data);

    public void projectEnd(Project data, String endDatetime, String updater,
            String remark);

}
