package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.CompanyCard;




public interface ICompanyCardBO extends IPaginableBO<CompanyCard> {


	public String saveCompanyCard(CompanyCard data);


	public int removeCompanyCard(String code);


	public int refreshCompanyCard(CompanyCard data);


	public List<CompanyCard> queryCompanyCardList(CompanyCard condition);


	public CompanyCard getCompanyCard(String code);


}