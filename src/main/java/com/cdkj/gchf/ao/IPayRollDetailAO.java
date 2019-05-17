package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631810Req;

@Component
public interface IPayRollDetailAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 删除多条工资单明细。
     * 已上传的不可删除
     *
     * @param codeList 包含工资单明细的主键list集合
     */
    void dropPayRollDetail(List<String> codeList);

    /**
     * 编辑工资单明细
     *
     * @param req req
     * @return int
     */
    int editPayRollDetail(XN631810Req req);

    /**
     * 分页查工资单明细
     *
     * @param start     开始页数
     * @param limit     每页记录数
     * @param condition 条件
     * @return 分页列表
     */
    Paginable<PayRollDetail> queryPayRollDetailPage(int start, int limit,
                                                    PayRollDetail condition);

    /**
     * 按条件列表查询工资单明细
     *
     * @param condition 条件
     * @return 列表
     */
    List<PayRollDetail> queryPayRollDetailList(PayRollDetail condition);

    /**
     * @param code 工资单明细主键code
     * @return PayRollDetail
     */
    PayRollDetail getPayDetailRoll(String code);

}
