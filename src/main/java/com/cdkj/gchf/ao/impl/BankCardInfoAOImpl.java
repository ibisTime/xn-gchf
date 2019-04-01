package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.bo.IBankCardBankBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631767Req;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;

@Service(value = "bankCardInfoAOImpl")
public class BankCardInfoAOImpl implements IBankCardInfoAO {
    @Autowired
    private IBankCardBankBO bankCardBankBO;

    @Override
    public String addBankCardInfo(XN631750Req req) {
        return bankCardBankBO.saveBankCardInfo(req);
    }

    @Override
    public void dropBankCardInfo(String code) {
        BankCardInfo bankCardInfo = bankCardBankBO.getBankCardInfo(code);
        if (bankCardInfo.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631750", "银行卡信息已上传,无法删除");
        }
        bankCardBankBO.removeBankCardInfo(code);
    }

    @Override
    public Paginable<BankCardInfo> queryBankCardInfoPage(int start, int limit,
            BankCardInfo condition) {
        return bankCardBankBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<BankCardInfo> queryBankCardInfoList(XN631767Req req) {
        BankCardInfo condition = new BankCardInfo();
        BeanUtils.copyProperties(req, condition);
        return bankCardBankBO.queryBankCardInfoList(condition);
    }

    @Override
    public BankCardInfo getBankCardInfo(String code) {
        return bankCardBankBO.getBankCardInfo(code);
    }

    @Override
    public void editBankCardInfo(XN631752Req req) {
        BankCardInfo bankCardInfo = bankCardBankBO
            .getBankCardInfo(req.getCode());
        if (bankCardInfo.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631750", "银行卡信息已上传,无法删除");
        }
        bankCardBankBO.refreshBankCardInfo(req);
    }

    @Override
    public void queryBankCardInfoDetail(String code) {
        bankCardBankBO.getBankCardInfo(code);
    }

}
