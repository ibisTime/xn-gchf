package com.cdkj.gchf.ao;

import java.util.List;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ChannelBank;

public interface IChannelBankAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public Long addChannelBank(String bankCode, String bankName);

    public void dropChannelBank(Long id);

    public Paginable<ChannelBank> queryChannelBankPage(int start, int limit,
            ChannelBank condition);

    public List<ChannelBank> queryChannelBankList(ChannelBank condition);

    public ChannelBank getChannelBank(Long id);

    public void editChannelBank(String id, String bankCode, String bankName);
}
