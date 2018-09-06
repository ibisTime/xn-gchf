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
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Attendance;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Report;
import com.cdkj.gchf.enums.EAttendanceStatus;
import com.cdkj.gchf.enums.EEmployStatus;
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
    @Transactional
    public void startWorkManualClockIn(List<String> codeList,
            Date attendanceStartDatetime, Date attendanceEndDatetime) {
        for (String code : codeList) {
            Attendance data = attendanceBO.getAttendance(code);

            if (attendanceStartDatetime.after(attendanceEndDatetime)) {
                throw new BizException("xn00000", "开始时间无法晚于结束时间，请重新输入！");
            }
            if (null != data.getEndDatetime()
                    && attendanceEndDatetime.after(data.getEndDatetime())) {
                throw new BizException("xn00000", "上班时间无法晚于下班时间，请重新输入！");
            }
            if (EAttendanceStatus.Paied.getCode().equals(data.getStatus())
                    || EAttendanceStatus.Absent.getCode()
                        .equals(data.getStatus())) {
                throw new BizException("xn00000", "考勤已结算，无法打卡！");
            }

            String status = EAttendanceStatus.TO_End.getCode();
            if (null != data.getEndDatetime()) {
                status = EAttendanceStatus.Unpaied.getCode();
            }

            Date startDatetime = new Date(
                random(attendanceStartDatetime.getTime(),
                    attendanceEndDatetime.getTime()));

            attendanceBO.startWorkManualClockIn(code, status, startDatetime);
        }
    }

    @Override
    @Transactional
    public void endWorkManualClockIn(List<String> codeList,
            Date attendanceStartDatetime, Date attendanceEndDatetime) {
        for (String code : codeList) {
            Attendance data = attendanceBO.getAttendance(code);

            if (attendanceEndDatetime.before(attendanceStartDatetime)) {
                throw new BizException("xn00000", "结束时间无法早于开始时间，请重新输入！");
            }
            if (null != data.getStartDatetime()
                    && attendanceEndDatetime.before(data.getStartDatetime())) {
                throw new BizException("xn00000", "下班时间无法早于上班时间，请重新输入！");
            }
            if (EAttendanceStatus.Paied.getCode().equals(data.getStatus())
                    || EAttendanceStatus.Absent.getCode()
                        .equals(data.getStatus())) {
                throw new BizException("xn00000", "考勤已结算，无法打卡！");
            }

            String status = EAttendanceStatus.TO_Start.getCode();
            if (null != data.getStartDatetime()) {
                status = EAttendanceStatus.Unpaied.getCode();
            }

            Date endDatetime = new Date(
                random(attendanceStartDatetime.getTime(),
                    attendanceEndDatetime.getTime()));

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

        // 取考勤最早时间作为上班时间，考勤最晚时间作为下班时间
        Date attendDatetime = DateUtil.strToDate(attendTime,
            DateUtil.DATA_TIME_PATTERN_1);
        Date startDatetime = null;// 上班打卡时间
        Date endDatetime = null;// 下班打卡时间

        // 第一条考勤记录不用判断
        if (null == data.getStartDatetime()) {
            startDatetime = attendDatetime;
        }
        if (null != data.getStartDatetime() && null == data.getEndDatetime()) {
            endDatetime = attendDatetime;
        }
        if (null != data.getStartDatetime()
                && attendDatetime.before(data.getStartDatetime())) {
            startDatetime = attendDatetime;
        }
        if (null != data.getEndDatetime()
                && attendDatetime.after(data.getEndDatetime())) {
            endDatetime = attendDatetime;
        }

        data.setSim(sim);
        data.setTerminalCode(terminalCode);

        // 将最早的打卡记为上班打卡
        if (null != startDatetime) {
            logger.info("----------------------员工" + staffCode
                    + "上班打卡----------------------");

            // 更新考勤状态
            String status = EAttendanceStatus.TO_End.getCode();
            if (null != data.getEndDatetime()) {
                status = EAttendanceStatus.Unpaied.getCode();
            }

            data.setStartDatetime(DateUtil.strToDate(
                attendTime.replace("%", " "), DateUtil.DATA_TIME_PATTERN_1));
            data.setStatus(status);
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
        }

        // 将最晚的打卡记为下班打卡
        if (null != endDatetime) {
            logger.info("----------------------员工" + staffCode
                    + "下班打卡----------------------");

            // 更新考勤状态
            String status = EAttendanceStatus.TO_Start.getCode();
            if (null != data.getStartDatetime()) {
                status = EAttendanceStatus.Unpaied.getCode();
            }

            data.setEndDatetime(
                DateUtil.strToDate(attendTime, DateUtil.DATA_TIME_PATTERN_1));
            data.setStatus(status);
            attendanceBO.endWorkMachineClockIn(data);
        }

        json.put("result", true);
        return new Gson().toJson(json);
    }

    // 定时器每天凌晨形成考勤记录
    @Override
    @Transactional
    public void createAttendanceDaily() {
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
                attendanceBO.saveAttendance(employ.getCode(), project.getCode(),
                    project.getName(), employ.getStaffCode(),
                    employ.getStaffName());
            }

            // 昨天上工人数清零
            reportBO.resetTodayDays(project.getCode());
        }
        logger.info("===========生成考勤结束==============");
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
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
