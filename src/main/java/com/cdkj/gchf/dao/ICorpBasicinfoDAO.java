package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;

public interface ICorpBasicinfoDAO extends IBaseDAO<CorpBasicinfo> {
    String NAMESPACE = ICorpBasicinfoDAO.class.getName().concat(".");

    int update(CorpBasicinfo corpBasicinfo);

}
