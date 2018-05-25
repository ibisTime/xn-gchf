package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.SalaryLog;

@Component
public interface ISalaryLogAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<SalaryLog> querySalaryLogPage(int start, int limit,
            SalaryLog condition);

    public List<SalaryLog> querySalaryLogList(SalaryLog condition);

    public SalaryLog getSalaryLog(String code);

    public String addsalaryLog(String code, String handler, String handleNote);

    public String changeToNormal(String salaryCode, String handler,
            String handleNote);

}
