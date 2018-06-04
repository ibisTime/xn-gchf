package com.cdkj.gchf.ao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IMessageLogAO;
import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.IStaffBO;
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
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631442Req;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EMessageStatus;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.exception.BizException;

@Service
public class SalaryAOImpl implements ISalaryAO {

    @Autowired
    ISalaryBO salaryBO;

    @Autowired
    IMessageBO messageBO;

    @Autowired
    IProjectBO projectBO;

    @Autowired
    ICompanyCardBO companyCardBO;

    @Autowired
    IBankCardBO bankCardBO;

    @Autowired
    IAttendanceBO attendanceBO;

    @Autowired
    IEmployBO employBO;

    @Autowired
    IStaffBO staffBO;

    @Autowired
    IMessageLogAO messageLogAO;

    @Override
    public void editSalary(XN631442Req req) {

        Long dif = 0L;
        Salary data = salaryBO.getSalary(req.getCode());
        if (!ESalaryStatus.To_Approve.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该工资条不能修改");
        }
        // 计算修改后扣款、实际发薪的的差值
        dif = data.getCutAmount1()
                - StringValidater.toLong(req.getCutAmount2());
        data.setEarlyDays(StringValidater.toInteger(req.getEarlyDays()));
        data.setDelayDays(StringValidater.toInteger(req.getDelayDays()));
        data.setLeavingDays(StringValidater.toDouble(req.getLeavingDays()));
        data.setTax(StringValidater.toLong(req.getTax()));
        data.setCutAmount2(StringValidater.toLong(req.getCutAmount2()));

        data.setCutAmount1(data.getCutAmount1() + data.getCutAmount2());
        data.setAwardAmount(StringValidater.toLong(req.getAwardAmount()));
        data.setCutNote("本月迟到：" + req.getEarlyDays() + "天，早退："
                + req.getDelayDays() + "天，请假：" + req.getLeavingDays()
                + "天，共计扣款：" + data.getCutAmount1() / 1000 + "元");
        Long fact = data.getShouldAmount() - data.getCutAmount1()
                + data.getAwardAmount();
        data.setFactAmount(fact);
        salaryBO.refreshSalary(data);

        // 更新每月累积发薪、扣款、税费
        Message message = messageBO.getMessage(data.getMessageCode());
        message.setTotalAmount(message.getTotalAmount() + dif);
        message.setTotalCutAmount(message.getTotalCutAmount() + dif);
        message.setTotalTax(message.getTotalTax() + data.getTax());
        messageBO.refreshMessage(message);
    }

    @Override
    @Transactional
    public void approveSalary(List<String> codeList, String approver,
            String approveNote, String result) {
        Salary data = null;
        Date date = new Date();
        String status = ESalaryStatus.TO_Send.getCode();
        if (EBoolean.NO.getCode().equals(result)) {
            status = ESalaryStatus.To_Approve.getCode();
        }
        for (String code : codeList) {
            data = salaryBO.getSalary(code);
            if (!ESalaryStatus.To_Approve.getCode().equals(data.getStatus())) {
                throw new BizException("xn0000", "存在不处于待审核状态的工资条");
            }
            data.setStatus(status);
            data.setApproveUser(approver);
            data.setApproveDatetime(date);
            data.setApproveNote(approveNote);
            salaryBO.approveSalary(data);
        }
    }

    @Override
    public Paginable<Salary> querySalaryPage(int start, int limit,
            Salary condition) {
        Paginable<Salary> page = salaryBO.getPaginable(start, limit, condition);
        Staff staff = null;
        for (Salary salary : page.getList()) {
            staff = staffBO.getStaff(salary.getStaffCode());
            salary.setStaffName(staff.getName());
            salary.setStaffMobile(staff.getMobile());
        }
        return page;
    }

    @Override
    public List<Salary> querySalaryList(Salary condition) {

        List<Salary> list = salaryBO.querySalaryList(condition);
        BankCard bankCard = null;
        CompanyCard companyCard = null;
        Staff staff = null;

        for (Salary salary : list) {
            bankCard = bankCardBO.getBankCardByStaff(salary.getStaffCode());
            salary.setBankCard(bankCard);
            companyCard = companyCardBO
                .getCompanyCardByProject(salary.getProjectCode());
            salary.setCompanyCard(companyCard);
            staff = staffBO.getStaff(salary.getStaffCode());
            salary.setStaffName(staff.getName());
            salary.setStaffMobile(staff.getMobile());
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

        Staff staff = staffBO.getStaff(data.getStaffCode());
        data.setStaffName(staff.getName());
        data.setStaffMobile(staff.getMobile());
        return data;
    }

    // 定时器形成工资条
    @Transactional
    public void createSalary() {
        // 获取已经开始的项目
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        Project condition = new Project();
        List<Project> pList = projectBO.queryProject(condition);
        System.err.println(calendar.get(Calendar.YEAR) + "/"
                + calendar.get(Calendar.MONTH) + "的工资条==============");
        for (Project project : pList) {
            // 获取项目的雇佣关系
            Employ eCondition = new Employ();
            eCondition.setProjectCode(project.getCode());
            List<Employ> eList = employBO.queryEmployList(eCondition);

            // 若当前时间是工资条生成时间,形成工资条
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            if (day == StringValidater
                .toInteger(project.getSalaryCreateDatetime())) {
                // 生成工资条
                String mCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Message.getCode());
                Long totalAmount = 0L;
                Long totalCutAmount = 0L;
                Integer number = 0;

                for (Employ employ : eList) {
                    Salary data = new Salary();
                    String code = OrderNoGenerater
                        .generate(EGeneratePrefix.Salary.getCode());
                    data.setCode(code);
                    data.setMessageCode(mCode);

                    data.setProjectCode(project.getCode());
                    data.setProjectName(project.getName());
                    data.setStaffCode(employ.getStaffCode());
                    data.setMonth(calendar.get(Calendar.YEAR) + "/"
                            + calendar.get(Calendar.MONTH));
                    data.setCreateDatetime(date);
                    data.setStatus(ESalaryStatus.To_Approve.getCode());

                    // 统计上个月工人考勤
                    Date startDatetime = DateUtil
                        .getFristDay(DateUtil.getMonth() - 2);
                    Date endDatetime = DateUtil
                        .getLastDay(DateUtil.getMonth() - 2);
                    Attendance aCondition = new Attendance();
                    aCondition.setCreateDatetimeStart(startDatetime);
                    aCondition.setCreateDatetimeEnd(endDatetime);
                    aCondition.setStatus(EAttendanceStatus.Unpaied.getCode());
                    List<Attendance> aList = attendanceBO
                        .queryAttendanceList(aCondition);

                    if (CollectionUtils.isEmpty(aList)) {
                        break;
                    }
                    // 计算上月迟到和早退小时
                    int early = 0;
                    int delay = 0;

                    for (Attendance attendance : aList) {
                        // 迟到
                        boolean isNormal = DateUtil.compare(
                            attendance.getStartDatetime(),
                            project.getAttendanceStarttime());

                        if (attendance.getStartDatetime() != null && isNormal) {
                            // 计算
                            early += DateUtil.getHours(
                                attendance.getStartDatetime(),
                                project.getAttendanceStarttime());
                        }

                        // 早退
                        isNormal = DateUtil.compare(attendance.getEndDatetime(),
                            project.getAttendanceEndtime());
                        if (attendance.getEndDatetime() != null && !isNormal) {
                            early += DateUtil.getHours(
                                attendance.getEndDatetime(),
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

                    Long cutAmount = AmountUtil.mul(employ.getCutAmount(),
                        (early + delay));

                    Long leavingCut = AmountUtil.mul(
                        employ.getSalary() / DateUtil.getMonthDays(),
                        employ.getLeavingDays());

                    data.setCutAmount1(cutAmount + leavingCut);
                    data.setCutNote("本月迟到：" + early + "天，早退：" + delay + "天，请假："
                            + leavingDays + "天，共计扣款："
                            + data.getCutAmount1() / 1000 + "元");
                    data.setLeavingDays(leavingDays);

                    // 获取上工天数，计算工资
                    data.setShouldAmount(
                        AmountUtil.mul(employ.getSalary(), aList.size()));
                    data.setFactAmount(
                        data.getShouldAmount() - data.getCutAmount1());
                    data.setSupplyAmount(0L);
                    salaryBO.saveSalary(data);

                    totalAmount = totalAmount + employ.getSalary();
                    totalCutAmount = totalCutAmount + leavingCut;
                    number = number + 1;

                    // 清空上月累积请假天数
                    data.setLeavingDays(0.0);
                    employBO.updateLeavingDays(data);
                }

                // 生成代发消息
                Message message = new Message();
                message.setCode(mCode);
                message.setProjectCode(project.getCode());
                message.setProjectName(project.getName());
                message.setMonth(calendar.get(Calendar.YEAR) + "/"
                        + calendar.get(Calendar.MONTH));
                CompanyCard card = companyCardBO
                    .getCompanyCardByProject(project.getCode());

                message.setBankCode(card.getBankCode());
                message.setBankName(card.getBankName());
                message.setSubbranch(card.getSubbranch());
                message.setBankcardNumber(card.getBankcardNumber());
                message.setCreateDatetime(new Date());

                message.setTotalAmount(totalAmount - totalCutAmount);
                message.setTotalCutAmount(totalCutAmount);
                message.setNumber(number);
                message.setDownload(0);
                message.setStatus(EMessageStatus.TO_Send.getCode());

                messageBO.saveMessage(message);
            }
        }

    }

}
