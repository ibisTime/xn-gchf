package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;

@Component
public interface IProjectAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加项目
     */
    public String addProject(XN631600Req req);

    /**
     * 修改项目
     */
    public void editProject(XN631602Req req);

    /**
     * 根据code查项目 
     */
    public Project getProject(String code);

    /**
     * 分页查询项目
     */
    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition);

    /**
     * 列表查
     */
    public List<Project> queryProjectList(Project condition);

    /**
     * 根据code查询项目
     */

}
