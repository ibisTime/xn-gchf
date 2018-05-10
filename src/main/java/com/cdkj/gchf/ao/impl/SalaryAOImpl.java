package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AmountUtil;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.core.StringValidater;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.CompanyCard;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Salary;
import com.cdkj.gchf.dto.req.XN631442Req;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EMessageStatus;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.enums.EUserKind;
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

    @Autowired
    private IAttendanceBO attendanceBO;

    @Autowired
    private IEmployBO employBO;

    @Override
    public String addSalary(Salary data) {
        salaryBO.saveSalary(data);
        return null;
    }

    @Override
    public void editSalary(XN631442Req req) {
        Salary data = salaryBO.getSalary(req.getCode());
        if (!ESalaryStatus.To_Approve.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该工资条不能修改");
        }
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
    public void approveSalary(List<String> codeList, String approver,
            String approveNote, String result) {
        String status = ESalaryStatus.To_Approve.getCode();
        String mCode = null;
        if (EBoolean.YES.getCode().equals(result)) {
            Date date = new Date();
            status = ESalaryStatus.TO_Send.getCode();
            Salary data = null;
            // 审核通过，生成代发消息
            Message message = new Message();
            mCode = OrderNoGenerater
                .generate(EGeneratePrefix.Message.getCode());
            // 修改工资条状态
            for (String code : codeList) {
                data = salaryBO.getSalary(code);
                salaryBO.addMessageCode(data, mCode, approver, date,
                    approveNote, status);
                if (!ESalaryStatus.To_Approve.getCode()
                    .equals(data.getStatus())) {
                    throw new BizException("xn0000", "工资条不处于待审核状态");
                }
            }

            Project project = projectBO.getProject(data.getProjectCode());

            message.setCode(mCode);
            message.setCompanyCode(project.getCompanyCode());
            message.setCompanyName(project.getCompanyName());
            message.setProjectCode(project.getCode());
            message.setProjectName(project.getName());

            CompanyCard card = companyCardBO
                .getCompanyCardByProject(project.getCode());
            message.setBankCode(card.getBankCode());
            message.setBankName(card.getBankName());
            message.setSubbranch(card.getSubbranch());
            message.setBankcardNumber(card.getBankcardNumber());
            message.setCreateDatetime(new Date());
            message.setDownload(0);
            message.setStatus(EMessageStatus.TO_Send.getCode());
            messageBO.saveMessage(message);
        }

    }

    @Override
    public void dropSalary(String code) {
    }

    @Override
    public Paginable<Salary> querySalaryPage(int start, int limit,
            Salary condition) {
        List<Salary> list = new ArrayList<Salary>();
        Paginable<Salary> page = new Page<Salary>();
        if (EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (StringUtils.isBlank(condition.getCompanyCode())) {
                page.setList(list);
                return page;
            }
        }
        page = salaryBO.getPaginable(start, limit, condition);
        for (Salary salary : page.getList()) {
            BankCard bankCard = bankCardBO
                .getBankCardByStaff(salary.getStaffCode());
            salary.setBankCard(bankCard);
            CompanyCard companyCard = companyCardBO
                .getCompanyCardByProject(salary.getProjectCode());
            salary.setCompanyCard(companyCard);
        }
        return page;
    }

    @Override
    public List<Salary> querySalaryList(Salary condition) {
        List<Salary> list = new ArrayList<Salary>();
        if (EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (StringUtils.isBlank(condition.getCompanyCode())) {
                return list;
            }
        }
        list = salaryBO.querySalaryList(condition);
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
        List<Salary> list = new ArrayList<Salary>();
        Page<Salary> page = new Page<Salary>();
        if (EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (StringUtils.isBlank(condition.getCompanyCode())) {
                page.setList(list);
                return page;
            }
        }
        long totalCount = salaryBO.getTotalSalaryCount(condition);
        page = new Page<Salary>(start, limit, totalCount);
        list = salaryBO.queryTotalSalaryPage(page.getPageNO(),
            page.getPageSize(), condition);
        page.setList(list);
        return page;
    }

    @Override
    public List<Salary> queryTotalSalaryList(Salary condition) {
        List<Salary> list = new ArrayList<Salary>();
        if (EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (StringUtils.isBlank(condition.getCompanyCode())) {
                return list;
            }
        }
        return salaryBO.queryTotalSalaryList(condition);
    }

    @Override
    public void cutAmount(List<Salary> list) {
        for (Salary salary : list) {
            Salary data = salaryBO.getSalary(salary.getCode());
            if (!ESalaryStatus.To_Approve.getCode().equals(data.getStatus())) {
                throw new BizException("xn00000", "该工资条不能修改");
            }
            data.setTax(salary.getTax());
            data.setCutAmount(salary.getCutAmount());
            data.setCutNote(salary.getCutNote());
            Long fact = data.getShouldAmount() - salary.getTax()
                    - salary.getCutAmount();
            data.setFactAmount(fact);
            salaryBO.cutAmount(data);
        }
    }

    // 定时器形成工资条
    public void createSalary() {
        // 获取已经开始的项目
        Date date = new Date();
        Project condition = new Project();
        List<Project> pList = projectBO.queryProject(condition);

        for (Project project : pList) {
            // 获取项目的雇佣关系
            Employ eCondition = new Employ();
            eCondition.setProjectCode(project.getCode());
            List<Employ> eList = employBO.queryEmployList(eCondition);

            // 若当前时间是工资条生成时间,形成工资条
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            if (day == StringValidater
                .toInteger(project.getSalaryCreateDatetime())) {
                // 生成工资条
                for (Employ employ : eList) {
                    Salary data = new Salary();
                    String code = OrderNoGenerater
                        .generate(EGeneratePrefix.Salary.getCode());
                    data.setCode(code);
                    data.setCompanyCode(project.getCompanyCode());
                    data.setCompanyName(project.getCompanyName());
                    data.setProjectCode(project.getCode());
                    data.setProjectName(project.getName());

                    data.setStaffCode(employ.getStaffCode());
                    data.setMonth(calendar.get(Calendar.MONTH));
                    data.setShouldAmount(employ.getSalary());
                    data.setCreateDatetime(date);
                    data.setStatus(ESalaryStatus.To_Approve.getCode());

                    // 统计上个月工人考勤
                    Date startDatetime = DateUtil
                        .getFristDay(DateUtil.getMonth() - 1);
                    Date endDatetime = DateUtil
                        .getLastDay(DateUtil.getMonth() - 1);
                    Attendance aCondition = new Attendance();
                    aCondition.setCreateDatetimeStart(startDatetime);
                    aCondition.setCreateDatetimeEnd(endDatetime);
                    aCondition.setStatus(EAttendanceStatus.Unpaied.getCode());
                    List<Attendance> aList = attendanceBO
                        .queryAttendanceList(aCondition);

                    // 计算上月迟到和早退小时
                    int early = 0;
                    int delay = 0;
                    long cutAmount = 0L;

                    for (Attendance attendance : aList) {
                        // 迟到
                        boolean isNormal = DateUtil.compare(
                            attendance.getSettleDatetime(),
                            project.getAttendanceStarttime());

                        if (StringUtils.isNotBlank(
                            attendance.getStartDatetime()) && isNormal) {
                            // 计算
                            early += 1;
                            cutAmount += employ.getCutAmount() * DateUtil
                                .getHours(attendance.getStartDatetime(),
                                    project.getAttendanceStarttime());
                        }
                        // 早退
                        isNormal = DateUtil.compare(attendance.getEndDatetime(),
                            project.getAttendanceEndtime());
                        if (StringUtils.isNotBlank(attendance.getEndDatetime())
                                && isNormal) {
                            early += 1;
                            cutAmount += employ.getCutAmount() * DateUtil
                                .getHours(attendance.getEndDatetime(),
                                    project.getAttendanceEndtime());
                        }
                        // 修改考勤状态
                        attendance.setStatus(EAttendanceStatus.Paied.getCode());
                        attendanceBO.updateStatus(attendance);
                    }
                    data.setEarlyDays(early);
                    data.setDelayDays(delay);
                    // 请假天数
                    Double leavingDays = 0.0;
                    if (employ.getLeavingDays() != null) {
                        leavingDays = employ.getLeavingDays();
                    }
                    Long leavingCut = AmountUtil.mul(
                        employ.getSalary() / DateUtil.getMonthDays(),
                        employ.getLeavingDays());
                    data.setCutAmount(cutAmount + leavingCut);
                    data.setCutNote("本月迟到：" + early + "天，早退：" + delay + "天，请假："
                            + leavingDays + "天，共计扣款：" + cutAmount + leavingCut);
                    data.setLeavingDays(leavingDays);

                    salaryBO.saveSalary(data);
                    // 清空上月累积请假天数
                    data.setLeavingDays(0.0);
                    employBO.updateLeavingDays(data);
                }

            }
        }

    }

}
