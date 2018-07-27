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
import com.cdkj.gchf.dto.req.XN631420Req;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class BankCardBOImpl extends PaginableBOImpl<BankCard>
        implements IBankCardBO {
    @Autowired
    private IBankCardDAO bankCardDAO;

    @Override
    public String addBankCard(XN631420Req req) {
        BankCard bankCard = new BankCard();
        String bankCardCode = OrderNoGenerater
            .generate(EGeneratePrefix.BankCard.getCode());
        bankCard.setCode(bankCardCode);
        bankCard.setProjectCode(req.getProjectCode());
        bankCard.setStaffCode(req.getStaffCode());
        bankCard.setStaffName(req.getStaffName());
        bankCard.setBankCode(req.getBankCode());

        bankCard.setBankName(req.getBankName());
        bankCard.setSubbranch(req.getSubbranch());
        bankCard.setBankcardNumber(req.getBankcardNumber());
        bankCard.setStatus(EBankCardStatus.Normal.getCode());
        bankCard.setUpdater(req.getUpdater());

        bankCard.setUpdateDatetime(new Date());
        bankCard.setCreateDatetime(new Date());
        bankCard.setRemark(req.getRemark());
        bankCardDAO.insert(bankCard);
        return bankCardCode;
    }

    @Override
    public String addBankCard(Staff staff, String projectCode, String bankCode,
            String bankName, String subbranch, String bankcardNumber,
            String updater) {
        BankCard bankCard = new BankCard();
        String bankCardCode = OrderNoGenerater
            .generate(EGeneratePrefix.BankCard.getCode());
        bankCard.setCode(bankCardCode);
        bankCard.setStaffCode(staff.getCode());
        bankCard.setStaffName(staff.getName());
        bankCard.setProjectCode(projectCode);
        bankCard.setBankCode(bankCode);

        bankCard.setBankName(bankName);
        bankCard.setSubbranch(subbranch);
        bankCard.setBankcardNumber(bankcardNumber);
        bankCard.setStatus(EBankCardStatus.Normal.getCode());
        bankCard.setUpdater(updater);

        bankCard.setUpdateDatetime(new Date());
        bankCard.setCreateDatetime(new Date());
        bankCardDAO.insert(bankCard);
        return bankCardCode;
    }

    @Override
    public void refreshBankCard(String code, String bankCode, String bankName,
            String subbranch, String bankcardNumber, String updater,
            String remark) {
        BankCard data = new BankCard();
        data.setCode(code);
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setSubbranch(subbranch);
        data.setBankcardNumber(bankcardNumber);

        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        bankCardDAO.update(data);
    }

    @Override
    public BankCard isBankCardExist(String staffCode, String projectCode) {
        BankCard data = null;
        if (StringUtils.isNotBlank(staffCode)
                && StringUtils.isNotBlank(projectCode)) {
            BankCard condition = new BankCard();
            condition.setStaffCode(staffCode);
            condition.setProjectCode(projectCode);
            data = bankCardDAO.select(condition);
        }
        return data;
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
            initBankcard(data);
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
            initBankcard(data);
        }
        return data;
    }

    @Override
    public BankCard getBankCard(String staffCode, String projectCode) {
        BankCard data = null;
        if (StringUtils.isNotBlank(staffCode)
                && StringUtils.isNotBlank(projectCode)) {
            BankCard condition = new BankCard();
            condition.setStaffCode(staffCode);
            condition.setProjectCode(projectCode);
            data = bankCardDAO.select(condition);
            initBankcard(data);
        }
        return data;
    }

    private void initBankcard(BankCard data) {
        if (data != null && null != data.getBankName()
                && null != data.getSubbranch()) {
            data.setBankSubbranchName(
                data.getBankName().concat(data.getSubbranch()));
        }
    }
}
