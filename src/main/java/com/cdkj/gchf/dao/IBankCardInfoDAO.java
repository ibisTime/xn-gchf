package com.cdkj.gchf.dao;

import java.util.List;

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

    int updateBankCardInfo(BankCardInfo condition);

    int updateBankCardInfoStatus(BankCardInfo condition);

    void updateBankCardInfoStatusByBussiness(BankCardInfo condition);

    List<BankCardInfo> selectBankCardByIdcard(List<String> idcards);
}
