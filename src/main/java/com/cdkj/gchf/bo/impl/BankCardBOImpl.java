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
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631413Req;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class BankCardBOImpl extends PaginableBOImpl<BankCard>
        implements IBankCardBO {
    @Autowired
    private IBankCardDAO bankCardDAO;

    @Override
    public void addBankCard(XN631413Req req, Staff staff) {
        BankCard bankCard = new BankCard();
        String bankCardCode = OrderNoGenerater
            .generate(EGeneratePrefix.BankCard.getCode());
        bankCard.setCode(bankCardCode);
        bankCard.setCompanyCode(staff.getCompanyCode());
        bankCard.setStaffCode(req.getCode());
        bankCard.setStaffName(staff.getName());
        bankCard.setBankCode(req.getBankCode());

        bankCard.setBankName(req.getBankName());
        bankCard.setSubbranch(req.getSubbranch());
        bankCard.setBankcardNumber(req.getBankcardNumber());
        bankCard.setStatus(EBankCardStatus.Normal.getCode());
        bankCard.setUpdater(req.getUpdater());
        bankCard.setUpdateDatetime(new Date());

        bankCardDAO.insert(bankCard);
    }

    @Override
    public void refreshBankCard(BankCard bankCard) {
        bankCardDAO.update(bankCard);
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
