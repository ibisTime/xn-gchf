package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631620Req;
import com.cdkj.gchf.dto.req.XN631622Req;

/**
 * @author silver
 */
@Component
public interface IProjectConfigAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加项目配置
     *
     * @param req
     * @return
     */
    String addProjectConfig(XN631620Req req);

    /**
     * 删除项目配置
     *
     * @param code
     * @return
     */
    int dropProjectConfig(String code);

    /**
     * 编辑项目配置
     *
     * @param req
     */
    void editProjectConfig(XN631622Req req);

    /**
     * @param code
     * @param userId
     * @param remark
     */
    void startStop(String code, String userId, String remark);

    /**
     * 分页查项目配置
     *
     * @param start     开始页
     * @param limit     每页记录数
     * @param condition 条件
     * @return
     */
    Paginable<ProjectConfig> queryProjectConfigPage(int start, int limit,
                                                    ProjectConfig condition);

    /**
     * 列表查项目配置
     *
     * @param condition 条件
     * @return
     */
    List<ProjectConfig> queryProjectConfigList(ProjectConfig condition);

    /**
     * 根据主键code查项目配置
     *
     * @param code 主键code
     * @return
     */
    ProjectConfig getProjectConfig(String code);

}
