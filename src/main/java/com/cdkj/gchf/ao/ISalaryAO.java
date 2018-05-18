package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.dto.req.XN631442Req;

@Component
public interface ISalaryAO {
    static final String DEFAULT_ORDER_COLUMN = "month";

    public String addSalary(Salary data);

    public void dropSalary(String code);

    public void editSalary(XN631442Req req);

    public Paginable<Salary> querySalaryPage(int start, int limit,
            Salary condition);

    public List<Salary> querySalaryList(Salary condition);

    public Salary getSalary(String code);

    // 审核工资条
    public void approveSalary(List<String> list, String approver,
            String approveNote, String result);

    // 累积薪资
    public Paginable<Salary> queryTotalSalaryPage(int start, int limit,
            Salary condition);

    public List<Salary> queryTotalSalaryList(Salary condition);

    // 补充扣款金额
    public void cutAmount(List<Salary> list);

    // 异常工资条
    Paginable<Salary> queryAbnormalSalaryPage(int start, int limit,
            Salary condition);

}
