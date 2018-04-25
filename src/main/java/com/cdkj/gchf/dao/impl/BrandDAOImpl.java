package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IBrandDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Brand;

@Repository("brandDAOImpl")
public class BrandDAOImpl extends AMybatisTemplate implements IBrandDAO {

    @Override
    public int insert(Brand data) {
        return super.insert(NAMESPACE.concat("insert_brand"), data);
    }

    @Override
    public int delete(Brand data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Brand select(Brand condition) {
        // TODO Auto-generated method stub
        return super.select(NAMESPACE.concat("select_brand"), condition,
            Brand.class);
    }

    @Override
    public long selectTotalCount(Brand condition) {
        // TODO Auto-generated method stub
        return super.selectTotalCount(NAMESPACE.concat("select_brand_count"),
            condition);
    }

    @Override
    public List<Brand> selectList(Brand condition) {
        return super.selectList(NAMESPACE.concat("select_brand"), condition,
            Brand.class);
    }

    @Override
    public List<Brand> selectList(Brand condition, int start, int count) {
        // TODO Auto-generated method stub
        return super.selectList(NAMESPACE.concat("select_brand"), start, count,
            condition, Brand.class);
    }

    @Override
    public int update(Brand data) {
        // TODO Auto-generated method stub
        return super.update(NAMESPACE.concat("update_brand"), data);
    }

    @Override
    public int updateUp(Brand data) {
        // TODO Auto-generated method stub
        return super.update(NAMESPACE.concat("update_brand_up"), data);
    }

    @Override
    public int updateDown(Brand data) {
        // TODO Auto-generated method stub
        return super.update(NAMESPACE.concat("update_brand_down"), data);
    }

}
