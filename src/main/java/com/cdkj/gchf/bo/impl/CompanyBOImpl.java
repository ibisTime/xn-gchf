package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.ICompanyDAO;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.exception.BizException;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company>
        implements ICompanyBO {

    @Autowired
    private ICompanyDAO companyDAO;

    @Override
    public void saveCompany(Company data) {
        companyDAO.insert(data);
    }

    @Override
    public void removeCompany(Company data) {
        companyDAO.delete(data);
    }

    @Override
    public void refreshCompany(Company data, String name) {
        data.setName(name);
        companyDAO.update(data);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyDAO.selectList(condition);
    }

    @Override
    public Company getCompany(String code) {
        Company data = null;
        if (StringUtils.isNotBlank(code)) {
            Company condition = new Company();
            condition.setCode(code);
            data = companyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "公司不存在");
            }
        }
        return data;
    }
}
