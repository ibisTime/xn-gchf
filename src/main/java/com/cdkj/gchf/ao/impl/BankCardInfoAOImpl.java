package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.bo.IBankCardBankBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631751Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631767Req;
import com.cdkj.gchf.enums.EBankCardBussinessType;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;

@Service(value = "bankCardInfoAOImpl")
public class BankCardInfoAOImpl implements IBankCardInfoAO {
    @Autowired
    private IBankCardBankBO bankCardBankBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Override
    public String addBankCardInfo(XN631750Req req) {

        if (EBankCardBussinessType.CORP.getCode()
            .equals(req.getBusinessType())) {
            List<BankCardInfo> bankCardInfos = bankCardBankBO
                .queryBankCardInfoList(req.getBusinessSysNo(),
                    EBankCardStatus.Normal.getCode());

            if (CollectionUtils.isNotEmpty(bankCardInfos)
                    && bankCardInfos.size() > 0) {
                throw new BizException("631750", "该参建单位已存在银行卡【"
                        + bankCardInfos.get(0).getBankNumber() + "】");
            }

            ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                .getProjectCorpInfo(req.getBusinessSysNo());
            req.setBusinessName(projectCorpInfo.getCorpName());
        }

        if (EBankCardBussinessType.USER.getCode()
            .equals(req.getBusinessType())) {
            WorkerInfo workerInfo = workerInfoBO
                .getWorkerInfo(req.getBusinessSysNo());
            req.setBusinessName(workerInfo.getName());
        }

        return bankCardBankBO.saveBankCardInfo(req);
    }

    @Override
    @Transactional
    public void changeBankCardStatus(XN631751Req req) {
        BankCardInfo bankCardInfo = bankCardBankBO
            .getBankCardInfo(req.getCode());
        if (bankCardInfo.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631751", "银行卡信息已上传,无法更新");
        }

        if (EBankCardBussinessType.CORP.getCode()
            .equals(bankCardInfo.getBusinessType())) {
            bankCardBankBO.refreshStatus(bankCardInfo.getBusinessSysNo(),
                EBankCardStatus.Freeze.getCode());
        }

        bankCardBankBO.updateBankCardInfoStatus(req.getCode());
    }

    @Override
    public void editBankCardInfo(XN631752Req req) {
        BankCardInfo bankCardInfo = bankCardBankBO
            .getBankCardInfo(req.getCode());
        if (bankCardInfo.getUploadStatus()
            .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
            throw new BizException("XN631750", "银行卡信息已上传,无法修改");
        }
        bankCardBankBO.refreshBankCardInfo(req);
    }

    @Override
    public Paginable<BankCardInfo> queryBankCardInfoPage(int start, int limit,
            BankCardInfo condition) {
        Paginable<BankCardInfo> paginable = bankCardBankBO.getPaginable(start,
            limit, condition);
        return paginable;
    }

    @Override
    public List<BankCardInfo> queryBankCardInfoList(XN631767Req req) {

        BankCardInfo condition = new BankCardInfo();
        BeanUtils.copyProperties(req, condition);
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IBankCardInfoAO.DEFAULT_ORDER_COLUMN;
        }
        if (StringUtils.isNotBlank(req.getBusinessType())) {
            EBankCardBussinessType.checkExists(req.getBusinessType());
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return bankCardBankBO.queryBankCardInfoList(condition);
    }

    @Override
    public BankCardInfo getBankCardInfo(String code) {
        return bankCardBankBO.getBankCardInfo(code);
    }

    @Override
    public void queryBankCardInfoDetail(String code) {
        bankCardBankBO.getBankCardInfo(code);
    }

}
