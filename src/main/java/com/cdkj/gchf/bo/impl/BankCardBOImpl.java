package com.cdkj.gchf.bo.impl;

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
    public void addBankCard(BankCard data) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.BankCard.getCode());
        data.setCode(code);
        bankCardDAO.insert(data);

    }

    @Override
    public void removeBankCard(String code) {
    }

    @Override
    public void refreshBankCard(BankCard data) {
        bankCardDAO.update(data);
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
        }
        return data;
    }

}
