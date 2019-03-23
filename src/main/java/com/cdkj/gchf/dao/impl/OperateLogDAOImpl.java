package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IOperateLogDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.OperateLog;

@Repository("operateLogDAOImpl")
public class OperateLogDAOImpl extends AMybatisTemplate
        implements IOperateLogDAO {

    @Override
    public int insert(OperateLog data) {
        return super.insert(NAMESPACE.concat("insert_operateLog"), data);
    }

    @Override
    public int delete(OperateLog data) {
        return super.delete(NAMESPACE.concat("delete_operateLog"), data);
    }

    @Override
    public OperateLog select(OperateLog condition) {
        return super.select(NAMESPACE.concat("select_operateLog"), condition,
            OperateLog.class);
    }

    @Override
    public long selectTotalCount(OperateLog condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_operateLog_count"), condition);
    }

    @Override
    public List<OperateLog> selectList(OperateLog condition) {
        return super.selectList(NAMESPACE.concat("select_operateLog"),
            condition, OperateLog.class);
    }

    @Override
    public List<OperateLog> selectList(OperateLog condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_operateLog"), start,
            count, condition, OperateLog.class);
    }

    @Override
    public int updateRemark(OperateLog operateLog) {
        return super.update(NAMESPACE.concat("update_operateLogRemark"),
            operateLog);
    }

}
