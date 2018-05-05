package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Report;

public interface IReportBO extends IPaginableBO<Report> {

    public void saveReport(Report data);

    public void removeReport(String code);

    public void refreshReport(String cpde, Report data, long lastMonthSalary,
            int todayWorkers);

    public List<Report> queryReportList(Report condition);

    public Report getReport(String code);

    public Report getReportByProject(String code);

}
