package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Ccontract;

public interface ICcontractBO extends IPaginableBO<Ccontract> {
    // 保存合同
    public String saveCcontract(String staffCode, String projectCode,
            String contentPic, Date contractDatetime, String updater,
            String remark);

    // 修改合同
    public void refreshCcontract(String code, String contentPic,
            Date contractDatetime, String updater, String remark);

    public List<Ccontract> queryCcontractList(Ccontract condition);

    public Ccontract getCcontract(String code);

    // 根据员工编号获取合同
    public Ccontract getCcontractByStaff(String staffCode);

    public Ccontract isExist(String projectCode, String staffCode);

}
