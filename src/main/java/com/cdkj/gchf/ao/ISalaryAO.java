package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.dto.req.XN631442Req;

@Component
public interface ISalaryAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addSalary(Salary data);

    public void dropSalary(String code);

    public void editSalary(XN631442Req req);

    public Paginable<Salary> querySalaryPage(int start, int limit,
            Salary condition);

    public List<Salary> querySalaryList(Salary condition);

    public Salary getSalary(String code);

    public void approveSalary(String code, String approver, String approveNote,
            String result);

}
