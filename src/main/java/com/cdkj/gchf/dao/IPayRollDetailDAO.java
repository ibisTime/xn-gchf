package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.PayRollDetail;

public interface IPayRollDetailDAO extends IBaseDAO<PayRollDetail> {
    String NAMESPACE = IPayRollDetailDAO.class.getName().concat(".");

    int update(PayRollDetail payRollDetail);

    int updateStatus(PayRollDetail payRollDetail);

    int updatePayRollCode(PayRollDetail payRollDetail);

    int updatePayRollDetailDeleteStatus(PayRollDetail payRollDetail);
}
