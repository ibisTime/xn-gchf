package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;

public interface IProjectAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 新增项目
    public String addProject(XN631350Req req);

    // 编辑项目
    public void editProject(XN631352Req req);

    // 项目开工
    public void startProject(String code, String approver, String approveNote);

    // 项目停工
    public void pauseProject(String code, String updater, String remark);

    // 项目重新开工
    public void restartProject(String code, String updater, String remark);

    // 项目结束
    public void endProject(String code, String updater, String remark);

    // 更新项目发放薪资可延迟天数
    public void editSalaryDelayDays(String code, Integer salaryDelayDays);

    // 分页查询
    Paginable<Project> queryProjectPage(int start, int limit,
            Project condition);

    // 详情查询
    public Project getProject(String code);

    // 列表查询
    public List<Project> queryProjectList(Project condition);
}
