package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IChannelBankAO;
import com.cdkj.gchf.bo.IChannelBankBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.ChannelBank;
import com.cdkj.gchf.exception.BizException;

@Service
public class ChannelBankAOImpl implements IChannelBankAO {

    @Autowired
    private IChannelBankBO channelBankBO;

    @Override
    public void addChannelBank(ChannelBank data) {
        channelBankBO.saveChannelBank(data);
    }

    @Override
    public void editChannelBank(ChannelBank data) {
        if (!channelBankBO.isChannelBankExist(data.getId())) {
            throw new BizException("xn0000", "渠道银行序号不存在");
        }
        channelBankBO.refreshChannelBank(data);
    }

    @Override
    public void dropChannelBank(Long id) {
        if (!channelBankBO.isChannelBankExist(id)) {
            throw new BizException("xn0000", "渠道银行序号不存在");
        }
        channelBankBO.removeChannelBank(id);
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
