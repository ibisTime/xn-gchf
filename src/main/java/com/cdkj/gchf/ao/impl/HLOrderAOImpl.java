package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IHLOrderAO;
import com.cdkj.gchf.bo.IAccountBO;
import com.cdkj.gchf.bo.IHLOrderBO;
import com.cdkj.gchf.bo.IJourBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.HLOrder;
import com.cdkj.gchf.domain.Jour;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EHLOrderStatus;
import com.cdkj.gchf.enums.EJourStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class HLOrderAOImpl implements IHLOrderAO {
    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IHLOrderBO hlOrderBO;

    @Autowired
    private IJourBO jourBO;


    @Override
    @Transactional
    public void approveOrder(String code, String adjustResult,
            String approveUser, String approveNote, String systemCode) {
        HLOrder order = hlOrderBO.getHLOrder(code, systemCode);
        Jour jour = jourBO.getJourNotException(order.getJourCode(),
            order.getSystemCode());
        if (null != jour) {
            approveOrderNow(order, jour, adjustResult, approveUser, approveNote);// 现在流水调账
        } else {
            approveOrderHistory(order, jour, adjustResult, approveUser,
                approveNote);// 历史流水调账
        }
    }

    private void approveOrderNow(HLOrder order, Jour jour, String adjustResult,
            String approveUser, String approveNote) {
        if (!EHLOrderStatus.toApprove.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "该单号不处于调账待审核状态");
        }
        if (!EJourStatus.Checked_NO.getCode().equals(jour.getStatus())) {
            throw new BizException("xn000000", "该流水不处于调账待审核状态");
        }
        if (EBoolean.YES.getCode().equals(adjustResult)) {
            hlOrderBO.approveOrder(order, EHLOrderStatus.Approved_YES,
                approveUser, approveNote);
            jourBO.adjustJourYES(jour, approveUser, approveNote);
            accountBO.changeAmountForHL(order);
        } else {
            hlOrderBO.approveOrder(order, EHLOrderStatus.Approved_NO,
                approveUser, approveNote);
            jourBO.adjustJourNO(jour, approveUser, approveNote);
        }
    }

    private void approveOrderHistory(HLOrder order, Jour jour,
            String adjustResult, String approveUser, String approveNote) {
        if (!EHLOrderStatus.toApprove.getCode().equals(order.getStatus())) {
            throw new BizException("xn000000", "该单号不处于调账待审核状态");
        }
        if (!EJourStatus.Checked_NO.getCode().equals(jour.getStatus())) {
            throw new BizException("xn000000", "该流水不处于调账待审核状态");
        }
        if (EBoolean.YES.getCode().equals(adjustResult)) {
            hlOrderBO.approveOrder(order, EHLOrderStatus.Approved_YES,
                approveUser, approveNote);
            accountBO.changeAmountForHL(order);
        } else {
            hlOrderBO.approveOrder(order, EHLOrderStatus.Approved_NO,
                approveUser, approveNote);
        }
    }

    @Override
    public Paginable<HLOrder> queryHLOrderPage(int start, int limit,
            HLOrder condition) {
        return hlOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<HLOrder> queryHLOrderList(HLOrder condition) {
        return hlOrderBO.queryHLOrderList(condition);
    }

    @Override
    public HLOrder getHLOrder(String code, String systemCode) {
        HLOrder hLOrder = hlOrderBO.getHLOrder(code, systemCode);
        Jour jour = jourBO.getJourNotException(hLOrder.getJourCode(),
            systemCode);
        if (null == jour) {
        }
        List<Jour> jourList = new ArrayList<Jour>();
        jourList.add(jour);
        hLOrder.setJourList(jourList);
        return hLOrder;
    }
}
