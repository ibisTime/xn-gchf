package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.ChannelBank;

public interface IChannelBankBO extends IPaginableBO<ChannelBank> {

    public Long saveChannelBank(String bankCode, String bankName);

    public void removeChannelBank(ChannelBank data);

    public void refreshChannelBank(ChannelBank data, String bankCode,
            String bankName);

    public List<ChannelBank> queryChannelBankList(ChannelBank condition);

    public ChannelBank getChannelBank(Long id);

    public ChannelBank getChannelBank(String bankCode);

}
