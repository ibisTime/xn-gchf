package com.cdkj.gchf.dao;

import java.util.List;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Staff;

public interface IStaffDAO extends IBaseDAO<Staff> {
    String NAMESPACE = IStaffDAO.class.getName().concat(".");

    // 查询员工简介信息
    public Staff selectBrief(Staff data);

    // 查询员工免冠照
    public List<Staff> selectStaffPic(Staff condition, int start, int count);

    // 查询员工免冠照
    public Staff selectStaffPict(Staff condition);

    // 列表查询员工简介
    public List<Staff> selectListBrief(Staff condition, int start, int count);

    // 更新免冠照
    void updatePict1(Staff data);

    // 更新身份证正反面、手持身份证照片
    void updatePicts(Staff data);

    // 更新联系方式信息
    void updateContactInfo(Staff data);

    // 更新免冠照特征值
    void updateFeat(Staff data);

}
