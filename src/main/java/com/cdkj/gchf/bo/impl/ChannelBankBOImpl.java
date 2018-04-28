package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IChannelBankBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IChannelBankDAO;
import com.cdkj.gchf.domain.ChannelBank;
import com.cdkj.gchf.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月10日 下午8:31:09 
 * @history:
 */
@Component
public class ChannelBankBOImpl extends PaginableBOImpl<ChannelBank>
        implements IChannelBankBO {

    @Autowired
    private IChannelBankDAO channelBankDAO;

    @Override
    public boolean isChannelBankExist(Long id) {
        ChannelBank condition = new ChannelBank();
        condition.setId(id);
        if (channelBankDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int saveChannelBank(ChannelBank data) {
        int count = 0;
        if (data != null) {
            count = channelBankDAO.insert(data);
        }
        return count;
    }

    @Override
    public int removeChannelBank(Long id) {
        int count = 0;
        if (id != null) {
            ChannelBank data = new ChannelBank();
            data.setId(id);
            count = channelBankDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshChannelBank(ChannelBank data) {
        int count = 0;
        if (data != null) {
            count = channelBankDAO.update(data);
        }
        return count;
    }

    @Override
    public List<ChannelBank> queryChannelBankList(ChannelBank condition) {
        return channelBankDAO.selectList(condition);
    }

    @Override
    public ChannelBank getChannelBank(Long id) {
        ChannelBank data = null;
        if (id != null) {
            ChannelBank condition = new ChannelBank();
            condition.setId(id);
            data = channelBankDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "渠道银行不存在");
            }
        }
        return data;
    }

    @Override
    public ChannelBank getChannelBank(String bankCode) {
        ChannelBank data = null;
        if (StringUtils.isNotBlank(bankCode)) {
            ChannelBank condition = new ChannelBank();
            condition.setBankCode(bankCode);
            List<ChannelBank> list = channelBankDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }
}
