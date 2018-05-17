package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ISYSConfigBO;
import com.cdkj.gchf.bo.IWithdrawBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.SysConstants;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWithdrawDAO;
import com.cdkj.gchf.domain.Account;
import com.cdkj.gchf.domain.Withdraw;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EWithdrawStatus;
import com.cdkj.gchf.exception.BizException;

@Component
public class WithdrawBOImpl extends PaginableBOImpl<Withdraw>
        implements IWithdrawBO {

    @Autowired
    private IWithdrawDAO withdrawDAO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Override
    public String applyOrder(Account account, BigDecimal amount, BigDecimal fee,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("xn000000", "取现金额不能为0");
        }
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Withdraw.getCode());
        Withdraw data = new Withdraw();
        data.setCode(code);
        data.setAccountNumber(account.getAccountNumber());
        data.setType(account.getType());
        data.setAmount(amount);
        data.setFee(fee);

        data.setPayCardInfo(payCardInfo);
        // 取现户名，应该和银行卡户名一致
        data.setAccountName(account.getRealName());
        data.setPayCardNo(payCardNo);
        data.setStatus(EWithdrawStatus.toApprove.getCode());
        data.setApplyUser(applyUser);

        data.setApplyNote(applyNote);
        data.setApplyDatetime(new Date());
        data.setSystemCode(account.getSystemCode());
        data.setCompanyCode(account.getCompanyCode());
        withdrawDAO.insert(data);
        return code;
    }

    @Override
    public void approveOrder(Withdraw data, EWithdrawStatus status,
            String approveUser, String approveNote) {
        data.setStatus(status.getCode());
        data.setApproveUser(approveUser);
        data.setApproveNote(approveNote);
        data.setApproveDatetime(new Date());
        withdrawDAO.approveOrder(data);

    }

    @Override
    public void payOrder(Withdraw data, EWithdrawStatus status, String payUser,
            String payNote, String channelOrder, String payCode,
            BigDecimal payFee) {
        data.setStatus(status.getCode());
        data.setPayUser(payUser);
        data.setPayNote(payNote);
        data.setPayGroup(null);
        data.setPayCode(payCode);
        data.setPayFee(payFee);
        data.setChannelOrder(channelOrder);
        data.setPayDatetime(new Date());
        withdrawDAO.payOrder(data);
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        return withdrawDAO.selectList(condition);
    }

    @Override
    public Withdraw getWithdraw(String code, String systemCode) {
        Withdraw order = null;
        if (StringUtils.isNotBlank(code)) {
            Withdraw condition = new Withdraw();
            condition.setCode(code);
            condition.setSystemCode(systemCode);
            order = withdrawDAO.select(condition);
        }
        return order;
    }

    /** 
     * @see com.std.account.bo.IWithdrawBO#doCheckTimes(java.lang.String)
     */
    @Override
    public void doCheckTimes(Account account) {
        // 判断本月申请次数是否达到上限
        Map<String, String> argsMap = sysConfigBO.getConfigsMap();
        String monthTimesKey = SysConstants.CUSERMONTIMES;
        String monthTimesValue = argsMap.get(monthTimesKey);
        if (StringUtils.isNotBlank(monthTimesValue)) {// 月取现次数判断
            Withdraw condition = new Withdraw();
            condition.setAccountNumber(account.getAccountNumber());
            condition.setApplyDatetimeStart(DateUtil.getCurrentMonthFirstDay());
            condition.setApplyDatetimeEnd(DateUtil.getCurrentMonthLastDay());
            long totalCount = withdrawDAO.selectTotalCount(condition);
            long maxMonthTimes = Long.valueOf(monthTimesValue);
            if (totalCount >= maxMonthTimes) {
                throw new BizException("xn0000",
                    "每月取现最多" + maxMonthTimes + "次,本月申请次数已用尽");
            }
        }

        // 判断是否还有未处理的取现记录
        Withdraw condition = new Withdraw();
        condition.setAccountNumber(account.getAccountNumber());
        condition.setStatus("134");// 待申请，审核成功待支付
        if (withdrawDAO.selectTotalCount(condition) > 0) {
            throw new BizException("xn000000", "上笔取现申请还未处理成功，不能再次申请");
        }
    }

    @Override
    public Withdraw getWithdraw(String hash) {
        Withdraw withdraw = null;
        Withdraw condition = new Withdraw();
        condition.setChannelOrder(hash);
        List<Withdraw> results = withdrawDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            withdraw = results.get(0);
        }
        return withdraw;
    }

    @Override
    public BigDecimal getTotalWithdraw() {
        return withdrawDAO.selectTotalWithdraw();
    }

}
