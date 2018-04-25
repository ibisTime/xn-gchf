package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Series;

public interface ISeriesDAO extends IBaseDAO<Series> {

    String NAMESPACE = ISeriesDAO.class.getName().concat(".");

    public int update(Series data);

    public int updateUp(Series data);

    public int updateDown(Series data);
}
