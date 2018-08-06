package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IOperatorGuideDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.OperatorGuide;

/**
 * 操作指南
 * @author: silver 
 * @since: 2018年8月6日 下午4:49:29 
 * @history:
 */
@Repository("operatorGuideDAOImpl")
public class OperatorGuideDAOImpl extends AMybatisTemplate
        implements IOperatorGuideDAO {

    @Override
    public int insert(OperatorGuide data) {
        return super.insert(NAMESPACE.concat("insert_operatorGuide"), data);
    }

    @Override
    public int delete(OperatorGuide data) {
        return super.delete(NAMESPACE.concat("delete_operatorGuide"), data);
    }

    @Override
    public int update(OperatorGuide data) {
        return super.update(NAMESPACE.concat("update_operatorGuide"), data);
    }

    @Override
    public OperatorGuide select(OperatorGuide condition) {
        return super.select(NAMESPACE.concat("select_operatorGuide"), condition,
            OperatorGuide.class);
    }

    @Override
    public long selectTotalCount(OperatorGuide condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_operatorGuide_count"), condition);
    }

    @Override
    public List<OperatorGuide> selectList(OperatorGuide condition) {
        return super.selectList(NAMESPACE.concat("select_operatorGuide"),
            condition, OperatorGuide.class);
    }

    @Override
    public List<OperatorGuide> selectList(OperatorGuide condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_operatorGuide"), start,
            count, condition, OperatorGuide.class);
    }
}
