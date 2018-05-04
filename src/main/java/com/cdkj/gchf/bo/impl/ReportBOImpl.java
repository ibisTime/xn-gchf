package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IReportDAO;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.exception.BizException;

@Component
public class ReportBOImpl extends PaginableBOImpl<Report> implements IReportBO {

    @Autowired
    private IReportDAO reportDAO;

    public void saveReport(Report data) {
        String code = null;
    }

    @Override
    public void removeReport(String code) {
    }

    @Override
    public void refreshReport(Report data) {
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

    @Override
    public Report getReportByProject(String proejctCode) {
        Report data = null;
        if (StringUtils.isNotBlank(proejctCode)) {
            Report condition = new Report();
            condition.setProjectCode(proejctCode);
            data = reportDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "统计信息不存在");
            }
        }
        return data;
    }
}
