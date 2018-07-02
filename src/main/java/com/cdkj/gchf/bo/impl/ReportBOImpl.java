package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IReportDAO;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class ReportBOImpl extends PaginableBOImpl<Report> implements IReportBO {
    @Autowired
    private IReportDAO reportDAO;

    public void saveReport(String projectCode, String name) {
        Report data = new Report();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Report.getCode());
        data.setCode(code);
        data.setProjectCode(projectCode);
        data.setProjectName(name);
        data.setTodayDays(0);
        data.setLastMonthSalary(0L);

        data.setNextMonthSalary(0L);
        data.setTotalSalary(0L);
        data.setStaffOn(0);
        data.setStaffIn(0);
        data.setStaffOut(0);

        data.setLeavingDays(0);
        data.setWorkingDays(0);
        reportDAO.insert(data);
    }

    @Override
    public Report getReportByProject(String proejctCode) {
        Report data = null;
        if (StringUtils.isNotBlank(proejctCode)) {
            Report condition = new Report();
            condition.setProjectCode(proejctCode);
            data = reportDAO.select(condition);

        }
        return data;
    }

    @Override
    public void refreshStaffIn(Report data) {
        reportDAO.updateStaffIn(data);
    }

    @Override
    public void refreshStaffOut(Report data) {
        reportDAO.updateStaffOut(data);
    }

    @Override
    public void refreshLeavingDays(Report data) {
        reportDAO.updateLeavingDays(data);
    }

    @Override
    public void refreshTodayDays(Report data, int todayDays) {
        data.setTodayDays(todayDays);
        data.setWorkingDays(data.getWorkingDays() + 1);
        reportDAO.updateTodayDays(data);
    }

    @Override
    public void refreshStaffOn(Report data) {
        reportDAO.updateStaffOn(data);

    }

    @Override
    public void refreshLastMonthSalary(Report data) {
        reportDAO.updateLastMonthSalary(data);
    }

    @Override
    public void refreshNextMonthSalary(Report data) {
        reportDAO.updateNextMonthSalary(data);
    }

    @Override
    public void resetTodayDays(String projectCode) {
        Report report = getReportByProject(projectCode);
        Report data = new Report();
        data.setCode(report.getCode());
        data.setTodayDays(0);
        reportDAO.updateResetTodayDays(data);
    }

    @Override
    public List<Report> queryReportList(Report condition) {
        return reportDAO.selectList(condition);
    }

    @Override
    public Report getReport(String code) {
        Report data = null;
        if (StringUtils.isNotBlank(code)) {
            Report condition = new Report();
            condition.setCode(code);
            data = reportDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "统计信息不存在");
            }
        }
        return data;
    }
}
