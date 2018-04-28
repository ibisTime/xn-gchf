package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.Progress;

//dao层 
public interface IProgressDAO extends IBaseDAO<Progress> {
    String NAMESPACE = IProgressDAO.class.getName().concat(".");

    public int updateProgress(Progress data);
}
