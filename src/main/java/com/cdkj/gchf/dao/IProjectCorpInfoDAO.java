package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.ProjectCorpInfo;

import java.util.List;

public interface IProjectCorpInfoDAO extends IBaseDAO<ProjectCorpInfo> {
    String NAMESPACE = IProjectCorpInfoDAO.class.getName().concat(".");

    void batchInsert(List<ProjectCorpInfo> projectCorpInfoList);

    int update(ProjectCorpInfo projectCorpInfo);

    int updateUploadStatus(ProjectCorpInfo projectCorpInfo);

    int updateDeleteStatus(ProjectCorpInfo projectCorpInfo);

}
