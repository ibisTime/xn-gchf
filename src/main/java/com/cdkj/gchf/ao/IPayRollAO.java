package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631812Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;

/**
 * @author old3
 */
@Component
public interface IPayRollAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 添加工资单- 入参包含工资单明细列表。添加工资单判断工资单是否存在。
     * 存在则添加工资单明细。反之添加工资单信息后添加明细
     * @param req
     * @return 主键code
     */
    String addPayRoll(XN631770Req req);

    /**
     * 根据code删工资单
     * @param code 主键code
     * @return int
     */
    int dropPayRoll(String code);

    /**
     * 修改工资单
     * @param req
     * @return int
     */
    int editPayRoll(XN631772Req req);

    /**
     * 导入工资单信息,添加工资单判断工资单是否存在
     * 存在则添加工资单明细。反之添加工资单信息后添加明细
     * @param req req
     */
    void importPayRollCodeList(XN631812Req req);

    /**
     * 上传工资单信息
     * @param userId  用户id
     * @param codeList 包含工资单明细主键的list列表
     */
    void uploadPayRollList(String userId, List<String> codeList);

    /**
     * 分页查工资单信息
     * @param start 开始页数
     * @param limit 每页记录数
     * @param condition 条件
     * @return 工资单列表
     */
    Paginable<PayRoll> queryPayRollPage(int start, int limit,
                                        PayRoll condition);

    /**
     * 按条件查询工资单
     * @param condition 条件
     * @return 工资单列表
     */
    List<PayRoll> queryPayRollList(PayRoll condition);

    /**
     * 根据code详细查工资单
     * @param code 工资单主键code
     * @return  PayRoll
     */
    PayRoll getPayRoll(String code);

    /**
     * 根据国家平台响应的 序列码请求国家平台上传结果。成功则刷新本地工资单编号。此方法为反射调用
     * @param code 主键code
     * @param payRollCode 国家平台返回的工资单编号
     */
    void refreshPayRollCodeByLocal(String code, String payRollCode);

    /****国家平台接口****/
    void uploadPayRoll(XN631920Req data);

    Paginable<PayRollDetail> queryPayRoll(XN631921Req req);
}
