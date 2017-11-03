package com.cdkj.coin.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.coin.ao.IChargeAO;
import com.cdkj.coin.bo.IAccountBO;
import com.cdkj.coin.bo.IChargeBO;
import com.cdkj.coin.bo.IUserBO;
import com.cdkj.coin.bo.base.Paginable;
import com.cdkj.coin.domain.Account;
import com.cdkj.coin.domain.Charge;
import com.cdkj.coin.domain.User;
import com.cdkj.coin.enums.EBizType;
import com.cdkj.coin.enums.EBoolean;
import com.cdkj.coin.enums.EChannelType;
import com.cdkj.coin.enums.EChargeStatus;
import com.cdkj.coin.enums.ECurrency;
import com.cdkj.coin.exception.BizException;

@Service
public class ChargeAOImpl implements IChargeAO {
    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String applyOrder(String accountNumber, String jourBizType,
            Long amount, String payCardInfo, String payCardNo,
            String applyUser, String applyNote) {
        if (amount <= 0) {
            throw new BizException("xn000000", "充值金额需大于零");
        }
        Account account = accountBO.getAccount(accountNumber);
        // 生成充值订单
        String code = chargeBO.applyOrderOffline(account,
            EBizType.getBizType(jourBizType), amount, payCardInfo, payCardNo,
            applyUser, applyNote);
        return code;
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String systemCode) {
        Charge data = chargeBO.getCharge(code, systemCode);
        if (!EChargeStatus.toPay.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote);
        } else {
            payOrderNO(data, payUser, payNote);
        }
    }

    private void payOrderNO(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, false, payUser, payNote);
    }

    private void payOrderYES(Charge data, String payUser, String payNote) {
        chargeBO.payOrder(data, true, payUser, payNote);
        // 账户加钱
        accountBO.changeAmount(data.getAccountNumber(), EChannelType.Offline,
            null, null, data.getCode(), EBizType.AJ_CHARGE, "线下充值",
            BigDecimal.valueOf(data.getAmount()));
        Account account = accountBO.getAccount(data.getAccountNumber());
        if (ECurrency.CNY.getCode().equals(account.getCurrency())) {
            // 托管账户加钱
            accountBO.changeAmount(data.getCompanyCode(), EChannelType.Offline,
                null, null, data.getCode(), EBizType.AJ_CHARGE, "线下充值",
                BigDecimal.valueOf(data.getAmount()));
        }
    }

    @Override
    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition) {
        Paginable<Charge> page = chargeBO.getPaginable(start, limit, condition);
        if (CollectionUtils.isNotEmpty(page.getList())) {
            List<Charge> list = page.getList();
            for (Charge charge : list) {
                User user = userBO.getUser(charge.getApplyUser());
                charge.setUser(user);
            }
        }
        return page;
    }

    @Override
    public List<Charge> queryChargeList(Charge condition) {
        List<Charge> list = chargeBO.queryChargeList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Charge charge : list) {
                User user = userBO.getUser(charge.getApplyUser());
                charge.setUser(user);
            }
        }
        return list;
    }

    @Override
    public Charge getCharge(String code, String systemCode) {
        Charge charge = chargeBO.getCharge(code, systemCode);
        User user = userBO.getUser(charge.getApplyUser());
        charge.setUser(user);
        return charge;
    }

}
