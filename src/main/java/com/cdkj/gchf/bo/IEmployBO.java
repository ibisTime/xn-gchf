package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Employ;

public interface IEmployBO extends IPaginableBO<Employ> {
    // 入职
    public void joinIn(Employ data);

    // 请假
    public void toHoliday(String staffCode, String projectCode,
            Date startDatetime, Integer leaveDays);

    // 离职
    public void leaveOffice(Employ data, String leavingDatetime, String updater,
            String remark);

    // 修改入职信息
    public void editEmploy(Employ data);

    public long getSalaryCount(Employ eCondition);

    public void updateStatus(Employ employ);

    // 更新离职状态
    public void updateLeavingStatus(Employ employ);

    // 更新薪资状态
    public void updateSalaryStatus(Employ employ);

    public Employ getEmployByStaff(String staffCode, String projectCode);

    public List<Employ> queryEmployList(Employ condition);

    // 根据项目查询雇员
    public List<Employ> queryEmployListByProject(String projectCode,
            String status);

    public Employ getEmploy(String code);
}
