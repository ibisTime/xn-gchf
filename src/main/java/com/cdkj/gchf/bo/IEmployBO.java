package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Employ;

public interface IEmployBO extends IPaginableBO<Employ> {
    // 入职
    public void joinIn(Employ data);

    // 离职
    public void leaveOffice(Employ data, String leavingDatetime, String updater,
            String remark);

    // 修改入职信息
    public void editEmploy(Employ data);

    // 请假
    public void toHoliday(String code, Date startDatetime, Integer leaveDays);

    // 更新状态
    public void updateStatus(Employ employ);

    // 更新薪资状态
    public void updateSalaryStatus(Employ employ);

    // 更新联系方式
    public void updateStaffMobile(String staffCode, String mobile);

    // 根据项目查询雇员
    public List<Employ> queryEmployListByProject(String projectCode,
            String status);

    // 获取员工未离职雇佣关系
    public Employ getEmployByStaff(String staffCode, String projectCode);

    // 获取项目主管
    public List<Employ> queryEmployManagerList(String projectCode);

    // 获取工资条数量
    public long getSalaryCount(Employ eCondition);

    // 获取工资条数量
    public long getTotalCount(Employ eCondition);

    public List<Employ> queryEmployList(Employ condition);

    public Employ getEmploy(String code);

}
