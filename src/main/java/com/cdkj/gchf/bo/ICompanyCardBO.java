package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.CompanyCard;

public interface ICompanyCardBO extends IPaginableBO<CompanyCard> {

    public void removeCompanyCard(String code);

    public void refreshCompanyCard(CompanyCard data);

    public List<CompanyCard> queryCompanyCardList(CompanyCard condition);

    public CompanyCard getCompanyCard(String code);

    public void saveCompanyCard(String projectCode, String projectName,
            String companyCode, String companyName, String bankCode,
            String bankName, String bankCardNumber, String subbranch,
            String updater, Date updateDatetime, String remark);

    public CompanyCard getCompanyCardByProject(String code);

}
