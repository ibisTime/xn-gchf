package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IBankCardDAO;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class BankCardBOImpl extends PaginableBOImpl<BankCard>
        implements IBankCardBO {

    @Autowired
    private IBankCardDAO bankCardDAO;

    @Override
    public void addBankCard(String staffCode, String name, String bankCode,
            String bankName, String bankcardNumber, String subbranch,
            String updater, Date updateDatetime, String remark) {

        BankCard data = new BankCard();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.BankCard.getCode());
        data.setCode(code);
        data.setStaffCode(staffCode);
        data.setStaffName(name);
        data.setBankCode(bankCode);
        data.setBankName(bankName);

        data.setBankcardNumber(bankcardNumber);
        data.setSubbranch(subbranch);
        data.setCreateDatetime(new Date());
        data.setUpdater(updater);
        data.setUpdateDatetime(updateDatetime);

        data.setRemark(remark);
        bankCardDAO.insert(data);

    }

    @Override
    public int removeBankCard(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            BankCard data = new BankCard();
            data.setCode(code);
            count = bankCardDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshBankCard(BankCard data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = bankCardDAO.update(data);
        }
        return count;
    }

    @Override
    public List<BankCard> queryBankCardList(BankCard condition) {
        return bankCardDAO.selectList(condition);
    }

    @Override
    public BankCard getBankCard(String code) {
        BankCard data = null;
        if (StringUtils.isNotBlank(code)) {
            BankCard condition = new BankCard();
            condition.setCode(code);
            data = bankCardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "工资卡卡不存在");
            }
        }
        return data;
    }

    @Override
    public BankCard getBankCardByStaff(String staffCode) {
        BankCard data = null;
        if (StringUtils.isNotBlank(staffCode)) {
            BankCard condition = new BankCard();
            condition.setStaffCode(staffCode);
            data = bankCardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "工资卡卡不存在");
            }
        }
        return data;
    }

}
