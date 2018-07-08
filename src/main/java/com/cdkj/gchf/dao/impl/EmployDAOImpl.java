package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IEmployDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Employ;

@Repository("employDAOImpl")
public class EmployDAOImpl extends AMybatisTemplate implements IEmployDAO {

    @Override
    public int insert(Employ data) {
        return super.insert(NAMESPACE.concat("insert_employ"), data);
    }

    @Override
    public int delete(Employ data) {
        return super.delete(NAMESPACE.concat("delete_employ"), data);
    }

    @Override
    public Employ select(Employ condition) {
        return super.select(NAMESPACE.concat("select_employ"), condition,
            Employ.class);
    }

    @Override
    public long selectTotalCount(Employ condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_employ_count"),
            condition);
    }

    @Override
    public List<Employ> selectList(Employ condition) {
        return super.selectList(NAMESPACE.concat("select_employ"), condition,
            Employ.class);
    }

    @Override
    public List<Employ> selectList(Employ condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_employ"), start, count,
            condition, Employ.class);
    }

    @Override
    public int update(Employ data) {
        return super.update(NAMESPACE.concat("update_employ"), data);
    }

    @Override
    public void toHoliday(Employ data) {
        super.update(NAMESPACE.concat("to_holiday"), data);
    }

    @Override
    public void leaveOffice(Employ data) {
        super.update(NAMESPACE.concat("leave_office"), data);
    }

    @Override
    public long getSalaryCount(Employ condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_salary_count"),
            condition);
    }

    @Override
    public void updateStatus(Employ data) {
        super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public void updateSalaryStatus(Employ data) {
        super.update(NAMESPACE.concat("update_salaryStatus"), data);
    }

    @Override
    public void updateLeavingStatus(Employ employ) {
        super.update(NAMESPACE.concat("update_leavingStatus"), employ);

    }

}
