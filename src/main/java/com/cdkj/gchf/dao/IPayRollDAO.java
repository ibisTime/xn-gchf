package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;

public interface IPayRollDAO extends IBaseDAO<PayRoll> {
    String NAMESPACE = IPayRollDAO.class.getName().concat(".");

    int update(PayRoll payRoll);

    int updatePayRollCode(PayRoll payRoll);

    int updatePayRollDeleteStatus(PayRoll payRoll);


}
