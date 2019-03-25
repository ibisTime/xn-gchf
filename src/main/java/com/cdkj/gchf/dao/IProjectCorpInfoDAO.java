package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectCorpInfo;

public interface IProjectCorpInfoDAO extends IBaseDAO<ProjectCorpInfo> {
    String NAMESPACE = IProjectCorpInfoDAO.class.getName().concat(".");

    int update(ProjectCorpInfo projectCorpInfo);

    int updateUploadStatus(ProjectCorpInfo projectCorpInfo);

}
