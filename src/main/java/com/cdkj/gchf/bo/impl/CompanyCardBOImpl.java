package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ICompanyCardDAO;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.enums.EBankCardStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class CompanyCardBOImpl extends PaginableBOImpl<CompanyCard>
        implements ICompanyCardBO {

    @Autowired
    private ICompanyCardDAO CompanyCardDAO;

    public void saveCompanyCard(String projectCode, String projectName,
            String companyCode, String companyName, String bankCode,
            String bankName, String bankCardNumber, String subbranch,
            String updater, Date updateDatetime, String remark) {
        CompanyCard data = new CompanyCard();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CompanyBank.getCode());
        data.setCode(code);
        data.setProjectCode(projectCode);
        data.setProjectName(projectName);
        data.setCompanyCode(companyCode);
        data.setCompanyName(companyName);

        data.setBankCode(bankCode);
        data.setBankName(bankName);
        data.setBankcardNumber(bankCardNumber);
        data.setSubbranch(subbranch);

        data.setCreateDatetime(new Date());
        data.setStatus(EBankCardStatus.Normal.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(updateDatetime);
        data.setRemark(remark);
        CompanyCardDAO.insert(data);

    }

    @Override
    public void removeCompanyCard(String code) {
    }

    @Override
    public void refreshCompanyCard(CompanyCard data) {
    }

    @Override
    public List<CompanyCard> queryCompanyCardList(CompanyCard condition) {
        return CompanyCardDAO.selectList(condition);
    }

    @Override
    public CompanyCard getCompanyCard(String code) {
        CompanyCard data = null;
        if (StringUtils.isNotBlank(code)) {
            CompanyCard condition = new CompanyCard();
            condition.setCode(code);
            data = CompanyCardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "公司账户不存在");
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
            data = CompanyCardDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "公司账户不存在");
            }
        }
        return data;
    }
}
