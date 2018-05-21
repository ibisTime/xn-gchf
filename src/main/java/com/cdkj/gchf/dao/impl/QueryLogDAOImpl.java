package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IQueryLogDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.QueryLog;

@Repository("queryLogDAOImpl")
public class QueryLogDAOImpl extends AMybatisTemplate implements IQueryLogDAO {

    @Override
    public int insert(QueryLog data) {
        return super.insert(NAMESPACE.concat("insert_queryLog"), data);
    }

    @Override
    public int delete(QueryLog data) {
        return super.delete(NAMESPACE.concat("delete_queryLog"), data);
    }

    @Override
    public QueryLog select(QueryLog condition) {
        return super.select(NAMESPACE.concat("select_queryLog"), condition,
            QueryLog.class);
    }

    @Override
    public long selectTotalCount(QueryLog condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_queryLog_count"),
            condition);
    }

    @Override
    public List<QueryLog> selectList(QueryLog condition) {
        return super.selectList(NAMESPACE.concat("select_queryLog"), condition,
            QueryLog.class);
    }

    @Override
    public List<QueryLog> selectList(QueryLog condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_queryLog"), start,
            count, condition, QueryLog.class);
    }

}
