package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.TeamMaster;

import java.util.List;

public interface ITeamMasterDAO extends IBaseDAO<TeamMaster> {
    String NAMESPACE = ITeamMasterDAO.class.getName().concat(".");

    int update(TeamMaster data);

    int updateUploadStatus(TeamMaster data);

    int updateTeamSysNo(TeamMaster data);

    int updateDeleteStatus(TeamMaster data);

    void batchInsert(List<TeamMaster> teamMasters);
}
