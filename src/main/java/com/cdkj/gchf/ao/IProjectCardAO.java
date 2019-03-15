package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectCard;
import com.cdkj.gchf.dto.req.XN631362Req;

@Component
public interface IProjectCardAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void editProjectCard(XN631362Req req);

    public Paginable<ProjectCard> queryProjectCardPage(int start, int limit,
            ProjectCard condition);

    public List<ProjectCard> queryProjectCardList(ProjectCard condition);

    public ProjectCard getProjectCard(String code);

}
