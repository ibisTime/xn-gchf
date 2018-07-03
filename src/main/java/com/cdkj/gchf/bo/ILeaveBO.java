package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Leave;

/**
 * 请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午2:00:30 
 * @history:
 */
public interface ILeaveBO extends IPaginableBO<Leave> {
    // 请假申请
    public String saveLeave(String staffCode, String projectCode,
            Date startDatetime, Integer leaveDays, String updater,
            String remark);

    // 获取指定月份请假天数
    public Integer getMonthLeaveDays(String staffCode, String projectCode,
            Integer month);

    public List<Leave> queryLeaveList(Leave condition);

    public Leave getLeave(String code);

}
