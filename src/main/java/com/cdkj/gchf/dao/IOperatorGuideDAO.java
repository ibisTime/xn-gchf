package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.OperatorGuide;

public interface IOperatorGuideDAO extends IBaseDAO<OperatorGuide> {
    String NAMESPACE = IOperatorGuideDAO.class.getName().concat(".");

    public int update(OperatorGuide data);

}
