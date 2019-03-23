package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.OperateLog;

public interface IOperateLogDAO extends IBaseDAO<OperateLog> {
    String NAMESPACE = IOperateLogDAO.class.getName().concat(".");

    int updateRemark(OperateLog operateLog);

}
