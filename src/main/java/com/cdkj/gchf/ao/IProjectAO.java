package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631350Req;
import com.cdkj.gchf.dto.req.XN631352Req;
import com.cdkj.gchf.dto.req.XN631353Req;

public interface IProjectAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 新增项目
    public String addProject(XN631350Req req);

    // 修改项目
    public void editProject(XN631352Req req);

    // 提请申请项目
    public void toApprove(XN631353Req req);

    // 审核项目
    public void approveProject(String code, String result, String auditor,
            String remark);

    // 分页查询
    Paginable<Project> queryProjectPage(int start, int limit,
            Project condition);

    // 详情查询
    public Project getProject(String code);

    // 列表查询
    public List<Project> queryProjectList(Project condition);

    // 项目结束
    public void projectEnd(String code, String endDatetime, String updater,
            String remark);

    // 项目停工
    public void stopProject(String code, String updater, String remark);

    // 项目重新开工
    public void restartProject(String code, String updater, String remark);

}
