package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.CompanyCard;

public interface ICompanyCardBO extends IPaginableBO<CompanyCard> {
    public void saveCompanyCard(String companyCode, String projectCode,
            String bankCode, String bankName, String accountName,
            String bankCardNumber, String subbranch, String updater,
            Date updateDatetime, String remark);

    public void refreshCompanyCard(CompanyCard data);

    public List<CompanyCard> queryCompanyCardList(CompanyCard condition);

    public CompanyCard getCompanyCard(String code);

    public CompanyCard getCompanyCardByProject(String code);

}
