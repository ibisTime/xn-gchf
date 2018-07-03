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
import com.cdkj.gchf.enums.EEmploystatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EMessageStatus;
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

    // 定时器形成工资条
    @Transactional
    @Override
    public void createSalary() {
        // 获取已经开始的项目
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        Project condition = new Project();
        condition.setStatus(EProjectStatus.Building.getCode());
        List<Project> projectsList = projectBO.queryProject(condition);

        logger.info("===========开始生成工资条==============");
        for (Project project : projectsList) {
            logger.info(
                "===========为项目【" + project.getName() + "】生成工资条==============");
            Employ employCondition = new Employ();
            employCondition.setProjectCode(project.getCode());
            employCondition.setStatus(EEmploystatus.Not_Leave.getCode());
            List<Employ> eList = employBO.queryEmployList(employCondition);

            // 若当前时间是工资条生成时间,形成工资条
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            if (day == StringValidater
                .toInteger(project.getSalaryCreateDatetime())) {
                String messageCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Message.getCode());
                Long totalAmount = 0L;
                Long totalCutAmount = 0L;
                Integer number = 0;

                for (Employ employ : eList) {
                    logger.info("===========为雇员【" + employ.getStaffName()
                            + "】生成工资条==============");
                    // 统计上个月工人考勤
                    Date startDatetime = DateUtil
                        .getFristDay(DateUtil.getMonth() - 2);
                    Date endDatetime = DateUtil
                        .getLastDay(DateUtil.getMonth() - 2);
                    Attendance attendanceCondition = new Attendance();
                    attendanceCondition.setCreateDatetimeStart(startDatetime);
                    attendanceCondition.setCreateDatetimeEnd(endDatetime);
                    attendanceCondition
                        .setStatus(EAttendanceStatus.Unpaied.getCode());
                    List<Attendance> attendanceList = attendanceBO
                        .queryAttendanceList(attendanceCondition);
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
                    data.setLeavingDays(employ.getLeavingDays());
                    data.setAttendanceDays(attendanceList.size());

                    // 计算上月迟到和早退小时
                    int earlyHours = 0;
                    int delayHours = 0;
                    for (Attendance attendance : attendanceList) {
                        // 计算迟到小时数
                        boolean isLess = DateUtil.compare(
                            DateUtil.dateToStr(attendance.getStartDatetime(),
                                DateUtil.DATA_TIME_PATTERN_7),
                            project.getAttendanceStarttime());
                        if (attendance.getStartDatetime() != null && !isLess) {
                            delayHours += DateUtil.getHours(
                                attendance.getStartDatetime(),
                                project.getAttendanceStarttime());
                        }

                        // 计算早退小时数
                        boolean isGreater = DateUtil.compare(
                            DateUtil.dateToStr(attendance.getEndDatetime(),
                                DateUtil.DATA_TIME_PATTERN_7),
                            project.getAttendanceEndtime());
                        if (attendance.getEndDatetime() != null && isGreater) {
                            earlyHours += DateUtil.getHours(
                                attendance.getEndDatetime(),
                                project.getAttendanceEndtime());
                        }
                        // 修改考勤状态
                        attendance.setStatus(EAttendanceStatus.Paied.getCode());
                        attendanceBO.updateStatus(attendance);
                    }
                    data.setEarlyHours(earlyHours);
                    data.setDelayHours(delayHours);

                    // 应发工资（attendanceDays*日薪-（delayHours+earlyHours）*扣款时薪）
                    Long cutAmount = AmountUtil.mul(employ.getCutAmount(),
                        (earlyHours + delayHours));
                    Long shouldAmount = AmountUtil.mul(employ.getSalary(),
                        attendanceList.size()) - cutAmount;
                    data.setShouldAmount(shouldAmount);
                    data.setStatus(ESalaryStatus.To_Approve.getCode());
                    data.setCreateDatetime(date);
                    salaryBO.saveSalary(data);

                    // 待发消息中的字段
                    totalAmount += shouldAmount;
                    totalCutAmount += cutAmount;
                    number = number + 1;

                    // 清空上月累积请假天数
                    data.setLeavingDays(0);
                    employBO.updateLeavingDays(data);
                }

                // 生成代发消息
                Message message = new Message();
                message.setCode(messageCode);
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
        logger.info("===========添加工资条完成==============");
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
}
