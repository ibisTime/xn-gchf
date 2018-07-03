package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ILeaveDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Leave;

/**
 * 请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午1:59:56 
 * @history:
 */
@Repository("leaveDAOImpl")
public class LeaveDAOImpl extends AMybatisTemplate implements ILeaveDAO {

    @Override
    public int insert(Leave data) {
        return super.insert(NAMESPACE.concat("insert_leave"), data);
    }

    @Override
    public int delete(Leave data) {
        return super.delete(NAMESPACE.concat("delete_leave"), data);
    }

    @Override
    public Leave select(Leave condition) {
        return super.select(NAMESPACE.concat("select_leave"), condition,
            Leave.class);
    }

    @Override
    public long selectTotalCount(Leave condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_leave_count"),
            condition);
    }

    @Override
    public int selectMonthLeaveDays(Leave data) {
        return (int) super.selectTotalCount(
            NAMESPACE.concat("select_monthLeaveDays"), data);
    }

    @Override
    public List<Leave> selectList(Leave condition) {
        return super.selectList(NAMESPACE.concat("select_leave"), condition,
            Leave.class);
    }

    @Override
    public List<Leave> selectList(Leave condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_leave"), start, count,
            condition, Leave.class);
    }
}
