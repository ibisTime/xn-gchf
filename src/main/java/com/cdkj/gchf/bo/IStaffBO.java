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

    // 列表查询员工简介
    public List<Staff> queryStaffListBrief(Staff condition, int start,
            int count);

    // 查询数量
    public long queryTotalCount(Staff condition);

    public List<Staff> getStaffFeatList(List<String> staffCodeList);

    public Staff getStaff(String code);

    // 根据身份证查询员工
    public Staff getStaffByIdNo(String idNo);

    // 根据关键字1查询员工
    public Staff getStaffByKeyword1(String keyword1);

    // 查询员工简介信息
    public Staff getStaffBrief(String code);
}
