package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.dto.req.XN631442Req;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EMessageStatus;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class SalaryAOImpl implements ISalaryAO {

    @Autowired
    private ISalaryBO salaryBO;

    @Autowired
    private IMessageBO messageBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private ICompanyCardBO companyCardBO;

    @Autowired
    private IBankCardBO bankCardBO;

    @Override
    public String addSalary(Salary data) {
        salaryBO.saveSalary(data);
        return null;
    }

    @Override
    public void editSalary(XN631442Req req) {
        Salary data = salaryBO.getSalary(req.getCode());
        data.setEarlyDays(StringValidater.toInteger(req.getEarlyDays()));
        data.setDelayDays(StringValidater.toInteger(req.getDelayDays()));
        data.setLeavingDays(StringValidater.toDouble(req.getLeavingDays()));

        data.setTax(StringValidater.toLong(req.getTax()));
        data.setCutAmount(StringValidater.toLong(req.getCutAmount()));
        data.setCutNote(req.getCutNote());
        Long fact = data.getShouldAmount()
                - StringValidater.toLong(req.getTax())
                - StringValidater.toLong(req.getCutAmount());
        data.setFactAmount(fact);
        salaryBO.refreshSalary(data);
    }

    @Override
    public void approveSalary(String code, String approver, String approveNote,
            String result) {
        Salary data = salaryBO.getSalary(code);
        if (!ESalaryStatus.To_Approve.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "工资条不处于待审核状态");
        }
        String status = ESalaryStatus.To_Approve.getCode();
        String mCode = null;
        if (EBoolean.YES.getCode().equals(result)) {
            status = ESalaryStatus.TO_Send.getCode();
            // 审核通过，生成代发消息
            Message message = new Message();
            mCode = OrderNoGenerater
                .generate(EGeneratePrefix.Message.getCode());
            Project project = projectBO.getProject(data.getProjectCode());
            message.setCode(mCode);
            message.setProjectCode(project.getCode());
            message.setProjectName(project.getName());

            CompanyCard card = companyCardBO
                .getCompanyCardByProject(project.getCode());
            message.setBankCode(card.getBankCode());
            message.setBankName(card.getBankName());
            message.setSubbranch(card.getSubbranch());
            message.setBankcardNumber(card.getBankcardNumber());
            message.setCreateDatetime(new Date());

            message.setStatus(EMessageStatus.Send_NO.getCode());
            messageBO.saveMessage(message);
        }

        salaryBO.approveSalary(data, mCode, approver, approveNote, status);
    }

    @Override
    public void dropSalary(String code) {
    }

    @Override
    public Paginable<Salary> querySalaryPage(int start, int limit,
            Salary condition) {
        Paginable<Salary> page = salaryBO.getPaginable(start, limit, condition);
        List<Salary> list = page.getList();
        for (Salary salary : list) {
            BankCard bankCard = bankCardBO
                .getBankCardByStaff(salary.getStaffCode());
            salary.setBankCard(bankCard);
            CompanyCard companyCard = companyCardBO
                .getCompanyCardByProject(salary.getProjectCode());
            salary.setCompanyCard(companyCard);
        }
        page.setList(list);
        return page;
    }

    @Override
    public List<Salary> querySalaryList(Salary condition) {
        List<Salary> list = salaryBO.querySalaryList(condition);
        for (Salary salary : list) {
            BankCard bankCard = bankCardBO
                .getBankCardByStaff(salary.getStaffCode());
            salary.setBankCard(bankCard);
            CompanyCard companyCard = companyCardBO
                .getCompanyCardByProject(salary.getProjectCode());
            salary.setCompanyCard(companyCard);
        }
        return list;
    }

    @Override
    public Salary getSalary(String code) {
        Salary data = salaryBO.getSalary(code);
        BankCard bankCard = bankCardBO.getBankCardByStaff(data.getStaffCode());
        data.setBankCard(bankCard);
        CompanyCard companyCard = companyCardBO
            .getCompanyCardByProject(data.getProjectCode());
        data.setCompanyCard(companyCard);

        return data;
    }

    @Override
    public Paginable<Salary> queryTotalSalaryPage(int start, int limit,
            Salary condition) {
        long totalCount = salaryBO.getTotalSalaryCount(condition);
        Page<Salary> page = new Page<Salary>(start, limit, totalCount);
        List<Salary> list = salaryBO.queryTotalSalaryPage(page.getPageNO(),
            page.getPageSize(), condition);
        page.setList(list);
        return page;
    }

    @Override
    public List<Salary> queryTotalSalaryList(Salary condition) {
        return salaryBO.queryTotalSalaryList(condition);
    }

}
