package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.ICompanyCardDAO;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.exception.BizException;

@Component
public class CompanyCardBOImpl extends PaginableBOImpl<CompanyCard>
        implements ICompanyCardBO {

    @Autowired
    private ICompanyCardDAO CompanyCardDAO;

    public String saveCompanyCard(CompanyCard data) {
        String code = null;
        if (data != null) {
            data.setCode(code);
            CompanyCardDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeCompanyCard(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            CompanyCard data = new CompanyCard();
            data.setCode(code);
            count = CompanyCardDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCompanyCard(CompanyCard data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
        }
        return count;
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
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
