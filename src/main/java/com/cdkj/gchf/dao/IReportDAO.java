package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Report;

//dao层 
public interface IReportDAO extends IBaseDAO<Report> {
    String NAMESPACE = IReportDAO.class.getName().concat(".");

    // 统计入职人数
    void updateStaffIn(Report data);

    // 统计离职人数
    void updateStaffOut(Report data);

    // 统计请假人数
    void updateLeavingDays(Report data);

    // 统计今日上工人数
    void updateTodayDays(Report data);

    // 更新目前在职人数
    void updateStaffOn(Report report);

    // 上月实际发薪金额
    void updateLastMonthSalary(Report data);

    // 下月累积发薪
    void updateNextMonthSalary(Report data);
}
