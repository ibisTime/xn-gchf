package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.SalaryLog;

public interface ISalaryLogBO extends IPaginableBO<SalaryLog> {
    public String saveSalaryLog(String salaryCode, String staffCode,
            String type, String handler, String handleNote, String HandlePic);

    public List<SalaryLog> querySalaryLogList(SalaryLog condition);

    public SalaryLog getSalaryLog(String code);

    public SalaryLog getLastSalaryLog(String salaryCode);

}
