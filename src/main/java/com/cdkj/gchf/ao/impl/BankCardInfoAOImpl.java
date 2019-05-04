package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IBankCardInfoAO;
import com.cdkj.gchf.bo.IBankCardBankBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631751Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631765Req;
import com.cdkj.gchf.dto.req.XN631766Req;
import com.cdkj.gchf.dto.req.XN631767Req;
import com.cdkj.gchf.enums.EBankCardBussinessType;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;

@Service(value = "bankCardInfoAOImpl")
public class BankCardInfoAOImpl implements IBankCardInfoAO {
    @Autowired
    private IBankCardBankBO bankCardBankBO;

    @Autowired
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectBO projectBO;

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
            BankCardInfo condition = new BankCardInfo();
            condition.setBankNumber(req.getBankNumber());
            condition.setBusinessType(EBankCardBussinessType.USER.getCode());

            List<BankCardInfo> queryBankCardInfoList = bankCardBankBO
                .queryBankCardInfoList(condition);
            for (BankCardInfo bankCardInfo : queryBankCardInfoList) {
                if (bankCardInfo.getBankNumber()
                    .equals(bankCardInfo.getBankNumber())) {
                    throw new BizException("XN631750", "银行卡信息已存在,请重新填写");
                }
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
    public void changeBankCardStatus(XN631751Req req) {

        bankCardBankBO.updateBankCardInfoStatus(req.getCode());
    }

    @Override
    public void editBankCardInfo(XN631752Req req) {
        BankCardInfo bankCardInfo = bankCardBankBO
            .getBankCardInfo(req.getCode());
        if (bankCardInfo.getStatus().equals(EBankCardStatus.Freeze.getCode())) {
            throw new BizException("XN631752", "银行卡已冻结,无法修改");

        }
        req.getBankNumber();
        BankCardInfo bankCardInfoByNum = bankCardBankBO
            .getBankCardInfoByNum(req.getBankNumber());
        if (bankCardInfoByNum != null && bankCardInfoByNum.getStatus()
            .equals(EBankCardStatus.Normal.getCode())) {
            throw new BizException("XN631752", "银行卡已存在,请检查银行卡信息");
        }
        bankCardBankBO.refreshBankCardInfo(req);
    }

    @Transactional
    @Override
    public Paginable<BankCardInfo> queryBankCardInfoPage(XN631765Req req,
            int start, int limit, BankCardInfo condition) {
        User briefUser = userBO.getBriefUser(req.getUserId());
        // 根据用户类型进行查询
        // 项目端查询
        if (briefUser.getType().equals(EUserKind.Owner.getCode())) {
            // 查询项目端项目人员的银行卡信息
            Project project = projectBO
                .getProject(briefUser.getOrganizationCode());
            List<ProjectWorker> projectWorkerList = projectWorkerBO
                .queryProjectWorkerListByProject(project.getCode());
            List<BankCardInfo> workerBankCard = new ArrayList<>();
            if (req.getBusinessType()
                .equals(EBankCardBussinessType.CORP.getCode())) {
                BankCardInfo bankCardInfo = new BankCardInfo();
                bankCardInfo
                    .setBusinessType(EBankCardBussinessType.CORP.getCode());
                bankCardInfo.setBusinessSysNo(req.getBusinessSysNo());
                Page<BankCardInfo> page = new Page<BankCardInfo>();
                page.setList(bankCardBankBO.queryBankCardInfoList(condition));
                return page;
            }
            for (ProjectWorker projectWorker : projectWorkerList) {
                List<BankCardInfo> bankCardInfo = bankCardBankBO
                    .getOwnerBankCardInfo(condition.getBusinessName(),
                        condition.getStatus(), projectWorker.getCode());
                if (bankCardInfo == null) {
                    continue;
                }

                for (BankCardInfo temp : bankCardInfo) {
                    temp.setProjectName(projectWorker.getProjectName());
                    temp.setTeamName(projectWorker.getTeamName());
                    temp.setWorkerName(projectWorker.getWorkerName());
                    temp.setIdcardNumber(projectWorker.getIdcardNumber());
                    workerBankCard.add(temp);
                }

            }

            Page<BankCardInfo> page = new Page<BankCardInfo>();
            page.setList(workerBankCard);
            return page;
        }

        // 平台端查询
        Paginable<BankCardInfo> paginable = bankCardBankBO.getPaginable(start,
            limit, condition);
        List<BankCardInfo> list = paginable.getList();
        for (BankCardInfo bankCardInfo : list) {
            String businessSysNo = bankCardInfo.getBusinessSysNo();
            // ProjectWorker projectWorker = projectWorkerBO
            // .getProjectWorker(businessSysNo);
            ProjectWorker worker = new ProjectWorker();
            worker.setCode(businessSysNo);
            List<ProjectWorker> queryProjectWorkerList = projectWorkerBO
                .queryProjectWorkerList(worker);
            if (CollectionUtils.isEmpty(queryProjectWorkerList)) {
                ProjectCorpInfo projectCorpInfo = projectCorpInfoBO
                    .getProjectCorpInfo(businessSysNo);
                bankCardInfo.setProjectName(projectCorpInfo.getProjectName());
                bankCardInfo.setBusinessName(projectCorpInfo.getCorpName());
                bankCardInfo.setWorkerName(projectCorpInfo.getCorpName());

            } else {
                ProjectWorker projectWorker = queryProjectWorkerList.get(0);
                bankCardInfo.setProjectName(projectWorker.getProjectName());
                bankCardInfo.setTeamName(projectWorker.getTeamName());
                bankCardInfo.setWorkerName(projectWorker.getWorkerName());
                bankCardInfo.setIdcardNumber(projectWorker.getIdcardNumber());
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
    public Object getBankCardInfo(XN631766Req req) {
        if (StringUtils.isNotBlank(req.getWorkerCode())) {
            BankCardInfo condition = new BankCardInfo();
            condition.setBusinessSysNo(req.getWorkerCode());
            return bankCardBankBO.getPaginable(1, 20, condition);

        }
        BankCardInfo bankCardInfo = bankCardBankBO
            .getBankCardInfo(req.getCode());
        // 人员
        if (bankCardInfo.getBusinessType()
            .equals(EBankCardBussinessType.USER.getCode())) {
            String businessSysNo = bankCardInfo.getBusinessSysNo();
            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(businessSysNo);
            bankCardInfo.setTeamName(projectWorker.getTeamName());
            bankCardInfo.setIdcardNumber(projectWorker.getIdcardNumber());
            bankCardInfo.setProjectName(projectWorker.getProjectName());
        }
        return bankCardInfo;
    }

    @Override
    public void queryBankCardInfoDetail(String code) {
        bankCardBankBO.getBankCardInfo(code);
    }

}
