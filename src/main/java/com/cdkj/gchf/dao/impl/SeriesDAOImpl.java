package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ISeriesDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.Series;

@Repository("seriesDAOImpl")
public class SeriesDAOImpl extends AMybatisTemplate implements ISeriesDAO {

    @Override
    public int insert(Series data) {
        return super.insert(NAMESPACE.concat("insert_series"), data);
    }

    @Override
    public int delete(Series data) {
        return 0;
    }

    @Override
    public Series select(Series condition) {
        return super.select(NAMESPACE.concat("select_series"), condition,
            Series.class);
    }

    @Override
    public long selectTotalCount(Series condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_series_count"),
            condition);
    }

    @Override
    public List<Series> selectList(Series condition) {
        return super.selectList(NAMESPACE.concat("select_series"), condition,
            Series.class);
    }

    @Override
    public List<Series> selectList(Series condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_series"), start, count,
            condition, Series.class);
    }

    @Override
    public int update(Series data) {
        return super.update(NAMESPACE.concat("update_series"), data);
    }

    @Override
    public int updateUp(Series data) {
        return super.update(NAMESPACE.concat("update_series_up"), data);
    }

    @Override
    public int updateDown(Series data) {
        return super.update(NAMESPACE.concat("update_series_down"), data);
    }

}
