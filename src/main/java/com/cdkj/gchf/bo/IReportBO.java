package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Report;

public interface IReportBO extends IPaginableBO<Report> {
    public void saveReport(String projectCode, String name);

    public void refreshEmploy(String projectCode, Long nextMonthSalary);

    public void refreshStaffOut(Report data);

    public void refreshLeavingDays(Report data);

    public void refreshTodayDays(Report data, int todayDays);

    public void refreshStaffOn(Report report);

    public void refreshLastMonthSalary(Report data);

    public void refreshNextMonthSalary(Report data);

    public void resetTodayDays(String projectCode);

    public List<Report> queryReportList(Report condition);

    public Report getReport(String code);

    public Report getReportByProject(String code);
}
