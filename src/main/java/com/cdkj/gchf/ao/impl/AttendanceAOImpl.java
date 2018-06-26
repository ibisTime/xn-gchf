package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IAttendanceAO;
import com.cdkj.gchf.bo.IAttendanceBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IReportBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EEmploystatus;
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

    @Override
    public void startWorkClockIn(String code, Date startDatetime) {
        Attendance data = attendanceBO.getAttendance(code);
        if (null == data) {
            throw new BizException("xn00000", "没有该员工今日考勤");
        }
        String status = EAttendanceStatus.Unpaied.getCode();
        if (null == data.getEndDatetime()) {
            status = EAttendanceStatus.TO_End.getCode();
        }
        attendanceBO.startClockIn(code, status, startDatetime);
    }

    @Override
    public void endWorkClockIn(String code, Date endDatetime) {
        Attendance data = attendanceBO.getAttendance(code);
        if (null == data) {
            throw new BizException("xn00000", "没有该员工今日考勤");
        }
        String status = EAttendanceStatus.Unpaied.getCode();
        if (null == data.getStartDatetime()) {
            status = EAttendanceStatus.TO_Start.getCode();
        }
        attendanceBO.endClockIn(code, status, endDatetime);
    }

    @Override
    public String clockIn(String sim, String projectCode, String staffCode,
            String attendTime, String terminalCode) {
        JSONObject json = new JSONObject();
        logger.info("===========获取考勤记录==============");
        Attendance data = attendanceBO.getAttendanceByProject(projectCode,
            staffCode);
        if (data == null) {
            json.put("result", false);
            return new Gson().toJson(json);
        }

        data.setSim(sim);
        data.setTerminalCode(terminalCode);
        // 上班打卡
        if (EAttendanceStatus.TO_Start.getCode().equals(data.getStatus())) {

            data.setStartDatetime(DateUtil.strToDate(
                attendTime.replace("%", " "), DateUtil.DATA_TIME_PATTERN_1));
            data.setStatus(EAttendanceStatus.TO_End.getCode());
            attendanceBO.toStart(data);

            // 统计上工人数
            Report report = reportBO.getReportByProject(data.getProjectCode());
            if (null == report) {
                json.put("result", false);
                return new Gson().toJson(json);
            }
            int todayDays = report.getTodayDays();
            todayDays = todayDays + 1;
            reportBO.refreshTodayDays(report, todayDays);
        } else { // 下班打卡
            data.setEndDatetime(
                DateUtil.strToDate(attendTime, DateUtil.DATA_TIME_PATTERN_1));
            data.setStatus(EAttendanceStatus.TO_End.getCode());
            attendanceBO.toEnd(data);
        }
        logger.info("===========考勤成功==============");
        json.put("result", true);
        logger.info("json" + new Gson().toJson(json));
        return new Gson().toJson(json);
    }

    // 定时器形成考勤记录
    public void createAttendance() {
        Project condition = new Project();
        Date date = new Date();
        // 获取已经开始的项目
        condition.setStatus(EProjectStatus.Building.getCode());
        List<Project> pList = projectBO.queryProject(condition);
        // 获取各个项目的上下班时间，形成考勤记录
        Attendance data = null;
        logger.info("===========开始生成考勤==============");
        String attendanceCode = null;
        for (Project project : pList) {
            // 获取项目下得所有未离职员工
            Employ eCondition = new Employ();
            eCondition.setProjectCode(project.getCode());
            eCondition.setStatus(EEmploystatus.Work.getCode());

            List<Employ> eList = employBO.queryEmployList(eCondition);

            for (Employ employ : eList) {
                data = new Attendance();
                attendanceCode = OrderNoGenerater
                    .generate(EGeneratePrefix.Attendance.getCode());
                data.setCode(attendanceCode);
                data.setProjectCode(project.getCode());
                data.setProjectName(project.getName());

                data.setStaffCode(employ.getStaffCode());
                data.setStaffName(employ.getStaffName());
                data.setStaffMobile(employ.getStaffMobile());

                data.setStatus(EAttendanceStatus.TO_Start.getCode());
                data.setCreateDatetime(date);

                attendanceBO.saveAttendance(data);
            }

            // 昨天上工人数清零
            Report report = reportBO.getReportByProject(project.getCode());
            report.setTodayDays(0);
            reportBO.resetTodayDays(report);
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
