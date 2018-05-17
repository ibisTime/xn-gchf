package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ISalaryLogDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.SalaryLog;

@Repository("salaryLogDAOImpl")
public class SalaryLogDAOImpl extends AMybatisTemplate
        implements ISalaryLogDAO {

    @Override
    public int insert(SalaryLog data) {
        return super.insert(NAMESPACE.concat("insert_salaryLog"), data);
    }

    @Override
    public int delete(SalaryLog data) {
        return super.delete(NAMESPACE.concat("delete_salaryLog"), data);
    }

    @Override
    public SalaryLog select(SalaryLog condition) {
        return super.select(NAMESPACE.concat("select_salaryLog"), condition,
            SalaryLog.class);
    }

    @Override
    public long selectTotalCount(SalaryLog condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_salaryLog_count"), condition);
    }

    @Override
    public List<SalaryLog> selectList(SalaryLog condition) {
        return super.selectList(NAMESPACE.concat("select_salaryLog"), condition,
            SalaryLog.class);
    }

    @Override
    public List<SalaryLog> selectList(SalaryLog condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_salaryLog"), start,
            count, condition, SalaryLog.class);
    }

    @Override
    public void update(SalaryLog data) {
        super.update(NAMESPACE.concat("update_salaryLog"), data);
    }

    @Override
    public void dealWithSalary(SalaryLog data) {
        super.update(NAMESPACE.concat("deal_with"), data);
    }

}
