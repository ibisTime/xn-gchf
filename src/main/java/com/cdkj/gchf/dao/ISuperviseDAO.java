package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Supervise;

/**
 * 监管单位
 * @author: silver 
 * @since: 2018年8月1日 上午10:28:59 
 * @history:
 */
public interface ISuperviseDAO extends IBaseDAO<Supervise> {
    String NAMESPACE = ISuperviseDAO.class.getName().concat(".");
}
