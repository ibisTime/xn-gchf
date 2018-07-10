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

    public void toBuilding(Project project);

    public void stopProject(Project data, String updater, String remark);

    // 更新项目发放薪资可延迟天数
    public void editSalaryDelayDays(Project project);

    public void restartProject(Project data, String updater, String remark);

    public List<String> queryProjectCodeList(Project condition);

}
