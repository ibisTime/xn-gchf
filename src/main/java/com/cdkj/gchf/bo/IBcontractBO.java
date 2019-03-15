package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Bcontract;

public interface IBcontractBO extends IPaginableBO<Bcontract> {

    public String saveBcontract(Bcontract data);

    public int removeBcontract(String code);

    public int refreshBcontract(Bcontract data);

    public List<Bcontract> queryBcontractList(Bcontract condition);

    public Bcontract getBcontract(String code);

}
