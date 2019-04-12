package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.bo.IBankCardBankBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631751Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631767Req;
import com.cdkj.gchf.enums.EBankCardBussinessType;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EOperateLogRefType;

@Service(value = "bankCardInfoAOImpl")
public class BankCardInfoAOImpl implements IBankCardInfoAO {
    @Autowired
    private IBankCardBankBO bankCardBankBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addBankCardInfo(XN631750Req req) {
        EBankCardBussinessType.checkExists(req.getBusinessType());

        return bankCardBankBO.saveBankCardInfo(req);
    }

    @Override
    public Paginable<BankCardInfo> queryBankCardInfoPage(int start, int limit,
            BankCardInfo condition) {
        Paginable<BankCardInfo> paginable = bankCardBankBO.getPaginable(start,
            limit, condition);
        List<BankCardInfo> list = paginable.getList();
        for (BankCardInfo bankCardInfo : list) {
            if (bankCardInfo.getBusinessType()
                .equals(EBankCardBussinessType.USER)) {
                WorkerInfo workerInfo = workerInfoBO
                    .getWorkerInfo(bankCardInfo.getBusinessSysNo());
                if (workerInfo == null) {
                    continue;
                }
                bankCardInfo.setBusinessName(workerInfo.getName());
            } else if (bankCardInfo.getBusinessType()
                .equals(EBankCardBussinessType.CORP)) {
                ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                    .getProjectCorpInfo(bankCardInfo.getBusinessSysNo());
                bankCardInfo.setBusinessName(projectCorpInfo.getCorpName());
            }

        }
        paginable.setList(list);
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
    public void editBankCardInfo(XN631752Req req) {

        bankCardBankBO.refreshBankCardInfo(req);
    }

    @Override
    public void queryBankCardInfoDetail(String code) {
        bankCardBankBO.getBankCardInfo(code);
    }

    @Transactional
    @Override
    public void changeBankCardStatus(XN631751Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        BankCardInfo bankCardInfo = bankCardBankBO
            .getBankCardInfo(req.getCode());
        String operate = null;
        if (bankCardInfo.getStatus().equals(EBankCardStatus.Freeze.getCode())) {
            operate = "启用银行卡";
        } else {
            operate = "冻结银行卡";
        }
        operateLogBO.saveOperateLog(EOperateLogRefType.BankCardInfo.getCode(),
            req.getCode(), operate, user, null);
        bankCardBankBO.updateBankCardInfoStatus(req.getCode());
    }

    @Override
    public void dropBankCardInfo(String code) {

    }

}
