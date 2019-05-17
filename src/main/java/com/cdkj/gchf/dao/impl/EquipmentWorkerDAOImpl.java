package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IEquipmentWorkerDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.EquipmentWorker;

@Repository("equipmentWorkerDAOImpl")
public class EquipmentWorkerDAOImpl extends AMybatisTemplate
        implements IEquipmentWorkerDAO {

    @Override
    public int insert(EquipmentWorker data) {
        return super.insert(NAMESPACE.concat("insert_EquipmentWorker"), data);
    }

    @Override
    public int delete(EquipmentWorker data) {
        return super.delete(NAMESPACE.concat("delete_EquipmentWorker"), data);
    }

    @Override
    public EquipmentWorker select(EquipmentWorker condition) {
        return super.select(NAMESPACE.concat("select_EquipmentWorker"),
                condition, EquipmentWorker.class);
    }

    @Override
    public long selectTotalCount(EquipmentWorker condition) {
        return super.selectTotalCount(
                NAMESPACE.concat("select_EquipmentWorker_count"), condition);
    }

    @Override
    public List<EquipmentWorker> selectList(EquipmentWorker condition) {
        return super.selectList(NAMESPACE.concat("select_EquipmentWorker"),
                condition, EquipmentWorker.class);
    }

    @Override
    public List<EquipmentWorker> selectList(EquipmentWorker condition,
                                            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_EquipmentWorker"),
                start, count, condition, EquipmentWorker.class);
    }

    @Override
    public int update(EquipmentWorker equipmentWorker) {
        return super.update(NAMESPACE.concat("update_EquipmentWorker"),
                equipmentWorker);
    }

    @Override
    public void batchInsert(List<EquipmentWorker> equipmentWorker) {
        super.insertBatch(NAMESPACE.concat("batch_insert"), equipmentWorker);
    }

}
