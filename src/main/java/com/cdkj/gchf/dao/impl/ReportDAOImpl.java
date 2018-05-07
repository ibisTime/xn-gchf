package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IReportDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Report;

@Repository("reportDAOImpl")
public class ReportDAOImpl extends AMybatisTemplate implements IReportDAO {

    @Override
    public int insert(Report data) {
        return super.insert(NAMESPACE.concat("insert_report"), data);
    }

    @Override
    public int delete(Report data) {
        return super.delete(NAMESPACE.concat("delete_report"), data);
    }

    @Override
    public Report select(Report condition) {
        return super.select(NAMESPACE.concat("select_report"), condition,
            Report.class);
    }

    @Override
    public long selectTotalCount(Report condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_report_count"),
            condition);
    }

    @Override
    public List<Report> selectList(Report condition) {
        return super.selectList(NAMESPACE.concat("select_report"), condition,
            Report.class);
    }

    @Override
    public List<Report> selectList(Report condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_report"), start, count,
            condition, Report.class);
    }

    @Override
    public void updateStaffIn(Report data) {
        super.update(NAMESPACE.concat("update_staff_in"), data);
    }

    @Override
    public void updateStaffOut(Report data) {
        super.update(NAMESPACE.concat("update_staff_out"), data);
    }

    @Override
    public void updateLeavingDays(Report data) {
        super.update(NAMESPACE.concat("update_leaving_days"), data);
    }

    @Override
    public void updateTodayDays(Report data) {
        super.update(NAMESPACE.concat("update_today_days"), data);
    }

    @Override
    public void updateStaffOn(Report data) {
        super.update(NAMESPACE.concat("update_staff_on"), data);
    }

    @Override
    public void updateLastMonthSalary(Report data) {
        super.update(NAMESPACE.concat("update_last_month_salary"), data);
    }

    @Override
    public void updateNextMonthSalary(Report data) {
        super.update(NAMESPACE.concat("update_next_month_salary"), data);
    }

}
