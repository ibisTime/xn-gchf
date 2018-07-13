package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Staff;

public interface IStaffBO extends IPaginableBO<Staff> {

    public void saveStaff(Staff data);

    public void refreshStaff(Staff data);

    public void refreshFeat(Staff data, String pict1, String feat,
            String updater);

    public void refreshIdPict(Staff data);

    public void refreshStaffInfo(Staff data);

    public List<Staff> queryStaffList(Staff condition);

    public List<Staff> getStaffFeatList(List<String> staffCodeList);

    public Staff getStaff(String code);

    // 根据身份证查询员工
    public Staff getStaffByIdNo(String idNo);

    // 查询员工简介信息
    public Staff getStaffBrief(String code);
}
