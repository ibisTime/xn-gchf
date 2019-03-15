package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ChannelBank;

/**
 * @author: xieyj 
 * @since: 2016年11月10日 下午8:26:58 
 * @history:
 */
public interface IChannelBankDAO extends IBaseDAO<ChannelBank> {
    String NAMESPACE = IChannelBankDAO.class.getName().concat(".");

    /**
     * 更新渠道银行
     * @param data
     * @return 
     * @create: 2016年11月10日 下午7:46:43 xieyj
     * @history:
     */
    public int update(ChannelBank data);
}
