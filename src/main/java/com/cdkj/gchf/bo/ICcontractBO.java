package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Ccontract;

public interface ICcontractBO extends IPaginableBO<Ccontract> {

    public void saveCcontract(Ccontract data);

    public void removeCcontract(String code);

    public void refreshCcontract(Ccontract data);

    public List<Ccontract> queryCcontractList(Ccontract condition);

    public Ccontract getCcontract(String code);

}
