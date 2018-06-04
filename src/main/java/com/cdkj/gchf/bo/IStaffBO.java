package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Staff;

public interface IStaffBO extends IPaginableBO<Staff> {

    public void saveStaff(Staff data);

    public void refreshStaff(Staff data);

    public List<Staff> queryStaffList(Staff condition);

    public Staff getStaff(String code);

    public List<Staff> getStaffFeatList();

    public Staff getStaffByIdNo(String idNo);

    public void saveStaffInfo(Staff data);

    public void refreshSalaryStatus(Staff staff);

    public void doDepartmentCode(Staff data, String departmentCode,
            String updater, String remark);
}
