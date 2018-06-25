package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IReportAO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.enums.EEmploystatus;
import com.cdkj.gchf.enums.EProjectStatus;

@Service
public class ReportAOImpl implements IReportAO {

    @Autowired
    private IReportBO reportBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IEmployBO employBO;

    @Override
    public String addReport(Report data) {
        return null;
    }

    @Override
    public void editReport(Report data) {
    }

    @Override
    public void dropReport(String code) {
    }

    @Override
    public Paginable<Report> queryReportPage(int start, int limit,
            Report condition) {
        return reportBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Report> queryReportList(Report condition) {
        return reportBO.queryReportList(condition);
    }

    @Override
    public Report getReport(String code) {
        return reportBO.getReport(code);
    }

    // 统计信息
    public void nextMonthSalary() {
        // 获取已经开始的项目
        Project condition = new Project();
        condition.setStatus(EProjectStatus.Building.getCode());
        List<Project> pList = projectBO.queryProject(condition);
        Report report = null;
        for (Project project : pList) {
            // 获取项目下得所有未离职员工
            Employ eCondition = new Employ();
            eCondition.setProjectCode(project.getCode());
            eCondition.setStatus(EEmploystatus.Not_Leave.getCode());
            report = reportBO.getReportByProject(project.getCode());
            // 下月预计发放金额
            long nextMonthSalary = employBO.getSalaryCount(eCondition);
            report.setNextMonthSalary(nextMonthSalary);
            reportBO.refreshNextMonthSalary(report);
        }
    }
}
