package com.cdkj.gchf.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Account;
import com.cdkj.gchf.domain.Withdraw;
import com.cdkj.gchf.enums.EWithdrawStatus;

public interface IWithdrawBO extends IPaginableBO<Withdraw> {

    public void doCheckTimes(Account account);

    String applyOrder(Account account, BigDecimal amount, BigDecimal fee,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    void approveOrder(Withdraw data, EWithdrawStatus status,
            String approveUser, String approveNote);

    void payOrder(Withdraw data, EWithdrawStatus status, String payUser,
            String payNote, String channelOrder, String payCode,
            BigDecimal payFee);

    List<Withdraw> queryWithdrawList(Withdraw condition);

    Withdraw getWithdraw(String code, String systemCode);

    public Withdraw getWithdraw(String hash);

    public BigDecimal getTotalWithdraw();
}
