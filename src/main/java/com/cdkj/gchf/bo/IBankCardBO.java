package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631420Req;

public interface IBankCardBO extends IPaginableBO<BankCard> {
    // 补录工资卡
    public String addBankCard(XN631420Req req);

    // 入职时添加工资卡
    public String addBankCard(Staff staff, String companyCode,
            String projectCode, String bankCode, String bankName,
            String subbranch, String bankcardNumber, String updater);

    // 更新银行卡信息
    public void refreshBankCard(String code, String bankCode, String bankName,
            String subbranch, String bankcardNumber, String updater,
            String remark);

    // 判断员工工资卡是否存在
    public BankCard isBankCardExist(String staffCode, String projectCode);

    public List<BankCard> queryBankCardList(BankCard condition);

    public BankCard getBankCard(String code);

    public BankCard getBankCardByStaff(String code);

    // 查询员工在工程下的工资卡
    public BankCard getBankCard(String staffCode, String projectCode);

}
