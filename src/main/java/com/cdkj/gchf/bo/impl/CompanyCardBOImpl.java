package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ICompanyCardDAO;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;

@Component
public class CompanyCardBOImpl extends PaginableBOImpl<CompanyCard>
        implements ICompanyCardBO {
    @Autowired
    private ICompanyCardDAO companyCardDAO;

    @Autowired
    private IProjectBO projectBO;

    public void saveCompanyCard(String projectCode, String bankCode,
            String bankName, String accountName, String bankCardNumber,
            String subbranch, String updater, Date updateDatetime,
            String remark) {
        CompanyCard data = new CompanyCard();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CompanyBank.getCode());
        data.setCode(code);
        data.setProjectCode(projectCode);

        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setAccountName(accountName);
        data.setBankcardNumber(bankCardNumber);
        data.setSubbranch(subbranch);

        data.setCreateDatetime(new Date());
        data.setStatus(EBankCardStatus.Normal.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(updateDatetime);
        data.setRemark(remark);

        Project project = projectBO.getProject(projectCode);
        if (null != project) {
            data.setProjectName(project.getName());
        }
        companyCardDAO.insert(data);
    }

    @Override
    public void refreshCompanyCard(String code, String bankCode,
            String bankName, String accountName, String bankCardNumber,
            String subbranch, String updater, Date updateDatetime,
            String remark) {

        CompanyCard data = getCompanyCard(code);
        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setBankcardNumber(bankCardNumber);
        data.setSubbranch(subbranch);

        data.setAccountName(accountName);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);

        companyCardDAO.update(data);
    }

    @Override
    public List<CompanyCard> queryCompanyCardList(CompanyCard condition) {
        return companyCardDAO.selectList(condition);
    }

    @Override
    public CompanyCard getCompanyCard(String code) {
        CompanyCard data = null;
        if (StringUtils.isNotBlank(code)) {
            CompanyCard condition = new CompanyCard();
            condition.setCode(code);
            data = companyCardDAO.select(condition);
            if (data != null) {
                data.setBankSubbranch(
                    data.getBankName().concat(data.getSubbranch()));
            }
        }
        return data;
    }

    @Override
    public CompanyCard getCompanyCardByProject(String projectCode) {
        CompanyCard data = null;
        if (StringUtils.isNotBlank(projectCode)) {
            CompanyCard condition = new CompanyCard();
            condition.setProjectCode(projectCode);
            data = companyCardDAO.select(condition);
            if (data != null) {
                data.setBankSubbranch(
                    data.getBankName().concat(data.getSubbranch()));
            }
        }
        return data;
    }
}
