package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ICompanyCardDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.CompanyCard;

@Repository("companyCardDAOImpl")
public class CompanyCardDAOImpl extends AMybatisTemplate
        implements ICompanyCardDAO {

    @Override
    public int insert(CompanyCard data) {
        return super.insert(NAMESPACE.concat("insert_companyCard"), data);
    }

    @Override
    public int delete(CompanyCard data) {
        return super.delete(NAMESPACE.concat("delete_companyCard"), data);
    }

    @Override
    public CompanyCard select(CompanyCard condition) {
        return super.select(NAMESPACE.concat("select_companyCard"), condition,
            CompanyCard.class);
    }

    @Override
    public long selectTotalCount(CompanyCard condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_companyCard_count"), condition);
    }

    @Override
    public List<CompanyCard> selectList(CompanyCard condition) {
        return super.selectList(NAMESPACE.concat("select_companyCard"),
            condition, CompanyCard.class);
    }

    @Override
    public List<CompanyCard> selectList(CompanyCard condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_companyCard"), start,
            count, condition, CompanyCard.class);
    }

}
