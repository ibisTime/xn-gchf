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
     * 删除银行卡
     */
    void deleteBankCardInfo(String code);

    /**
     * @param projectWorker 项目人员
     * @param bankCode 银行代码
     * @param bankNumber 银行卡号
     * @param subranch 支行
     * @param bankLinkNumber 银行联号
     * @param remark 备注
     * @return 新增银行卡code
     */
    String saveWorkerBankCardInfo(ProjectWorker projectWorker, String bankCode, String bankNumber,
            String subranch, String bankLinkNumber, String remark);


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
     * @Description: 根据项目人员code 银行卡号查询人员银行卡信息
     */
    BankCardInfo getBankCardByIdCardNumBankNum(String projectWorkerCode,
            String bankNum);


    /**
     * 按照业务类型查询银行卡类别
     *
     * @param bussinessType 业务类型 ，参考 EBankCardBussinessType ：001 参建单位 / 002 个人用户
     * @param bussinessCode 参建单位：参建单位主键code ,个人用户：项目人员主键code
     * @return 银行卡
     */
    List<BankCardInfo> getBankCardByByssinessCode(String bussinessType, String bussinessCode);

    public List<BankCardInfo> queryBankCardInfoList(String businessSysNo,
            String status);

    public List<BankCardInfo> queryBankCardInfoList(BankCardInfo condition);


    public BankCardInfo getBankCardInfo(String bankCode);


}
