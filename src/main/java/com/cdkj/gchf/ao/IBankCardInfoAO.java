package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631751Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631765Req;
import com.cdkj.gchf.dto.req.XN631766Req;
import com.cdkj.gchf.dto.req.XN631767Req;

/**
 * @author admin
 */
@Component
public interface IBankCardInfoAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * @author old3
     * @// 添加银行卡 参建单位和个人。根据byssinussType区别
     * @date 2019-05-14
     * @param  * @param
     * @return java.lang.String
     */
    String addBankCardInfo(XN631750Req req);

    /**
     * @param req 改变银行卡状态 ：冻结 启用
     */
    void changeBankCardStatus(XN631751Req req);

    /**
     * @param req 编辑银行卡信息
     */
    void editBankCardInfo(XN631752Req req);

    /**
     * 根据code查询银行卡信息
     * @author old3
     * @date 2019-05-14
     * @param  * @param code
     * @return void
     */
    void queryBankCardInfoDetail(String code);

    /**
     * @author old3
     * @date 2019-05-14
     * @param  * @param req
     * @param start 开始页数
     * @param limit 每页数量
     * @param condition  条件
     * @return com.cdkj.gchf.bo.base.Paginable<com.cdkj.gchf.domain.BankCardInfo>
     */
    Paginable<BankCardInfo> queryBankCardInfoPage(XN631765Req req,
                                                  int start, int limit, BankCardInfo condition);

    /**
     * @param req 列表查银行卡
     */
    List<BankCardInfo> queryBankCardInfoList(XN631767Req req);

    /**
     * @param req 根据code查银行卡
     */
    Object getBankCardInfo(XN631766Req req);

}
