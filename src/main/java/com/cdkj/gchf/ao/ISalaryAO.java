package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.dto.req.XN631442Req;
import com.cdkj.gchf.dto.res.XN631448Res;

@Component
public interface ISalaryAO {
    static final String DEFAULT_ORDER_COLUMN = "month";

    public void editSalary(XN631442Req req);

    // 审核工资条
    public void approveSalary(List<String> list, String approver,
            String approveNote, String result);

    // 定时生成工资条
    public void createSalary();

    // 手动生成工资条
    public int createSalaryManual(String projectCode, String month);

    // 批量删除工资条
    public void dropSalaryList(List<String> salaryCodeList);

    public Paginable<Salary> querySalaryPage(int start, int limit,
            Salary condition);

    public List<Salary> querySalaryList(Salary condition);

    public Salary getSalary(String code);

    public List<XN631448Res> getMohtnSalarySumByProject(String projectCode);
}
