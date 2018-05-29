package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IEventRemindDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.EventRemind;

@Repository("eventRemindDAOImpl")
public class EventRemindDAOImpl extends AMybatisTemplate
        implements IEventRemindDAO {

    @Override
    public int insert(EventRemind data) {
        return super.insert(NAMESPACE.concat("insert_eventRemind"), data);
    }

    @Override
    public int delete(EventRemind data) {
        return super.delete(NAMESPACE.concat("delete_eventRemind"), data);
    }

    @Override
    public EventRemind select(EventRemind condition) {
        return super.select(NAMESPACE.concat("select_eventRemind"), condition,
            EventRemind.class);
    }

    @Override
    public long selectTotalCount(EventRemind condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_eventRemind_count"), condition);
    }

    @Override
    public List<EventRemind> selectList(EventRemind condition) {
        return super.selectList(NAMESPACE.concat("select_eventRemind"),
            condition, EventRemind.class);
    }

    @Override
    public List<EventRemind> selectList(EventRemind condition, int start,
            int limit) {
        return super.selectList(NAMESPACE.concat("select_eventRemind"), start,
            limit, condition, EventRemind.class);
    }

    @Override
    public void update(EventRemind data) {
        super.update(NAMESPACE.concat("upadte_eventRemind"), data);
    }

}
