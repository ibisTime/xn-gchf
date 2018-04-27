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

    // 修改项目
    public void editProject(XN631352Req req);

    // 审核项目
    public void auditProject(String code, String result, String auditor,
            String remark);

    // 分页查询
    Paginable<Project> queryProjectPage(int start, int limit,
            Project condition);

    // 详情查询
    public Project getProject(String code);

    // 列表查询
    public List<Project> queryProjectList(Project condition);

}
