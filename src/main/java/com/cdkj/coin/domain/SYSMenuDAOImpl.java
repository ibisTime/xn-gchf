package com.cdkj.coin.domain;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.dao.ISYSMenuDAO;
import com.cdkj.coin.dao.base.support.AMybatisTemplate;

@Repository("sysMenuDAOImpl")
public class SYSMenuDAOImpl extends AMybatisTemplate implements ISYSMenuDAO {

    @Override
    public int insert(SYSMenu data) {
        return super.insert(NAMESPACE.concat("insert_sysMenu"), data);
    }

    @Override
    public int delete(SYSMenu data) {
        return super.delete(NAMESPACE.concat("delete_sysMenu"), data);
    }

    @Override
    public SYSMenu select(SYSMenu condition) {
        return super.select(NAMESPACE.concat("select_sysMenu"), condition,
            SYSMenu.class);
    }

    @Override
    public long selectTotalCount(SYSMenu condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_sysMenu_count"),
            condition);
    }

    @Override
    public List<SYSMenu> selectList(SYSMenu condition) {
        return super.selectList(NAMESPACE.concat("select_sysMenu"), condition,
            SYSMenu.class);
    }

    @Override
    public List<SYSMenu> selectList(SYSMenu condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sysMenu"), start,
            count, condition, SYSMenu.class);
    }

    @Override
    public int update(SYSMenu data) {
        return super.update(NAMESPACE.concat("update_sysMenu"), data);
    }

}
