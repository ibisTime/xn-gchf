package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IBankCardDAO;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.dto.req.XN631420Req;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.ECardNumberStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class BankCardBOImpl extends PaginableBOImpl<BankCard>
        implements IBankCardBO {
    @Autowired
    private IBankCardDAO bankCardDAO;

    @Autowired
    private IEmployBO employBO;

    @Override
    public String addBankCard(XN631420Req req) {
        Employ employ = employBO.getEmploy(req.getEmployCode());

        BankCard bankCard = new BankCard();
        String bankCardCode = OrderNoGenerater
            .generate(EGeneratePrefix.BankCard.getCode());
        bankCard.setCode(bankCardCode);
        bankCard.setEmployCode(employ.getCode());
        bankCard.setProjectCode(employ.getProjectCode());
        bankCard.setProjectName(employ.getProjectName());
        bankCard.setStaffCode(employ.getStaffCode());

        bankCard.setStaffName(employ.getStaffName());
        bankCard.setBankCode(req.getBankCode());
        bankCard.setBankName(req.getBankName());
        bankCard.setSubbranch(req.getSubbranch());
        bankCard.setBankcardNumber(req.getBankcardNumber());

        bankCard.setStatus(EBankCardStatus.Normal.getCode());
        bankCard.setNumberStatus(
            getNumberStatus(req.getSubbranch(), req.getBankcardNumber()));
        bankCard.setUpdater(req.getUpdater());

        bankCard.setUpdateDatetime(new Date());
        bankCard.setCreateDatetime(new Date());
        bankCard.setRemark(req.getRemark());
        bankCardDAO.insert(bankCard);
        return bankCardCode;
    }

    @Override
    public String addBankCard(String employCode, String bankCode,
            String bankName, String subbranch, String bankcardNumber,
            String updater) {
        Employ employ = employBO.getEmploy(employCode);

        BankCard bankCard = new BankCard();
        String bankCardCode = OrderNoGenerater
            .generate(EGeneratePrefix.BankCard.getCode());
        bankCard.setCode(bankCardCode);
        bankCard.setEmployCode(employ.getCode());
        bankCard.setStaffCode(employ.getStaffCode());
        bankCard.setStaffName(employ.getStaffName());
        bankCard.setProjectCode(employ.getProjectCode());

        bankCard.setProjectName(employ.getProjectName());
        bankCard.setBankCode(bankCode);
        bankCard.setBankName(bankName);
        bankCard.setSubbranch(subbranch);
        bankCard.setBankcardNumber(bankcardNumber);

        bankCard.setStatus(EBankCardStatus.Normal.getCode());
        bankCard.setNumberStatus(getNumberStatus(subbranch, bankcardNumber));
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

        data.setNumberStatus(getNumberStatus(subbranch, bankcardNumber));
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        bankCardDAO.update(data);
    }

    @Override
    public BankCard isBankCardExist(String employCode) {
        BankCard data = null;
        if (StringUtils.isNotBlank(employCode)) {
            BankCard condition = new BankCard();
            condition.setEmployCode(employCode);
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
    public BankCard getEmployBankCard(String employCode) {
        BankCard data = null;
        if (StringUtils.isNotBlank(employCode)) {
            BankCard condition = new BankCard();
            condition.setEmployCode(employCode);
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

    private String getNumberStatus(String subbranch, String bankCardNumber) {
        String bankCardNumberStatus = ECardNumberStatus.Non_Input.getCode();

        if (StringUtils.isNotBlank(bankCardNumber)
                && StringUtils.isNotBlank(subbranch)) {
            bankCardNumberStatus = ECardNumberStatus.Inputed.getCode();
        }

        return bankCardNumberStatus;
    }
}
