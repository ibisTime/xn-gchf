package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.SalaryLog;

public interface ISalaryLogDAO extends IBaseDAO<SalaryLog> {
    String NAMESPACE = ISalaryLogDAO.class.getName().concat(".");

    public SalaryLog selectLastSalaryLog(SalaryLog data);
}
