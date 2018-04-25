package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ICarDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Car;

@Repository("carDAOImpl")
public class CarDAOImpl extends AMybatisTemplate implements ICarDAO {

    @Override
    public int insert(Car data) {
        return super.insert(NAMESPACE.concat("insert_car"), data);
    }

    @Override
    public int delete(Car data) {
        return 0;
    }

    @Override
    public Car select(Car condition) {
        return super.select(NAMESPACE.concat("select_car"), condition,
            Car.class);
    }

    @Override
    public long selectTotalCount(Car condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_car_count"),
            condition);
    }

    @Override
    public List<Car> selectList(Car condition) {
        return super.selectList(NAMESPACE.concat("select_car"), condition,
            Car.class);
    }

    @Override
    public List<Car> selectList(Car condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_car"), start, count,
            condition, Car.class);
    }

    @Override
    public int update(Car data) {
        return super.update(NAMESPACE.concat("update_car"), data);
    }

    @Override
    public int updateUp(Car data) {
        return super.update(NAMESPACE.concat("update_car_up"), data);
    }

    @Override
    public int updateDown(Car data) {
        return super.update(NAMESPACE.concat("update_car_down"), data);
    }

}
