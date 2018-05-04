package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IReportAO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Report;

@Service
public class ReportAOImpl implements IReportAO {

    @Autowired
    private IReportBO reportBO;

    @Override
    public String addReport(Report data) {
        reportBO.saveReport(data);
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
}
