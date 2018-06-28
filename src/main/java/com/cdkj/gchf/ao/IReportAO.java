package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Report;

@Component
public interface IReportAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Report> queryReportPage(int start, int limit,
            Report condition);

    public List<Report> queryReportList(Report condition);

    public Report getReport(String code);

}
