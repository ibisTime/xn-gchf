package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Brand;

public interface IBrandDAO extends IBaseDAO<Brand> {
    String NAMESPACE = IBrandDAO.class.getName().concat(".");

    public int update(Brand data);

    public int updateUp(Brand data);

    public int updateDown(Brand data);
}
