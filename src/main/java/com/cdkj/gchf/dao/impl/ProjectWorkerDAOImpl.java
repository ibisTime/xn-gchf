package com.cdkj.gchf.dao.impl;

import java.util.List;

import java.util.Map;
import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IProjectWorkerDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.ProjectWorker;

@Repository("projectWorkerDAOImpl")
public class ProjectWorkerDAOImpl extends AMybatisTemplate
        implements IProjectWorkerDAO {

    @Override
    public int insert(ProjectWorker data) {
        return super.insert(NAMESPACE.concat("insert_projectWorker"), data);
    }

    @Override
    public void batchInsertProjectWorker(List<ProjectWorker> projectWorkerList) {
        super.insertBatch(NAMESPACE.concat("batch_insert"), projectWorkerList);
    }

    @Override
    public int delete(ProjectWorker data) {
        return super.delete(NAMESPACE.concat("delete_projectWorker"), data);
    }

    @Override
    public ProjectWorker select(ProjectWorker condition) {
        return super.select(NAMESPACE.concat("select_projectWorker"), condition,
                ProjectWorker.class);
    }

    @Override
    public long selectTotalCount(ProjectWorker condition) {
        return super.selectTotalCount(
                NAMESPACE.concat("select_projectWorker_count"), condition);
    }

    @Override
    public List<ProjectWorker> selectList(ProjectWorker condition) {
        return super.selectList(NAMESPACE.concat("select_projectWorker"),
                condition, ProjectWorker.class);
    }

    @Override
    public List<ProjectWorker> selectList(ProjectWorker condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_projectWorker"), start,
                count, condition, ProjectWorker.class);
    }

    @Override
    public int update(ProjectWorker projectWorker) {
        return super.update(NAMESPACE.concat("update_projectWorker"),
                projectWorker);
    }

    @Override
    public int updateTeamSysNoByLocal(ProjectWorker projectWorker) {
        return super.update(NAMESPACE.concat("update_teamSysNoByLocal"),
                projectWorker);
    }

    @Override
    public int updateUploadStatus(ProjectWorker projectWorker) {
        return super.update(NAMESPACE.concat("update_projectWorker_status"),
                projectWorker);
    }

    @Override
    public int updateStatus(ProjectWorker projectWorker) {
        return super.update(NAMESPACE.concat("updateStatus"),
                projectWorker);
    }

    @Override
    public int updateLastPayRoll(ProjectWorker projectWorker) {
        return super.update(NAMESPACE.concat("updateLastPayRoll"),
                projectWorker);
    }

    @Override
    public int updateLastInOutRecord(ProjectWorker projectWorker) {
        return super.update(
                NAMESPACE.concat("updateLastInOutRecord"),
                projectWorker);
    }

    @Override
    public int updateLastAttendance(ProjectWorker projectWorker) {
        return super.update(
                NAMESPACE.concat("updateLastAttendance"),
                projectWorker);
    }

    @Override
    public int updateProjectWorkerDeleteStatus(ProjectWorker projectWorker) {
        return super.update(
                NAMESPACE.concat("update_projectWorker_delete_status"),
                projectWorker);
    }

    @Override
    public int updateProjectWorkerUploadStatus(ProjectWorker projectWorker) {
        return super.update(
                NAMESPACE.concat("update_projectWorker_upload_status"),
                projectWorker);
    }

    @Override
    public int updateProjectWorkerTeamName(ProjectWorker projectWorker) {

        return super.update(NAMESPACE.concat("update_projectWorker_team_name"),
                projectWorker);
    }

    @Override
    public int updateProjectWorkerWorkerInfo(ProjectWorker projectWorker) {
        return super.update(
                NAMESPACE.concat("update_projectWorker_worker_info"),
                projectWorker);
    }

    @Override
    public int updateProjectWorkerWorkerPhone(ProjectWorker projectWorker) {
        return super.update(
                NAMESPACE.concat("update_projectWorker_worker_info_phone"),
                projectWorker);
    }

    @Override
    public List<Map> selectWorkerTypeSpread(String userId) {
        return super.selectList(NAMESPACE.concat("selectWorkerTypeSpread"), userId, Map.class);
    }


    @Override
    public List<Map> selectWorkerAgeInterval(String userId) {
        return super.selectList(NAMESPACE.concat("selectWorkerAgeInterval"), userId, Map.class);
    }

    @Override
    public List<Map> selectWorkerData(String userId) {
        return super.selectList(NAMESPACE.concat("selectWorkerData"), userId, Map.class);
    }
}
