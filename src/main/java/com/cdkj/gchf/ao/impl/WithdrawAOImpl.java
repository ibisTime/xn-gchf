package com.cdkj.gchf.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.WalletUtils;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.cdkj.gchf.ao.IWithdrawAO;
import com.cdkj.gchf.bo.IAccountBO;
import com.cdkj.gchf.bo.ISYSConfigBO;
import com.cdkj.gchf.bo.ISYSDictBO;
import com.cdkj.gchf.bo.ISmsOutBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWithdrawBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AmountUtil;
import com.cdkj.gchf.common.SysConstants;
import com.cdkj.gchf.domain.Account;
import com.cdkj.gchf.domain.SYSDict;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.Withdraw;
import com.cdkj.gchf.enums.EAccountType;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EJourBizTypeUser;
import com.cdkj.gchf.enums.EWithdrawStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class WithdrawAOImpl implements IWithdrawAO {

    private static Logger logger = Logger.getLogger(WithdrawAOImpl.class);

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISYSDictBO sysDictBO;

    @Override
    @Transactional
    public String applyOrderTradePwd(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote, String tradePwd, String googleCaptcha) {
        User user = userBO.getUser(applyUser);
        // if (StringUtils.isBlank(user.getRealName())) {
        // throw new BizException("xn000000", "请先进行实名认证");
        // }
        // 取现手续费
        BigDecimal fee = sysConfigBO
            .getBigDecimalValue(SysConstants.WITHDRAW_FEE);
        fee = Convert.toWei(fee, Unit.ETHER);
        if (amount.compareTo(fee) == 0 || amount.compareTo(fee) == -1) {
            throw new BizException("xn000000", "提现金额需大于手续费");
        }
        if (!WalletUtils.isValidAddress(payCardNo)) {
            throw new BizException("xn000000", "提现地址不符合以太坊规则，请仔细核对");
        }
        Account dbAccount = accountBO.getAccount(accountNumber);
        if (dbAccount.getAmount().subtract(dbAccount.getFrozenAmount())
            .compareTo(amount) == -1) {
            throw new BizException("xn000000", "可用余额不足");
        }

        // 判断本月是否次数已满，且现在只能有一笔取现未支付记录
        withdrawBO.doCheckTimes(dbAccount);

        // 生成取现订单
        String withdrawCode = withdrawBO.applyOrder(dbAccount, amount, fee,
            payCardInfo, payCardNo, applyUser, applyNote);
        // 冻结取现金额
        dbAccount = accountBO.frozenAmount(dbAccount, amount,
            EJourBizTypeUser.AJ_WITHDRAW_FROZEN.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW_FROZEN.getValue(), withdrawCode);

        // 通知相关人员
        String content = String.format(SysConstants.WITHDRAW, withdrawCode);
        SYSDict condition2 = new SYSDict();
        condition2.setParentKey(SysConstants.QX_SMS_NOTICE);
        List<SYSDict> mobiledDicts = sysDictBO.querySYSDictList(condition2);
        for (SYSDict sysDict : mobiledDicts) {
            // smsOutBO.sendSmsOut(sysDict.getDkey(), content);
        }

        return withdrawCode;
    }

    @Override
    @Transactional
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote) {

        // 取现手续费
        BigDecimal fee = sysConfigBO
            .getBigDecimalValue(SysConstants.WITHDRAW_FEE);
        fee = Convert.toWei(fee, Unit.ETHER);
        if (amount.compareTo(fee) == 0 || amount.compareTo(fee) == -1) {
            throw new BizException("xn000000", "提现金额需大于手续费");
        }
        if (!WalletUtils.isValidAddress(payCardNo)) {
            throw new BizException("xn000000", "提现地址不符合以太坊规则，请仔细核对");
        }
        Account dbAccount = accountBO.getAccount(accountNumber);
        if (dbAccount.getAmount().subtract(dbAccount.getFrozenAmount())
            .compareTo(amount) == -1) {
            throw new BizException("xn000000", "可用余额不足");
        }

        // 判断本月是否次数已满，且现在只能有一笔取现未支付记录
        withdrawBO.doCheckTimes(dbAccount);

        // 生成取现订单
        String withdrawCode = withdrawBO.applyOrder(dbAccount, amount, fee,
            payCardInfo, payCardNo, applyUser, applyNote);
        // 冻结取现金额
        dbAccount = accountBO.frozenAmount(dbAccount, amount,
            EJourBizTypeUser.AJ_WITHDRAW_FROZEN.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW_FROZEN.getValue(), withdrawCode);

        return withdrawCode;

    }

    @Override
    @Transactional
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote, String systemCode) {
        Withdraw data = withdrawBO.getWithdraw(code, systemCode);
        if (!EWithdrawStatus.toApprove.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待审批状态，无法审批");
        }
        if (EBoolean.YES.getCode().equals(approveResult)) {
            approveOrderYES(data, approveUser, approveNote);
        } else {
            approveOrderNO(data, approveUser, approveNote);
        }
    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder, String systemCode) {
        Withdraw data = withdrawBO.getWithdraw(code, systemCode);
        if (!EWithdrawStatus.Approved_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote, channelOrder);
        } else {
            payOrderNO(data, payUser, payNote, channelOrder);
        }
    }

    private void approveOrderYES(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_YES, approveUser,
            approveNote);
    }

    private void approveOrderNO(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_NO, approveUser,
            approveNote);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getCode(), "取现失败退回",
            data.getCode());

    }

    private void payOrderNO(Withdraw data, String payUser, String payNote,
            String payCode) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_NO, payUser, payNote,
            payCode, payCode, BigDecimal.ZERO);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.AJ_WITHDRAW.getCode(), "取现失败退回", data.getCode());
    }

    private void payOrderYES(Withdraw data, String payUser, String payNote,
            String payCode) {
        // withdrawBO.payOrder(data, EWithdrawStatus.Pay_YES, payUser, payNote,
        // payCode, payCode, BigDecimal.ZERO);
        // Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // // 先解冻，然后扣减余额
        // accountBO.unfrozenAmount(dbAccount, data.getAmount(),
        // EJourBizTypeUser.AJ_WITHDRAW.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getValue(), data.getCode());
        // accountBO.changeAmount(dbAccount.getAccountNumber(),
        // EChannelType.Offline, null, null, data.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getValue(), data.getAmount());
        // Account account = accountBO.getAccount(data.getAccountNumber());
        // if (ECurrency.CNY.getCode().equals(account.getCurrency())) {
        // // 冷钱包减钱
        // accountBO.changeAmount(
        // ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode(),
        // EChannelType.Offline, null, null, data.getCode(),
        // EJourBizTypeCold.AJ_PAY.getCode(), "ETH取现", data.getAmount()
        // .negate());
        // }
    }

    @Override
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition) {
        Paginable<Withdraw> page = withdrawBO.getPaginable(start, limit,
            condition);
        if (CollectionUtils.isNotEmpty(page.getList())) {
            List<Withdraw> list = page.getList();
            for (Withdraw withdraw : list) {
                User user = userBO.getUser(withdraw.getApplyUser());
                withdraw.setUser(user);
            }
        }
        return page;
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        List<Withdraw> list = withdrawBO.queryWithdrawList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Withdraw withdraw : list) {
                User user = userBO.getUser(withdraw.getApplyUser());
                withdraw.setUser(user);
            }
        }
        return list;
    }

    @Override
    public Withdraw getWithdraw(String code, String systemCode) {
        Withdraw withdraw = withdrawBO.getWithdraw(code, systemCode);
        User user = userBO.getUser(withdraw.getApplyUser());
        withdraw.setUser(user);
        return withdraw;
    }

    /**
     * 取现申请检查，验证参数，返回手续费
     * @param accountType
     * @param amount
     * @param systemCode
     * @param companyCode
     * @return 
     * @create: 2017年5月17日 上午7:53:01 xieyj
     * @history:
     */
    private BigDecimal doGetFee(String accountType, BigDecimal amount,
            String systemCode, String companyCode) {
        Map<String, String> argsMap = sysConfigBO.getConfigsMap();
        String qxfl = null;
        if (EAccountType.Customer.getCode().equals(accountType)) {
            qxfl = SysConstants.CUSERQXFL;
        } else {// 暂定其他账户类型不收手续费
            return BigDecimal.ZERO;
        }
        // 取现单笔最大金额
        String qxDbzdjeValue = argsMap.get(SysConstants.QXDBZDJE);
        if (StringUtils.isNotBlank(qxDbzdjeValue)) {
            BigDecimal qxDbzdje = BigDecimal
                .valueOf(Double.valueOf(qxDbzdjeValue));
            if (amount.compareTo(qxDbzdje) == 1) {
                throw new BizException("xn000000",
                    "取现单笔最大金额不能超过" + qxDbzdjeValue + "元。");
            }
        }
        String feeRateValue = argsMap.get(qxfl);
        Double feeRate = 0D;
        if (StringUtils.isNotBlank(feeRateValue)) {
            feeRate = Double.valueOf(feeRateValue);
        }
        return AmountUtil.mul(amount, feeRate);
    }
}
