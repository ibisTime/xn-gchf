package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Employ;

public interface IEmployBO extends IPaginableBO<Employ> {

    public void joinIn(Employ data);

    // 更新请假状态
    public void toHoliday(String staffCode, String projectCode,
            Date startDatetime, Integer leaveDays);

    // 更新离职状态
    public void leaveOffice(Employ data, String leavingDatetime, String updater,
            String remark);

    public long getSalaryCount(Employ eCondition);

    public void updateStatus(Employ employ);

    // 更新离职状态
    public void updateLeavingStatus(Employ employ);

    public void updateSalaryStatus(Employ employ);

    public Employ getEmployByStaff(String staffCode, String projectCode);

    public List<Employ> queryEmployList(Employ condition);

    // 根据项目查询雇员
    public List<Employ> queryEmployListByProject(String projectCode,
            String status);

    public Employ getEmploy(String code);
}
