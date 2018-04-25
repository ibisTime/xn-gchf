package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ICarOrderDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.CarOrder;

@Repository("carOrderDAOImpl")
public class CarOrderDAOImpl extends AMybatisTemplate implements ICarOrderDAO {

    @Override
    public int insert(CarOrder data) {
        return super.insert(NAMESPACE.concat("insert_carOrder"), data);
    }

    @Override
    public int delete(CarOrder data) {
        return 0;
    }

    @Override
    public CarOrder select(CarOrder condition) {
        return super.select(NAMESPACE.concat("select_carOrder"), condition,
            CarOrder.class);
    }

    @Override
    public long selectTotalCount(CarOrder condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_carOrder_count"),
            condition);
    }

    @Override
    public List<CarOrder> selectList(CarOrder condition) {
        return super.selectList(NAMESPACE.concat("select_carOrder"), condition,
            CarOrder.class);
    }

    @Override
    public List<CarOrder> selectList(CarOrder condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_carOrder"), start,
            count, condition, CarOrder.class);
    }

    @Override
    public int update(CarOrder data) {
        return super.update(NAMESPACE.concat("update_carOrder"), data);
    }

}
