package com.cdkj.gchf.dao;

import java.math.BigDecimal;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Withdraw;

public interface IWithdrawDAO extends IBaseDAO<Withdraw> {
    String NAMESPACE = IWithdrawDAO.class.getName().concat(".");

    void approveOrder(Withdraw data);

    void payOrder(Withdraw data);

    public BigDecimal selectTotalWithdraw();

}
