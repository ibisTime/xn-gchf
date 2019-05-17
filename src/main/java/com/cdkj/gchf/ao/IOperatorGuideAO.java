package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.OperatorGuide;
import com.cdkj.gchf.dto.req.XN631120Req;
import com.cdkj.gchf.dto.req.XN631122Req;

/**
 * 操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午4:57:01 
 * @history:
 */
@Component
public interface IOperatorGuideAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加操作指南
     *
     * @param req
     * @return
     */
    String addOperatorGuide(XN631120Req req);

    /**
     * 删除操作指南
     *
     * @param code 根据主键code删除操作指南
     */
    void dropOperatorGuide(String code);

    /**
     * 编辑操作指南
     *
     * @param req
     */
    void editOperatorGuide(XN631122Req req);

    /**
     * 分页查操作指南
     *
     * @param start     开始页数
     * @param limit     每页记录数
     * @param condition 条件
     * @return
     */
    Paginable<OperatorGuide> queryOperatorGuidePage(int start, int limit,
                                                    OperatorGuide condition);

    /**
     * 按条件查询操作指南列表
     *
     * @param condition 条件
     * @return
     */
    List<OperatorGuide> queryOperatorGuideList(OperatorGuide condition);

    /**
     * 按code详细查操作指南
     *
     * @param code 主键code
     * @return
     */
    OperatorGuide getOperatorGuide(String code);

}
