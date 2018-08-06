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
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 添加操作指南
    public String addOperatorGuide(XN631120Req req);

    // 删除操作指南
    public void dropOperatorGuide(String code);

    // 编辑操作指南
    public void editOperatorGuide(XN631122Req req);

    public Paginable<OperatorGuide> queryOperatorGuidePage(int start, int limit,
            OperatorGuide condition);

    public List<OperatorGuide> queryOperatorGuideList(OperatorGuide condition);

    public OperatorGuide getOperatorGuide(String code);

}
