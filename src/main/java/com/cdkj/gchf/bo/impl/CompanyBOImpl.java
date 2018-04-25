package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ICompanyDAO;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company>
        implements ICompanyBO {

    @Autowired
    private ICompanyDAO companyDAO;

    @Override
    public long getTotalCount(Company condition) {
        return companyDAO.selectTotalCount(condition);
    }

    @Override
    public Paginable<Company> getPaginable(int start, Company condition) {
        return null;
    }

    @Override
    public Paginable<Company> getPaginable(int start, int pageSize,
            Company condition) {
        return null;
    }

    @Override
    public String saveCompany(Company data) {
        String code = null;
        if (data != null) {
            if (data.getCode() == null) {
                code = OrderNoGenerater
                    .generate(EGeneratePrefix.Brand.getCode());
                data.setCode(code);
            }
            companyDAO.insert(data);
        }
        return code;
    }

    @Override
    public Company getCompany(String code) {
        Company data = null;
        if (StringUtils.isNotBlank(code)) {
            Company condition = new Company();
            condition.setCode(code);
            data = companyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该编号不存在");
            }
        }
        return data;
    }

    @Override
    public int editCompany(Company data) {
        return companyDAO.update(data);
    }

    @Override
    public List<Company> queryCompany(Company condition) {
        return companyDAO.selectList(condition);
    }

    @Override
    public void deleteCompany(Company data) {
        companyDAO.delete(data);
    }

    // @Override
    // public List<Company> queryCompanyPage(int start, int limit,
    // Company condition) {
    // return companyDAO.selectList(start, limit, condition);
    // }
    //
    // @Override
    // public List<Company> queryCompanylist(Company condition) {
    // return companyDAO.selectList(condition);
    // }

}
