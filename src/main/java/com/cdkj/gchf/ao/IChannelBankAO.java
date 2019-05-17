package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ChannelBank;

/**
 * @author
 */
@Component
public interface IChannelBankAO {
    String DEFAULT_ORDER_COLUMN = "id";

    /**
     * 添加渠道银行
     *
     * @param bankCode 银行代码
     * @param bankName 银行名称
     * @return
     */
    Long addChannelBank(String bankCode, String bankName);

    /**
     * @param id 删除渠道银行
     */
    void dropChannelBank(Long id);

    /**
     * todo 分页查渠道银行
     *
     * @param start     开始页
     * @param limit     每页数量
     * @param condition 条件
     * @return
     */
    Paginable<ChannelBank> queryChannelBankPage(int start, int limit,
                                                ChannelBank condition);

    /**
     * @param condition 列表查渠道银行
     * @return
     */
    List<ChannelBank> queryChannelBankList(ChannelBank condition);

    /**
     * @param id 通过id查渠道银行
     * @return
     */
    ChannelBank getChannelBank(Long id);

    /**
     * @param id       编辑渠道银行
     * @param bankCode
     * @param bankName
     */
    void editChannelBank(String id, String bankCode, String bankName);
}
