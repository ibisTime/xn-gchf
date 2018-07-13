package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IStaffDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Staff;

@Repository("staffDAOImpl")
public class StaffDAOImpl extends AMybatisTemplate implements IStaffDAO {

    @Override
    public int insert(Staff data) {
        return super.insert(NAMESPACE.concat("insert_staff"), data);
    }

    @Override
    public int delete(Staff data) {
        return super.delete(NAMESPACE.concat("delete_staff"), data);
    }

    @Override
    public Staff select(Staff condition) {
        return super.select(NAMESPACE.concat("select_staff"), condition,
            Staff.class);
    }

    @Override
    public Staff selectBrief(Staff data) {
        return super.select(NAMESPACE.concat("select_staffBrief"), data,
            Staff.class);
    }

    @Override
    public long selectTotalCount(Staff condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_staff_count"),
            condition);
    }

    @Override
    public List<Staff> selectList(Staff condition) {
        return super.selectList(NAMESPACE.concat("select_staff"), condition,
            Staff.class);
    }

    @Override
    public List<Staff> selectList(Staff condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_staff"), start, count,
            condition, Staff.class);
    }

    @Override
    public void update(Staff data) {
        super.update(NAMESPACE.concat("update_staff"), data);
    }

    @Override
    public void updateFeat(Staff data) {
        super.update(NAMESPACE.concat("update_feat"), data);
    }

    @Override
    public void updateIdPict(Staff data) {
        super.update(NAMESPACE.concat("update_idPict"), data);
    }

    @Override
    public void updateStaffInfo(Staff data) {
        super.update(NAMESPACE.concat("update_StaffInfo"), data);

    }
}
