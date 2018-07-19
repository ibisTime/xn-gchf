package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IAttendanceAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EEmployStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;
import com.google.gson.Gson;

@Service
public class AttendanceAOImpl implements IAttendanceAO {
    private static final Logger logger = LoggerFactory
        .getLogger(AttendanceAOImpl.class);

    @Autowired
    private IAttendanceBO attendanceBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IEmployBO employBO;

    @Autowired
    private IReportBO reportBO;

    @Autowired
    private IStaffBO staffBO;

    @Override
    @Transactional
    public void startWorkManualClockIn(List<String> codeList,
            Date startDatetime) {
        for (String code : codeList) {
            Attendance data = attendanceBO.getAttendance(code);
            if (null == data) {
                throw new BizException("xn00000", "没有该员工今日考勤");
            }
            if (EAttendanceStatus.Paied.getCode().equals(data.getStatus())) {
                throw new BizException("xn00000", "考勤已结算，无法打卡！");
            }

            String status = EAttendanceStatus.TO_End.getCode();
            if (null != data.getEndDatetime()) {
                status = EAttendanceStatus.Unpaied.getCode();
            }

            attendanceBO.startWorkManualClockIn(code, status, startDatetime);
        }
    }

    @Override
    @Transactional
    public void endWorkManualClockIn(List<String> codeList, Date endDatetime) {
        for (String code : codeList) {
            Attendance data = attendanceBO.getAttendance(code);
            if (null == data) {
                throw new BizException("xn00000", "没有该员工今日考勤");
            }
            if (EAttendanceStatus.Paied.getCode().equals(data.getStatus())) {
                throw new BizException("xn00000", "考勤已结算，无法打卡！");
            }

            String status = EAttendanceStatus.TO_Start.getCode();
            if (null != data.getStartDatetime()) {
                status = EAttendanceStatus.Unpaied.getCode();
            }

            attendanceBO.endWorkManualClockIn(code, status, endDatetime);
        }
    }

    @Override
    @Transactional
    public String manchineClockIn(String sim, String projectCode,
            String staffCode, String attendTime, String terminalCode) {
        JSONObject json = new JSONObject();
        Attendance data = attendanceBO.getAttendanceByProject(projectCode,
            staffCode,
            DateUtil.strToDate(attendTime, DateUtil.FRONT_DATE_FORMAT_STRING));
        if (data == null) {
            logger.info("----------------------员工" + staffCode
                    + "考勤记录不存在----------------------");
            json.put("result", false);
            return new Gson().toJson(json);
        }

        data.setSim(sim);
        data.setTerminalCode(terminalCode);
        // 将最早的打卡记为上班打卡
        if (EAttendanceStatus.TO_Start.getCode().equals(data.getStatus())) {
            logger.info("----------------------员工" + staffCode
                    + "上班打卡----------------------");
            data.setStartDatetime(DateUtil.strToDate(
                attendTime.replace("%", " "), DateUtil.DATA_TIME_PATTERN_1));
            data.setStatus(EAttendanceStatus.TO_End.getCode());
            attendanceBO.startWorkMachineClockIn(data);

            // 统计上工人数
            Report report = reportBO.getReportByProject(data.getProjectCode());
            if (null == report) {
                json.put("result", false);
                return new Gson().toJson(json);
            }
            int todayDays = report.getTodayDays();
            todayDays = todayDays + 1;
            reportBO.refreshTodayDays(report, todayDays);
        } else {
            // 将最晚的打卡记为下班打卡
            logger.info("----------------------员工" + staffCode
                    + "下班打卡----------------------");
            Date endDatetime = DateUtil.strToDate(attendTime,
                DateUtil.DATA_TIME_PATTERN_1);
            data.setEndDatetime(endDatetime);
            data.setStatus(EAttendanceStatus.Unpaied.getCode());
            attendanceBO.endWorkMachineClockIn(data);
        }
        json.put("result", true);
        return new Gson().toJson(json);
    }

    // 定时器每天凌晨形成考勤记录
    @Override
    @Transactional
    public void createAttendanceDaily() {
        Date now = new Date();
        // 获取在建的项目
        Project condition = new Project();
        condition.setStatus(EProjectStatus.Building.getCode());
        List<Project> pList = projectBO.queryProject(condition);
        logger.info("===========开始生成考勤==============");

        for (Project project : pList) {
            // 获取项目下得所有未离职员工
            Employ eCondition = new Employ();
            eCondition.setProjectCode(project.getCode());
            eCondition.setStatus(EEmployStatus.Work.getCode());
            List<Employ> eList = employBO.queryEmployList(eCondition);

            for (Employ employ : eList) {
                Staff staff = staffBO.getStaff(employ.getStaffCode());
                Attendance data = new Attendance();
                String attendanceCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Attendance.getCode());
                data.setCode(attendanceCode);
                data.setProjectCode(project.getCode());
                data.setProjectName(project.getName());
                data.setStaffCode(employ.getStaffCode());
                data.setStaffName(employ.getStaffName());

                data.setStaffMobile(staff.getMobile());
                data.setStatus(EAttendanceStatus.TO_Start.getCode());
                data.setCreateDatetime(now);
                attendanceBO.saveAttendance(data);
            }

            // 昨天上工人数清零
            reportBO.resetTodayDays(project.getCode());
        }
        logger.info("===========生成考勤结束==============");
    }

    @Override
    public Paginable<Attendance> queryAttendancePage(int start, int limit,
            Attendance condition) {
        Paginable<Attendance> page = new Page<Attendance>();
        List<Attendance> list = new ArrayList<Attendance>();
        if (EUserKind.Owner.getCode().equals(condition.getKeyword())
                || EUserKind.Supervise.getCode()
                    .equals(condition.getKeyword())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }
        }

        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeStart()
                    .after(condition.getCreateDatetimeEnd())) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }

        return attendanceBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Attendance> queryAttendanceList(Attendance condition) {
        List<Attendance> list = new ArrayList<Attendance>();
        if (EUserKind.Owner.getCode().equals(condition.getKeyword())
                || EUserKind.Supervise.getCode()
                    .equals(condition.getKeyword())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }

        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeStart()
                    .after(condition.getCreateDatetimeEnd())) {
            throw new BizException("xn0000", "开始时间不能大于结束时间");
        }
        return attendanceBO.queryAttendanceList(condition);
    }

    @Override
    public Attendance getAttendance(String code) {
        return attendanceBO.getAttendance(code);
    }
}
