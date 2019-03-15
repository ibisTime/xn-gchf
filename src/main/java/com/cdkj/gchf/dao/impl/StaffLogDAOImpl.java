package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IStaffLogDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.StaffLog;

//CHECK 。。。 
@Repository("staffLogDAOImpl")
public class StaffLogDAOImpl extends AMybatisTemplate implements IStaffLogDAO {

    @Override
    public int insert(StaffLog data) {
        return super.insert(NAMESPACE.concat("insert_staffLog"), data);
    }

    @Override
    public int delete(StaffLog data) {
        return super.delete(NAMESPACE.concat("delete_staffLog"), data);
    }

    @Override
    public StaffLog select(StaffLog condition) {
        return super.select(NAMESPACE.concat("select_staffLog"), condition,
            StaffLog.class);
    }

    @Override
    public long selectTotalCount(StaffLog condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_staffLog_count"),
            condition);
    }

    @Override
    public List<StaffLog> selectList(StaffLog condition) {
        return super.selectList(NAMESPACE.concat("select_staffLog"), condition,
            StaffLog.class);
    }

    @Override
    public List<StaffLog> selectList(StaffLog condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_staffLog"), start,
            count, condition, StaffLog.class);
    }

}
