package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IBankCardBankBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IBankCardInfoDAO;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;
import com.cdkj.gchf.enums.EBankCardBussinessType;
import com.cdkj.gchf.enums.EBankCardCodeType;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;

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
        bankCardInfo.setBankName(
                EBankCardCodeType.getBankCardType(req.getBankCode()).getValue());
        // bankCardInfo.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        bankCardInfo.setUpdateDatetime(new Date(System.currentTimeMillis()));
        bankCardInfo.setStatus(EBankCardStatus.Normal.getCode());
        bankCardInfo.setSubranch(req.getSubranch());
        bankCardInfo.setBankCode(req.getBankCode());
        bankCardInfo.setCreateDatetime(new Date(System.currentTimeMillis()));
        bankCardInfoDAO.insert(bankCardInfo);
        return code;
    }


    @Override
    public String saveWorkerBankCardInfo(ProjectWorker projectWorker, String bankCode,
            String bankNumber, String subranch, String bankLinkNumber, String remark) {

        BankCardInfo bankCardInfo = new BankCardInfo();
        String code = OrderNoGenerater.generate(EGeneratePrefix.BankCardInfo.getCode());
        bankCardInfo.setBusinessType(EBankCardBussinessType.USER.getCode());
        bankCardInfo.setBankLinkNumber(bankNumber);
        bankCardInfo.setBankCode(bankCode);
        bankCardInfo.setBankName(EBankCardCodeType.getBankCardType(bankCode).getValue());
        bankCardInfo.setBankLinkNumber(bankLinkNumber);
        bankCardInfo.setRemark(remark);
        bankCardInfo.setSubranch(subranch);
        bankCardInfo.setStatus(EBankCardStatus.Normal.getCode());
        bankCardInfo.setCreateDatetime(new Date(System.currentTimeMillis()));
        bankCardInfo.setUpdater("admin");
        bankCardInfo.setUpdateDatetime(new Date(System.currentTimeMillis()));
        bankCardInfo.setCode(code);
        bankCardInfoDAO.insert(bankCardInfo);
        return code;
    }

    @Override
    public void refreshBankCardInfo(XN631752Req req) {
        BankCardInfo condition = new BankCardInfo();
        condition.setCode(req.getCode());
        if (StringUtils.isNotEmpty(req.getBankCode())) {
            condition.setBankCode(req.getBankCode());
            condition.setBankName(EBankCardCodeType
                    .getBankCardType(req.getBankCode()).getValue());
        }

        if (StringUtils.isNotBlank(req.getBankNumber())) {
            condition.setBankNumber(req.getBankNumber());
        }
        if (StringUtils.isNotBlank(req.getBankLinkNumber())) {
            condition.setBankLinkNumber(req.getBankLinkNumber());
        }
        if (StringUtils.isNotBlank(req.getSubranch())) {
            condition.setSubranch(req.getSubranch());
        }
        bankCardInfoDAO.updateBankCardInfo(condition);
    }


    @Override
    public BankCardInfo getBankCardInfoByNum(String payRollBankCardNumber) {
        BankCardInfo bankCardInfo = new BankCardInfo();
        bankCardInfo.setBankNumber(payRollBankCardNumber);
        bankCardInfo.setStatus(EBankCardStatus.Normal.getCode());
        bankCardInfo.setBusinessType(EBankCardBussinessType.USER.getCode());
        return bankCardInfoDAO.select(bankCardInfo);
    }

    @Override
    public BankCardInfo getBankCardByIdCardNumBankNum(String projectWorkerCode,
                                                      String bankNum) {
        BankCardInfo bankCardInfo = new BankCardInfo();
        bankCardInfo.setBankNumber(bankNum);
        bankCardInfo.setBusinessType(EBankCardBussinessType.USER.getCode());
        bankCardInfo.setBusinessSysNo(projectWorkerCode);

        return bankCardInfoDAO.select(bankCardInfo);
    }

    @Override
    public List<BankCardInfo> getOwnerBankCardInfo(String workerName,
                                                   String status, String bussinessNo) {
        BankCardInfo bankCardInfo = new BankCardInfo();
        if (StringUtils.isNotBlank(workerName)) {
            bankCardInfo.setBusinessName(workerName);
        }
        if (StringUtils.isNotBlank(status)) {
            bankCardInfo.setStatus(status);
        }

        bankCardInfo.setBusinessSysNo(bussinessNo);

        return bankCardInfoDAO.selectList(bankCardInfo);
    }

    @Override
    public List<BankCardInfo> queryBankCardInfoList(BankCardInfo condition) {
        return bankCardInfoDAO.selectList(condition);
    }

    @Override
    public List<BankCardInfo> getBankCardByByssinessCode(String bussinessType, String bussinessCode) {
        BankCardInfo bankCardInfo = new BankCardInfo();
        bankCardInfo.setBusinessType(bussinessType);
        bankCardInfo.setBusinessSysNo(bussinessCode);
        return bankCardInfoDAO.selectList(bankCardInfo);
    }

    @Override
    public List<BankCardInfo> queryBankCardInfoList(String businessSysNo,
                                                    String status) {
        BankCardInfo bankCardInfo = new BankCardInfo();

        bankCardInfo.setBusinessSysNo(businessSysNo);
        bankCardInfo.setStatus(status);
        return bankCardInfoDAO.selectList(bankCardInfo);
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
