package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.FieldTimes;

public interface IFieldTimesDAO extends IBaseDAO<FieldTimes> {
    String NAMESPACE = IFieldTimesDAO.class.getName().concat(".");
}
