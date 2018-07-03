package com.cdkj.gchf.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Employ;

public interface IEmployBO extends IPaginableBO<Employ> {

    public void joinIn(Employ data);

    public int removeEmploy(String code);

    public void refreshEmploy(Employ data);

    public List<Employ> queryEmployList(Employ condition);

    public Employ getEmploy(String code);

    // 更新请假状态
    public void toHoliday(String staffCode, String projectCode,
            Date startDatetime, Integer leaveDays);

    public void leaveOffice(Employ data, String leavingDatetime, String updater,
            String remark);

    public Employ getEmployStaff(String code);

    public long getSalaryCount(Employ eCondition);

    public void updateStatus(Employ employ);

    public void isExist(String projectCode, String staffCode);

    public Employ getEmployByStaff(String staffCode, String projectCode);

    public void updateSalaryStatus(Employ employ);

}
