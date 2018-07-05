package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IMessageLogAO;
import com.cdkj.gchf.ao.ISalaryAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.ICompanyCardBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.ILeaveBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.ISalaryBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
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
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631442Req;
import com.cdkj.gchf.dto.res.XN631448Res;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.ESalaryStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Service
public class SalaryAOImpl implements ISalaryAO {
    private static final Logger logger = LoggerFactory
        .getLogger(AttendanceAOImpl.class);

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

    @Autowired
    IUserBO userBO;

    @Autowired
    ILeaveBO leaveBO;

    // 定时器形成工资条
    @Override
    @Transactional
    public void createSalary() {
        // 获取已经开始的项目
        Calendar calendar = Calendar.getInstance();
        Project condition = new Project();
        condition.setStatus(EProjectStatus.Building.getCode());
        List<Project> projectsList = projectBO.queryProject(condition);

        logger.info("===========开始生成工资条==============");
        for (Project project : projectsList) {
            // 若当前时间是工资条生成时间,形成工资条
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            if (day == StringValidater
                .toInteger(project.getSalaryCreateDatetime())) {
                createSalary4Project(project, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) - 1);
            }
        }
        logger.info("===========生成工资条完成==============");
    }

    // 手工生成工资条
    @Override
    @Transactional
    public void createSalaryManual(String projectCode, String month) {
        Project project = projectBO.getProject(projectCode);
        if (null != project) {
            String[] salaryDate = month.split("-");
            createSalary4Project(project,
                StringValidater.toInteger(salaryDate[0]),
                StringValidater.toInteger(salaryDate[1]));
        }
    }

    private void createSalary4Project(Project project, int year, int month) {
        logger.info(
            "===========为项目【" + project.getName() + "】生成工资条==============");

        Calendar calendar = Calendar.getInstance();
        List<Employ> employList = employBO.queryEmployListByProject(
            project.getCode(), EEmployStatus.Not_Leave.getCode());
        String messageCode = OrderNoGenerater
            .generate(EGeneratePrefix.Message.getCode());

        // 待发消息薪资总额（所有员工）
        Long totalAmount = 0L;

        // 待发消息扣款总额（所有员工）
        Long totalCutAmount = 0L;

        // 待发消息员工数量
        Integer number = 0;

        for (Employ employ : employList) {
            logger.info("===========为雇员【" + employ.getStaffName()
                    + "】生成工资条==============");

            // 统计指定月份的工人正常考勤天数
            Date startDatetime = DateUtil.getFristDay(year, month);
            Date endDatetime = DateUtil.getLastDay(year, month);
            List<Attendance> attendanceList = attendanceBO
                .queryAttendanceListByStaff(employ.getStaffCode(),
                    employ.getProjectCode(), startDatetime, endDatetime,
                    EAttendanceStatus.Unpaied.getCode());

            if (CollectionUtils.isEmpty(attendanceList)) {
                logger.info("===========雇员【" + employ.getStaffName()
                        + "】不存在考勤记录==============");
                break;
            }

            Salary data = new Salary();
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.Salary.getCode());
            data.setCode(code);
            data.setMessageCode(messageCode);
            data.setProjectCode(project.getCode());
            data.setProjectName(project.getName());

            data.setStaffCode(employ.getStaffCode());
            data.setStaffName(employ.getStaffName());
            data.setMonth(calendar.get(Calendar.YEAR) + "/"
                    + calendar.get(Calendar.MONTH));

            // 统计上个月员工请假天数和正常考勤天数
            data.setLeavingDays(leaveBO.getMonthLeaveDays(employ.getStaffCode(),
                project.getCode(), calendar.get(Calendar.MONTH)));
            data.setAttendanceDays(attendanceList.size());

            // 计算上月迟到和早退小时
            int earlyHours = 0;
            int delayHours = 0;
            for (Attendance attendance : attendanceList) {
                // 迟到小时数
                boolean isLess = DateUtil.compare(
                    DateUtil.dateToStr(attendance.getStartDatetime(),
                        DateUtil.DATA_TIME_PATTERN_7),
                    project.getAttendanceStarttime());
                if (attendance.getStartDatetime() != null && !isLess) {
                    delayHours += DateUtil.getHours(
                        attendance.getStartDatetime(),
                        project.getAttendanceStarttime());
                }

                // 早退小时数
                boolean isGreater = DateUtil.compare(
                    DateUtil.dateToStr(attendance.getEndDatetime(),
                        DateUtil.DATA_TIME_PATTERN_7),
                    project.getAttendanceEndtime());
                if (attendance.getEndDatetime() != null && isGreater) {
                    earlyHours += DateUtil.getHours(attendance.getEndDatetime(),
                        project.getAttendanceEndtime());
                }
                // 将考勤状态更新为【已结算】
                attendanceBO.updateSettleStatus(attendance.getCode(),
                    EAttendanceStatus.Paied.getCode(), new Date());
            }
            data.setEarlyHours(earlyHours);
            data.setDelayHours(delayHours);

            // 计算应发工资（attendanceDays*日薪-（delayHours+earlyHours）*扣款时薪）
            Long cutAmount = AmountUtil.mul(employ.getCutAmount(),
                (earlyHours + delayHours));
            Long shouldAmount = AmountUtil.mul(employ.getSalary(),
                attendanceList.size()) - cutAmount;

            data.setShouldAmount(shouldAmount);
            data.setStatus(ESalaryStatus.To_Approve.getCode());
            data.setCreateDatetime(new Date());
            salaryBO.saveSalary(data);

            // 待发消息中的字段
            totalAmount += shouldAmount;
            totalCutAmount += cutAmount;
            number = number + 1;
        }

        // 生成代发消息
        if (number > 0) {
            String salaryMonth = calendar.get(Calendar.YEAR) + "/"
                    + calendar.get(Calendar.MONTH);
            messageBO.saveMessage(messageCode, project.getCode(), salaryMonth,
                totalAmount, totalCutAmount, number);
        }
    }

    @Override
    @Transactional
    public void dropSalaryList(List<String> salaryCodeList) {
        for (String salaryCode : salaryCodeList) {
            // 将工资所属月份的考勤状态更新为【已打卡待结算】
            Salary salary = salaryBO.getSalary(salaryCode);

            if (!ESalaryStatus.To_Approve.getCode()
                .equals(salary.getStatus())) {
                throw new BizException("xn000",
                    "员工" + salary.getStaffName() + "工资已审核，无法删除！");
            }

            String[] salaryDate = salary.getMonth().split("/");
            Date startDatetime = DateUtil.getFristDay(
                StringValidater.toInteger(salaryDate[0]),
                StringValidater.toInteger(salaryDate[1]) - 1);
            Date endDatetime = DateUtil.getLastDay(
                StringValidater.toInteger(salaryDate[0]),
                StringValidater.toInteger(salaryDate[1]) - 1);

            List<Attendance> attendanceList = attendanceBO
                .queryAttendanceListByStaff(salary.getStaffCode(),
                    salary.getProjectCode(), startDatetime, endDatetime,
                    EAttendanceStatus.Paied.getCode());

            if (CollectionUtils.isNotEmpty(attendanceList)) {
                for (Attendance attendance : attendanceList) {
                    attendanceBO.updateSettleStatus(attendance.getCode(),
                        EAttendanceStatus.Unpaied.getCode(), null);
                }
            }

            // 减去代发消息的统计字段：1、每月累积发薪；2、每月共计扣款；3、每月共计税费
            Long totalAmount = salary.getShouldAmount() - salary.getCutAmount()
                    + salary.getAwardAmount();
            messageBO.refreshMessage4DropSalary(salary.getMessageCode(),
                totalAmount, salary.getCutAmount(), salary.getTax());

            // 删除工资条
            salaryBO.dropSalary(salaryCode);

            // 当所有工资条都删除完后，删除代发消息
            messageBO.dropNotExistSalary(salary.getMessageCode());
        }
    }

    @Override
    @Transactional
    public void editSalary(XN631442Req req) {
        Salary data = salaryBO.getSalary(req.getCode());
        if (!ESalaryStatus.To_Approve.getCode().equals(data.getStatus())) {
            throw new BizException("xn00000", "该工资条不能修改");
        }

        // 更新每月累积发薪、扣款、税费
        Message message = messageBO.getMessage(data.getMessageCode());
        message.setTotalAmount(message.getTotalAmount() - data.getAwardAmount()
                + StringValidater.toLong(req.getAwardAmount()));
        message
            .setTotalCutAmount(message.getTotalCutAmount() - data.getCutAmount()
                    + StringValidater.toLong(req.getCutAmount()));
        message.setTotalTax(message.getTotalTax() - data.getTax()
                + StringValidater.toLong(req.getTax()));
        messageBO.refreshMessage(message);

        Employ employ = employBO.getEmployByStaff(data.getStaffCode(),
            data.getProjectCode());
        data.setAwardAmount(StringValidater.toLong(req.getAwardAmount()));
        data.setTax(StringValidater.toLong(req.getTax()));
        data.setCutAmount(StringValidater.toLong(req.getCutAmount()));
        data.setApplyNote(req.getApplyNote());
        data.setApplyUser(req.getApplyUser());
        data.setApplyDatetime(new Date());

        // 计算实际发薪金额
        Long factAmount = data.getShouldAmount() - data.getCutAmount()
                - data.getTax() + data.getAwardAmount();
        data.setFactAmount(factAmount);
        data.setFactAmountRemark("本月正常考勤天数：" + data.getAttendanceDays()
                + "天；日薪：" + employ.getSalary() / 1000 + "元；迟到："
                + data.getEarlyHours() + "小时，早退：" + data.getDelayHours()
                + "小时，请假：" + data.getLeavingDays() + "天，总计扣款："
                + (data.getEarlyHours() + data.getDelayHours())
                        * employ.getCutAmount() / 1000
                + "元；人工扣款：" + data.getCutAmount() / 1000 + "元；奖励："
                + data.getAwardAmount() / 1000 + "元。");
        salaryBO.refreshSalary(data);
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
        Employ employ = null;

        for (Salary salary : page.getList()) {
            staff = staffBO.getStaff(salary.getStaffCode());
            salary.setStaffName(staff.getName());
            salary.setStaffMobile(staff.getMobile());
            employ = employBO.getEmployByStaff(salary.getStaffCode(),
                salary.getProjectCode());
            salary.setUpUserName(getName(employ.getUpUser()));
            salary.setApplyUserName(getName(salary.getApplyUser()));
            salary.setApproveUserName(getName(salary.getApproveUser()));
        }
        return page;
    }

    @Override
    public List<Salary> querySalaryList(Salary condition) {
        List<Salary> list = salaryBO.querySalaryList(condition);
        BankCard bankCard = null;
        CompanyCard companyCard = null;
        Staff staff = null;
        Employ employ = null;
        for (Salary salary : list) {
            bankCard = bankCardBO.getBankCardByStaff(salary.getStaffCode());
            salary.setBankCard(bankCard);
            companyCard = companyCardBO
                .getCompanyCardByProject(salary.getProjectCode());
            salary.setCompanyCard(companyCard);
            staff = staffBO.getStaff(salary.getStaffCode());

            salary.setStaffName(staff.getName());
            salary.setStaffMobile(staff.getMobile());
            employ = employBO.getEmployByStaff(salary.getStaffCode(),
                salary.getProjectCode());
            salary.setUpUserName(getName(employ.getUpUser()));
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
        data.setStaffMobile(staff.getMobile());
        Employ employ = employBO.getEmployByStaff(data.getStaffCode(),
            data.getProjectCode());
        data.setUpUserName(getName(employ.getUpUser()));
        data.setApplyUserName(getName(data.getApplyUser()));
        data.setApproveUserName(getName(data.getApproveUser()));
        return data;
    }

    @Override
    public List<XN631448Res> getMohtnSalarySumByProject(String projectCode) {
        List<Salary> salaryList = salaryBO
            .selectMonthlySalarySumByProject(projectCode);
        List<XN631448Res> resList = new ArrayList<XN631448Res>();

        if (CollectionUtils.isNotEmpty(salaryList)) {
            for (Salary salary : salaryList) {
                XN631448Res res = new XN631448Res();
                res.setProjectCode(projectCode);
                res.setMonth(salary.getMonth());
                res.setTotalSalary(String.valueOf(salary.getFactAmount()));
                resList.add(res);
            }
        }
        return resList;
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;
    }
}
