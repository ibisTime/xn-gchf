package com.cdkj.gchf.dao;

import java.util.List;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Staff;

//dao层 
public interface IStaffDAO extends IBaseDAO<Staff> {
    String NAMESPACE = IStaffDAO.class.getName().concat(".");

    void update(Staff data);

    // 补充特征值
    void updateFeat(Staff data);

    // 补充身份证照片
    void updateIdPict(Staff data);

    // 补全信息
    void updateStaffInfo(Staff data);

    // 查询员工简介信息
    public Staff selectBrief(Staff data);

    // 列表查询员工简介
    public List<Staff> selectListBrief(Staff condition, int start, int count);
}
