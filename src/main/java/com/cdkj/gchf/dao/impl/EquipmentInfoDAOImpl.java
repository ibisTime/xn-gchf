package com.cdkj.gchf.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.gchf.dao.IEquipmentInfoDAO;
import com.cdkj.gchf.dao.base.support.AMybatisTemplate;
import com.cdkj.gchf.domain.EquipmentInfo;

//CHECK 。。。 
@Repository("equipmentInfoDAOImpl")
public class EquipmentInfoDAOImpl extends AMybatisTemplate
        implements IEquipmentInfoDAO {

    @Override
    public int insert(EquipmentInfo data) {
        return super.insert(NAMESPACE.concat("insert_EquipmentInfo"), data);
    }

    @Override
    public int delete(EquipmentInfo data) {
        return super.delete(NAMESPACE.concat("delete_EquipmentInfo"), data);
    }

    @Override
    public EquipmentInfo select(EquipmentInfo condition) {
        return super.select(NAMESPACE.concat("select_EquipmentInfo"), condition,
                EquipmentInfo.class);
    }

    @Override
    public long selectTotalCount(EquipmentInfo condition) {
        return super.selectTotalCount(
                NAMESPACE.concat("select_EquipmentInfo_count"), condition);
    }

    @Override
    public List<EquipmentInfo> selectList(EquipmentInfo condition) {
        return super.selectList(NAMESPACE.concat("select_EquipmentInfo"),
                condition, EquipmentInfo.class);
    }

    @Override
    public List<EquipmentInfo> selectList(EquipmentInfo condition, int start,
                                          int count) {
        return super.selectList(NAMESPACE.concat("select_EquipmentInfo"), start,
                count, condition, EquipmentInfo.class);
    }

    @Override
    public int update(EquipmentInfo condition) {
        return super.update(NAMESPACE.concat("update_EquipmentInfo"),
                condition);
    }

    @Override
    public int updateEquipment(EquipmentInfo equipmentInfo) {
        return super.update(NAMESPACE.concat("update_EquipmentInfo_cloud"),
                equipmentInfo);
    }

    @Override
    public int updateEquipmentPassTimes(EquipmentInfo equipmentInfo) {
        return super.update(NAMESPACE.concat("update_EquipmentInfo_passTimes"),
                equipmentInfo);
    }

}
