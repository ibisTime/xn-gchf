package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631600Req;
import com.cdkj.gchf.dto.req.XN631602Req;

/**
 * @author silver
 */
@Component
public interface IProjectAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加项目，入参有项目施工许可证时同时添加项目许可证
     * @param req
     * @return
     */
    String addProject(XN631600Req req);

    /**
     * 修改项目
     * @param req  req
     */
    void editProject(XN631602Req req);

    /**
     * 根据code查项目
     * @param code 项目主键code
     * @return project
     */
    Project getProject(String code);

    /**
     * 分页查询项目
     * @param start 开始页数
     * @param limit 每页记录数
     * @param condition 条件
     * @return 项目列表
     */
    Paginable<Project> queryProjectPage(int start, int limit,
                                        Project condition);

    /**
     * 列表查项目信息
     * @param condition 条件
     * @return
     */
    List<Project> queryProjectList(Project condition);

}
