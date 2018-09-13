package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Staff;

public interface IStaffBO extends IPaginableBO<Staff> {

    // 添加员工
    public void saveStaff(Staff data);

    // 更新免冠照
    public void refreshPict1(String code, String pict1, String feat,
            String updater);

    // 更新身份证正反面、手持身份证照片
    public void refreshPicts(String code, String pict2, String pict3,
            String pict4, String updater);

    // 录入联系方式信息
    public void refreshContactInfo(String code, String mobile, String contact,
            String contactMobile, String updater, String remark);

    // 更新免冠照特征值
    void refreshFeat(String code, String feat);

    // 列表查询员工
    public List<Staff> queryStaffList(Staff condition);

    // 列表查询员工特征值
    public List<Staff> getStaffFeatList(List<String> staffCodeList);

    // 查询员工详情信息
    public Staff getStaff(String code);

    // 根据身份证查询员工
    public Staff getStaffByIdNo(String idNo);

    // 查询员工简介信息
    public Staff getStaffBrief(String code);

    // 列表查询员工简介
    public List<Staff> queryStaffListBrief(Staff condition, int start,
            int count);

    // 列表查询员工免冠照
    public List<Staff> queryStaffPicList(Staff condition, int start, int count);

    // 查询数量
    public long queryTotalCount(Staff condition);
}
