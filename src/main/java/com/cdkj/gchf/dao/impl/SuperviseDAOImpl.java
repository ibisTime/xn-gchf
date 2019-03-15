package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ISuperviseDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Supervise;

/**
 * 监管单位
 * @author: silver 
 * @since: 2018年8月1日 上午10:29:52 
 * @history:
 */
@Repository("superviseDAOImpl")
public class SuperviseDAOImpl extends AMybatisTemplate
        implements ISuperviseDAO {

    @Override
    public int insert(Supervise data) {
        return super.insert(NAMESPACE.concat("insert_supervise"), data);
    }

    @Override
    public int delete(Supervise data) {
        return super.delete(NAMESPACE.concat("delete_supervise"), data);
    }

    @Override
    public Supervise select(Supervise condition) {
        return super.select(NAMESPACE.concat("select_supervise"), condition,
            Supervise.class);
    }

    @Override
    public long selectTotalCount(Supervise condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_supervise_count"), condition);
    }

    @Override
    public List<Supervise> selectList(Supervise condition) {
        return super.selectList(NAMESPACE.concat("select_supervise"), condition,
            Supervise.class);
    }

    @Override
    public List<Supervise> selectList(Supervise condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_supervise"), start,
            count, condition, Supervise.class);
    }

}
