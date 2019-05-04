package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;

public interface IBankCardBankBO extends IPaginableBO<BankCardInfo> {

    public String saveBankCardInfo(XN631750Req req);

    String saveBankCardInfo(BankCardInfo bankCardInfo);

    String saveBankCardInfo(XN631770ReqDetail data,
            ProjectWorker projectWorker);

    public void removeBankCardInfo(String code);

    public void refreshBankCardInfo(XN631752Req req);

    public int updateBankCardInfoStatus(String code);

    public void refreshStatus(String businessSysNo, String status);

    BankCardInfo getBankCardInfoByNum(String payRollBankCardNumber);

    List<BankCardInfo> getOwnerBankCardInfo(String workerName, String status,
            String bussinessNo);

    /**
     * @Description: 通过身份证号和银行卡号查询银行卡信息
     */
    BankCardInfo getBankCardByIdCardNumBankNum(String idCardNumber,
            String bankNum);

    public List<BankCardInfo> queryBankCardInfoList(String businessSysNo,
            String status);

    public List<BankCardInfo> queryBankCardInfoList(BankCardInfo condition);

    public BankCardInfo getBankCardInfo(Long id);

    public BankCardInfo getBankCardInfo(String bankCode);

    List<BankCardInfo> queryBankCardInfoListByIdcardNumber(
            List<String> idCardNumbers);

}
