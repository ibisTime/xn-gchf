package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631750Req;
import com.cdkj.gchf.dto.req.XN631752Req;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;

/**
 * @author
 */
public interface IBankCardBankBO extends IPaginableBO<BankCardInfo> {

    /**
     * 保存银行卡信息
     * @param req
     * @return
     */
    String saveBankCardInfo(XN631750Req req);


    /**
     * 保存银行卡信息
     * @param data
     * @param projectWorker
     * @return
     */
    String saveBankCardInfo(XN631770ReqDetail data,
            ProjectWorker projectWorker);


    /**
     * 编辑银行卡
     * @param req
     */
    void refreshBankCardInfo(XN631752Req req);

    /**
     * 修改银行卡状态： ①启用->冻结 、②冻结->启用
     * @param code
     * @return
     */
    int updateBankCardInfoStatus(String code);


    /**
     * 通过银行卡账号查询银行卡信息
     * @param payRollBankCardNumber 银行卡号
     * @return BankCardInfo
     */
    BankCardInfo getBankCardInfoByNum(String payRollBankCardNumber);

    /**
     * 查询平台段银行卡信息
     * @param workerName
     * @param status
     * @param bussinessNo
     * @return
     */
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


    public BankCardInfo getBankCardInfo(String bankCode);


}
