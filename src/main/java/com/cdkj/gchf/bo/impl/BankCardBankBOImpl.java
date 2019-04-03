package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IBankCardBankBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IBankCardInfoDAO;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EUploadStatus;

@Component
public class BankCardBankBOImpl extends PaginableBOImpl<BankCardInfo>
        implements IBankCardBankBO {
    @Autowired
    private IBankCardInfoDAO bankCardInfoDAO;

    @Override
    public String saveBankCardInfo(XN631750Req req) {
        String code = null;
        BankCardInfo bankCardInfo = new BankCardInfo();
        code = OrderNoGenerater
            .generate(EGeneratePrefix.BankCardInfo.getCode());
        bankCardInfo.setCode(code);
        BeanUtils.copyProperties(req, bankCardInfo);
        bankCardInfo.setBankName(req.getBankName());
        bankCardInfo.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        bankCardInfo.setUpdateDatetime(new Date(System.currentTimeMillis()));
        bankCardInfo.setStatus(EBankCardStatus.Normal.getCode());
        bankCardInfo.setCreateDatetime(new Date(System.currentTimeMillis()));
        bankCardInfoDAO.insert(bankCardInfo);
        return code;
    }

    @Override
    public void removeBankCardInfo(String code) {
        BankCardInfo bankCardInfo = new BankCardInfo();
        bankCardInfo.setCode(code);
        bankCardInfoDAO.delete(bankCardInfo);
    }

    @Override
    public void refreshBankCardInfo(XN631752Req req) {
        BankCardInfo condition = new BankCardInfo();
        condition.setCode(req.getCode());

        BankCardInfo bankCardInfo = bankCardInfoDAO.select(condition);
        if (req.getBankName() != null) {
            bankCardInfo.setBankName(req.getBankName());
        }
        if (req.getBankNumber() != null) {
            bankCardInfo.setBankNumber(req.getBankNumber());
        }
        if (req.getBankLinkNumber() != null) {
            bankCardInfo.setBankLinkNumber(req.getBankLinkNumber());
        }
        bankCardInfoDAO.updateBankCardInfo(bankCardInfo);
    }

    @Override
    public List<BankCardInfo> queryBankCardInfoList(BankCardInfo condition) {
        return bankCardInfoDAO.selectList(condition);
    }

    @Override
    public BankCardInfo getBankCardInfo(Long id) {
        return null;
    }

    @Override
    public BankCardInfo getBankCardInfo(String code) {
        BankCardInfo condition = new BankCardInfo();
        condition.setCode(code);
        return bankCardInfoDAO.select(condition);
    }

    @Override
    public int updateBankCardInfoStatus(String code) {
        BankCardInfo condition = new BankCardInfo();
        condition.setCode(code);
        BankCardInfo select = bankCardInfoDAO.select(condition);
        if (select.getStatus() != null) {
            if (select.getStatus().equals(EBankCardStatus.Normal.getCode())) {
                select.setStatus(EBankCardStatus.Freeze.getCode());
            } else if (select.getStatus()
                .equals(EBankCardStatus.Freeze.getCode())) {
                select.setStatus(EBankCardStatus.Normal.getCode());
            }
        }
        select.setUpdateDatetime(new Date(System.currentTimeMillis()));
        return bankCardInfoDAO.updateBankCardInfoStatus(select);
    }

}
