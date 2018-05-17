package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IChannelBankAO;
import com.cdkj.gchf.bo.IChannelBankBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.ChannelBank;

@Service
public class ChannelBankAOImpl implements IChannelBankAO {

    @Autowired
    private IChannelBankBO channelBankBO;

    @Override
    public Long addChannelBank(String bankCode, String bankName) {
        return channelBankBO.saveChannelBank(bankCode, bankName);
    }

    @Override
    public void editChannelBank(String id, String bankCode, String bankName) {
        ChannelBank data = channelBankBO
            .getChannelBank(StringValidater.toLong(id));
        channelBankBO.refreshChannelBank(data, bankCode, bankName);
    }

    @Override
    public void dropChannelBank(Long id) {
        ChannelBank data = channelBankBO.getChannelBank(id);
        channelBankBO.removeChannelBank(data);
    }

    @Override
    public Paginable<ChannelBank> queryChannelBankPage(int start, int limit,
            ChannelBank condition) {
        return channelBankBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<ChannelBank> queryChannelBankList(ChannelBank condition) {
        return channelBankBO.queryChannelBankList(condition);
    }

    @Override
    public ChannelBank getChannelBank(Long id) {
        return channelBankBO.getChannelBank(id);
    }
}
