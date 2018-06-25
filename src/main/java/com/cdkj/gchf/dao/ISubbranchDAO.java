package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Subbranch;

/**
 * 支行表
 * @author: silver 
 * @since: 2018年6月25日 下午7:12:34 
 * @history:
 */
public interface ISubbranchDAO extends IBaseDAO<Subbranch> {
    String NAMESPACE = ISubbranchDAO.class.getName().concat(".");
}
