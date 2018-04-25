package com.cdkj.gchf.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Account;
import com.cdkj.gchf.domain.HLOrder;
import com.cdkj.gchf.domain.Jour;
import com.cdkj.gchf.enums.EHLOrderStatus;

public interface IHLOrderBO extends IPaginableBO<HLOrder> {

    String applyOrder(Account account, Jour jour, BigDecimal applyAmount,
            String applyUser, String applyNote);

    void approveOrder(HLOrder order, EHLOrderStatus status, String approveUser,
            String approveNote);

    HLOrder getHLOrder(String code, String systemCode);

    List<HLOrder> queryHLOrderList(HLOrder condition);

}
