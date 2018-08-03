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
import com.cdkj.gchf.bo.IDepartmentBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.ILeaveBO;
import com.cdkj.gchf.bo.IMessageBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectCardBO;
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
import com.cdkj.gchf.domain.Department;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Message;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectCard;
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
    IProjectCardBO projectCardBO;

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

    @Autowired
    IDepartmentBO departmentBO;

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
                    calendar.get(Calendar.MONTH));
            }
        }
        logger.info("===========生成工资条完成==============");
    }

    // 手工生成工资条
    @Override
    @Transactional
    public int createSalaryManual(String projectCode, String month) {
        Project project = projectBO.getProject(projectCode);
        Integer salaryNumber = 0;// 工资条数量

        if (!EProjectStatus.Building.getCode().equals(project.getStatus())) {
            throw new BizException("xn0000", "项目状态不是在建，无法生成工资条！");
        }
        if (null != project) {
            String[] salaryDate = month.split("-");
            salaryNumber = createSalary4Project(project,
                StringValidater.toInteger(salaryDate[0]),
                StringValidater.toInteger(salaryDate[1]));
        }

        return salaryNumber;
    }

    private int createSalary4Project(Project project, int year, int month) {
        logger.info(
            "===========为项目【" + project.getName() + "】生成工资条==============");

        List<Employ> employList = employBO.queryEmployListByProject(
            project.getCode(), EEmployStatus.Not_Leave.getCode());
        String messageCode = OrderNoGenerater
            .generate(EGeneratePrefix.Message.getCode());

        // 待发消息薪资总额（所有员工）
        Long totalAmount = 0L;

        // 待发消息扣款总额（所有员工）
        Long totalCutAmount = 0L;

        // 待发消息员工数量，生成的工资条数量
        Integer number = 0;

        for (Employ employ : employList) {
            logger.info("===========为雇员【" + employ.getStaffName()
                    + "】生成工资条==============");

            // 统计指定月份的工人正常考勤天数
            Date startDatetime = DateUtil.getFristDay(year, month - 1);
            Date endDatetime = DateUtil.getLastDay(year, month - 1);
            List<String> normalStatusList = new ArrayList<String>();
            normalStatusList.add(EAttendanceStatus.Unpaied.getCode());
            List<Attendance> normalAttendanceList = attendanceBO
                .queryEmployAttendanceList(employ.getCode(), startDatetime,
                    endDatetime, normalStatusList);// 正常考勤数据

            List<String> absentStatusList = new ArrayList<String>();
            absentStatusList.add(EAttendanceStatus.TO_Start.getCode());
            absentStatusList.add(EAttendanceStatus.TO_End.getCode());
            List<Attendance> absentAttendanceList = attendanceBO
                .queryEmployAttendanceList(employ.getCode(), startDatetime,
                    endDatetime, absentStatusList);// 异常考勤数据

            if (CollectionUtils.isEmpty(normalAttendanceList)
                    && CollectionUtils.isEmpty(absentAttendanceList)) {
                logger.info("===========雇员【" + employ.getStaffName()
                        + "】不存在考勤记录==============");
                continue;
            }

            Salary data = new Salary();
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.Salary.getCode());
            data.setCode(code);
            data.setEmployCode(employ.getCode());
            data.setMessageCode(messageCode);
            data.setProjectCode(project.getCode());
            data.setProjectName(project.getName());

            data.setStaffCode(employ.getStaffCode());
            data.setStaffName(employ.getStaffName());
            data.setMonth(year + "/" + month);

            // 统计指定年份和月份员工请假天数和正常考勤天数
            data.setLeavingDays(leaveBO.getMonthLeaveDays(employ.getStaffCode(),
                project.getCode(), startDatetime, endDatetime));
            data.setAttendanceDays(normalAttendanceList.size());

            // 计算上月迟到和早退小时
            int earlyHours = 0;
            int delayHours = 0;
            for (Attendance attendance : normalAttendanceList) {
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

            // 将旷工考勤状态更新为【旷工】
            for (Attendance attendance : absentAttendanceList) {
                if (null == attendance.getStartDatetime()
                        && null == attendance.getEndDatetime()) {
                    attendanceBO.updateStatus(attendance.getCode(),
                        EAttendanceStatus.Absent.getCode());
                }
            }

            data.setEarlyHours(earlyHours);
            data.setDelayHours(delayHours);

            // 计算应发工资（attendanceDays*日薪-（delayHours+earlyHours）*扣款时薪）
            Long cutAmount = AmountUtil.mul(employ.getCutAmount(),
                (earlyHours + delayHours));
            Long shouldAmount = AmountUtil.mul(employ.getSalary(),
                normalAttendanceList.size()) - cutAmount;
            if (shouldAmount < 0)
                shouldAmount = 0L;// 应发工资不应该出现负数

            data.setShouldAmount(shouldAmount);
            data.setFactAmount(shouldAmount);
            data.setStatus(ESalaryStatus.To_Approve.getCode());
            data.setCreateDatetime(new Date());

            int delayEarlyHours = data.getDelayHours() + data.getEarlyHours();
            data.setFactAmountRemark("日薪：" + employ.getSalary() / 1000
                    + "元；迟到早退扣款：" + delayEarlyHours + "小时*"
                    + employ.getCutAmount() / 1000 + "元="
                    + delayEarlyHours * employ.getCutAmount() / 1000 + "元；");
            salaryBO.saveSalary(data);

            // 待发消息中的字段
            totalAmount += shouldAmount;
            totalCutAmount += cutAmount;
            number = number + 1;
        }

        // 生成代发消息
        if (number > 0) {
            String salaryMonth = year + "/" + month;
            messageBO.saveMessage(messageCode, project.getCode(), salaryMonth,
                totalAmount, totalCutAmount, number);
        }

        return number;
    }

    @Override
    @Transactional
    public void dropSalaryList(List<String> salaryCodeList) {
        for (String salaryCode : salaryCodeList) {
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
            List<String> statusList = new ArrayList<String>();
            statusList.add(EAttendanceStatus.Paied.getCode());
            statusList.add(EAttendanceStatus.Absent.getCode());

            List<Attendance> attendanceList = attendanceBO
                .queryEmployAttendanceList(salary.getEmployCode(),
                    startDatetime, endDatetime, statusList);

            // 将工资所属月份的考勤状态更新为考勤数据的状态
            if (CollectionUtils.isNotEmpty(attendanceList)) {
                for (Attendance attendance : attendanceList) {
                    String status = EAttendanceStatus.Unpaied.getCode();
                    if (null == attendance.getEndDatetime()) {
                        status = EAttendanceStatus.TO_End.getCode();
                    }
                    if (null == attendance.getStartDatetime()) {
                        status = EAttendanceStatus.TO_Start.getCode();
                    }
                    attendanceBO.updateStatus(attendance.getCode(), status);
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

        Long awardAmount = StringValidater.toLong(req.getAwardAmount());// 奖励金额
        Long cutAmount = StringValidater.toLong(req.getCutAmount());// 扣减金额
        Long taxAmount = StringValidater.toLong(req.getTax());// 税费

        // 判断扣款金额是否过大导致负数
        if ((cutAmount + taxAmount) > (data.getShouldAmount() + awardAmount)) {
            throw new BizException("xn0000", "扣款金额过大，请重新输入！");
        }

        Message message = messageBO.getMessage(data.getMessageCode());

        // 减去之前的奖励，加上现在的奖励；加上之前的扣款，减去现在的扣款；加上之前的税费，减去现在的税费
        message.setTotalAmount(message.getTotalAmount() - data.getAwardAmount()
                + awardAmount + data.getCutAmount() - cutAmount + data.getTax()
                - taxAmount);

        // 减去之前的扣款，加上现在的扣款
        message.setTotalCutAmount(
            message.getTotalCutAmount() - data.getCutAmount() + cutAmount);

        // 减去之前的税费，加上现在的税费
        message.setTotalTax(message.getTotalTax() - data.getTax() + taxAmount);
        messageBO.refreshMessage(message);

        data.setAwardAmount(awardAmount);
        data.setTax(taxAmount);
        data.setCutAmount(cutAmount);
        data.setApplyNote(req.getApplyNote());
        data.setApplyUser(req.getApplyUser());
        data.setApplyDatetime(new Date());

        // 计算实际发薪金额
        Long factAmount = data.getShouldAmount() - data.getCutAmount()
                - data.getTax() + data.getAwardAmount();
        data.setFactAmount(factAmount);

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

        for (Salary salary : page.getList()) {
            initSalary(salary);
        }
        return page;
    }

    @Override
    public List<Salary> querySalaryList(Salary condition) {
        List<Salary> list = salaryBO.querySalaryList(condition);
        for (Salary salary : list) {
            initSalary(salary);
        }
        return list;
    }

    @Override
    public Salary getSalary(String code) {
        Salary data = salaryBO.getSalary(code);
        initSalary(data);
        return data;
    }

    private void initSalary(Salary salary) {
        Staff staff = null;
        Employ employ = null;
        BankCard bankCard = null;
        ProjectCard projectCard = null;
        Project project = null;
        Message message = null;
        Department department = null;

        staff = staffBO.getStaffBrief(salary.getStaffCode());
        if (null != staff) {
            salary.setStaffName(staff.getName());
            salary.setStaffMobile(staff.getMobile());
            salary.setStaffIdNo(staff.getIdNo());
        }

        // 隶属上级名称
        employ = employBO.getEmployByStaff(salary.getStaffCode(),
            salary.getProjectCode());
        if (null != employ) {
            department = departmentBO.getDepartment(employ.getDepartmentCode());
            if (null != department) {
                salary.setDepartmentLeaderName(department.getLeader());
                salary.setDepartmentLeaderMobile(department.getLeadeMobile());
            }
        }

        // 修改人
        salary.setApplyUserName(getName(salary.getApplyUser()));

        // 审核人
        salary.setApproveUserName(getName(salary.getApproveUser()));

        // 公司账户
        projectCard = projectCardBO
            .getProjectCardByProject(salary.getProjectCode());
        salary.setProjectCard(projectCard);

        // 银行卡
        bankCard = bankCardBO.getEmployBankCard(salary.getEmployCode());
        salary.setBankCard(bankCard);

        // 项目信息
        project = projectBO.getProject(salary.getProjectCode());
        if (null != project) {
            salary.setProjectChargeUser(project.getChargeUser());
            salary.setProjectChargeUserMobile(project.getChargeMobile());
        }

        // 工资发放人员
        message = messageBO.getMessage(salary.getMessageCode());
        if (null != message) {
            User sendUser = userBO.getUser(message.getSender());
            if (null != sendUser) {
                salary.setSendUserName(sendUser.getLoginName());
                salary.setSendUserMobile(sendUser.getMobile());
            }
        }

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
