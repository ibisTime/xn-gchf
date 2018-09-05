package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Project;

public interface IProjectBO extends IPaginableBO<Project> {

    // 添加项目
    public String saveProject(String name, String updater, String remark);

    // 编辑项目
    public int editProject(Project data);

    // 项目开工
    public void startProject(String code, String approver, String approveNote);

    // 项目停工
    public void pauseProject(String code, String updater, String remark);

    // 重新开工
    public void restartProject(String code, String updater, String remark);

    // 项目结束
    public void endProject(String code, String updater, String remark);

    // 更新项目发放薪资可延迟天数
    public void editSalaryDelayDays(String code, Integer salaryDelayDays);

    // 查询所在区域的项目
    public List<String> queryProjectCodeList(String province, String city,
            String area);

    public Project getProject(String code);

    public List<Project> queryProject(Project condition);

}
