package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ISalaryDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Salary;

@Repository("salaryDAOImpl")
public class SalaryDAOImpl extends AMybatisTemplate implements ISalaryDAO {

    @Override
    public int insert(Salary data) {
        return super.insert(NAMESPACE.concat("insert_salary"), data);
    }

    @Override
    public int delete(Salary data) {
        return super.delete(NAMESPACE.concat("delete_salary"), data);
    }

    @Override
    public Salary select(Salary condition) {
        return super.select(NAMESPACE.concat("select_salary"), condition,
            Salary.class);
    }

    @Override
    public long selectTotalCount(Salary condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_salary_count"),
            condition);
    }

    @Override
    public List<Salary> selectList(Salary condition) {
        return super.selectList(NAMESPACE.concat("select_salary"), condition,
            Salary.class);
    }

    @Override
    public List<Salary> selectList(Salary condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_salary"), start, count,
            condition, Salary.class);
    }

    @Override
    public void update(Salary data) {
        super.update(NAMESPACE.concat("update_salary"), data);
    }

    @Override
    public void addMessageCode(Salary data) {
        super.update(NAMESPACE.concat("approve_salary"), data);

    }

    @Override
    public void payAmount(Salary data) {
        super.update(NAMESPACE.concat("pay_amount"), data);
    }

    @Override
    public long getTotalSalaryCount(Salary condition) {
        return super.selectTotalCount(NAMESPACE.concat("total_salary_count"),
            condition);
    }

    @Override
    public List<Salary> queryTotalSalaryList(Salary condition) {
        return super.selectList(NAMESPACE.concat("select_total_salary"),
            condition, Salary.class);
    }

    @Override
    public List<Salary> queryTotalSalaryPage(int pageNO, int pageSize,
            Salary condition) {
        return super.selectList(NAMESPACE.concat("select_total_salary"), pageNO,
            pageSize, condition, Salary.class);
    }

    @Override
    public void updateStatus(Salary data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

}
