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
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.User;
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

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IUserBO userBO;

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
            BankCardInfo bankCardInfoByNum = bankCardBankBO
                .getBankCardInfoByNum(req.getBankNumber());
            if (bankCardInfoByNum != null) {
                throw new BizException("XN631750", "请输入正确的银行卡号");
            }

            ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                .getProjectCorpInfo(req.getBusinessSysNo());
            req.setBusinessName(projectCorpInfo.getCorpName());
        }

        if (EBankCardBussinessType.USER.getCode()
            .equals(req.getBusinessType())) {
            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(req.getBusinessSysNo());
            req.setBusinessName(projectWorker.getWorkerName());
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
    public Paginable<BankCardInfo> queryBankCardInfoPage(String userId,
            int start, int limit, BankCardInfo condition) {
        User briefUser = userBO.getBriefUser(userId);
        // if (briefUser.getType().equals(EUserKind.Owner.getCode())) {
        // // 项目端查询自己项目下的银行卡信息
        // String organizationCode = briefUser.getOrganizationCode();
        // ProjectWorker projectWorker = new ProjectWorker();
        // projectWorker.setProjectCode(organizationCode);
        // List<ProjectWorker> queryProjectWorkerList = projectWorkerBO
        // .queryProjectWorkerList(projectWorker);
        // List<String> idcardNumbers = new ArrayList<>();
        // for (ProjectWorker tempProjectWorker : queryProjectWorkerList) {
        // idcardNumbers.add(tempProjectWorker.getIdcardNumber());
        // }
        // return bankCardBankBO
        // .queryBankCardInfoListByIdcardNumber(idcardNumbers);
        //
        // }
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
        BankCardInfo bankCardInfo = bankCardBankBO.getBankCardInfo(code);

        if (bankCardInfo.getBusinessType()
            .equals(EBankCardBussinessType.USER.getCode())) {
            String businessSysNo = bankCardInfo.getBusinessSysNo();
            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(businessSysNo);
            bankCardInfo.setTeamName(projectWorker.getTeamName());
            bankCardInfo.setIdCardNumber(projectWorker.getIdcardNumber());
            bankCardInfo.setProjectName(projectWorker.getProjectName());
        }
        return bankCardBankBO.getBankCardInfo(code);
    }

    @Override
    public void queryBankCardInfoDetail(String code) {
        bankCardBankBO.getBankCardInfo(code);
    }

}
