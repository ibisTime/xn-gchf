package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IAbnormalRemindDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.AbnormalRemind;

@Repository("abnormalRemindDAOImpl")
public class AbnormalRemindDAOImpl extends AMybatisTemplate
        implements IAbnormalRemindDAO {

    @Override
    public int insert(AbnormalRemind data) {
        return super.insert(NAMESPACE.concat("insert_abnormalRemind"), data);
    }

    @Override
    public int delete(AbnormalRemind data) {
        return super.delete(NAMESPACE.concat("delete_abnormalRemind"), data);
    }

    @Override
    public AbnormalRemind select(AbnormalRemind condition) {
        return super.select(NAMESPACE.concat("select_abnormalRemind"),
            condition, AbnormalRemind.class);
    }

    @Override
    public long selectTotalCount(AbnormalRemind condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_abnormalRemind_count"), condition);
    }

    @Override
    public List<AbnormalRemind> selectList(AbnormalRemind condition) {
        return super.selectList(NAMESPACE.concat("select_abnormalRemind"),
            condition, AbnormalRemind.class);
    }

    @Override
    public List<AbnormalRemind> selectList(AbnormalRemind condition, int start,
            int limit) {
        return super.selectList(NAMESPACE.concat("select_abnormalRemind"),
            start, limit, condition, AbnormalRemind.class);
    }

    @Override
    public void update(AbnormalRemind data) {
        super.update(NAMESPACE.concat("upadte_abnormalRemind"), data);
    }

}
