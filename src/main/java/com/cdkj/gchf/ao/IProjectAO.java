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

    public String addProject(XN631600Req req);

    public void editProject(XN631602Req req);

    public Paginable<Project> queryProjectPage(int start, int limit,
            Project condition);

    public List<Project> queryProjectList(Project condition);

    public Project getProject(String code);

}
