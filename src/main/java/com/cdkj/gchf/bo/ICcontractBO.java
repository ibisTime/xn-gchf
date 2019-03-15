package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Ccontract;

public interface ICcontractBO extends IPaginableBO<Ccontract> {
    // 保存合同
    public String saveCcontract(String employCode, String staffCode,
            String staffName, String staffMobile, String projectCode,
            String projectName, String contentPic, String updater,
            String remark);

    // 修改合同
    public void refreshCcontract(String code, String contentPic, String updater,
            String remark);

    // 删除合同
    public void dropCcontract(String code);

    public List<Ccontract> queryCcontractList(Ccontract condition);

    public Ccontract getCcontract(String code);

    // 根据雇佣编号获取合同
    public Ccontract getEmployCcontract(String employCode);

    public Ccontract isExist(String projectCode, String staffCode);

}
