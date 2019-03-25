package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.ITeamMasterDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.TeamMaster;

@Repository("teamMasterDAOImpl")
public class TeamMasterDAOImpl extends AMybatisTemplate
        implements ITeamMasterDAO {

    @Override
    public int insert(TeamMaster data) {
        return super.insert(NAMESPACE.concat("insert_teamMaster"), data);
    }

    @Override
    public int delete(TeamMaster data) {
        return super.delete(NAMESPACE.concat("delete_teamMaster"), data);
    }

    @Override
    public TeamMaster select(TeamMaster condition) {
        return super.select(NAMESPACE.concat("select_teamMaster"), condition,
            TeamMaster.class);
    }

    @Override
    public long selectTotalCount(TeamMaster condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_teamMaster_count"), condition);
    }

    @Override
    public List<TeamMaster> selectList(TeamMaster condition) {
        return super.selectList(NAMESPACE.concat("select_teamMaster"),
            condition, TeamMaster.class);
    }

    @Override
    public List<TeamMaster> selectList(TeamMaster condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_teamMaster"), start,
            count, condition, TeamMaster.class);
    }

    @Override
    public int update(TeamMaster data) {
        return super.update(NAMESPACE.concat("update_teamMaster"), data);
    }

    @Override
    public int updateUploadStatus(TeamMaster data) {
        return super.update(NAMESPACE.concat("update_teamMasterUploadStatus"),
            data);
    }

}
