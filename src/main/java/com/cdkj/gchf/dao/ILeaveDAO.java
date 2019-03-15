package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Leave;

/**
 * 请假明细
 * @author: silver 
 * @since: 2018年7月3日 下午1:59:27 
 * @history:
 */
public interface ILeaveDAO extends IBaseDAO<Leave> {
    String NAMESPACE = ILeaveDAO.class.getName().concat(".");

    public int selectMonthLeaveDays(Leave data);

}
