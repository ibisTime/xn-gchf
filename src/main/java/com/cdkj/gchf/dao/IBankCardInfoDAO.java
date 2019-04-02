package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.BankCardInfo;

/**
 * 
 * @ClassName:  IBankCardInfo   
 * @Description:TODO
 * @author: Old3
 * @date:   2019年3月22日 上午10:44:55     
 * @Copyright:
 */
public interface IBankCardInfoDAO extends IBaseDAO<BankCardInfo> {
    String NAMESPACE = IBankCardInfoDAO.class.getName().concat(".");

    /**
     * 
     * @Description: TODO
     * @param: @param 更新银行卡信息
     * @param: @return      
     * @return: int      
     * @throws
     */
    int updateBankCardInfo(BankCardInfo condition);

    int updateBankCardInfoStatus(BankCardInfo condition);
}
