package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.EquipmentInfo;

//dao层 
public interface IEquipmentInfoDAO extends IBaseDAO<EquipmentInfo> {
    String NAMESPACE = IEquipmentInfoDAO.class.getName().concat(".");

    int update(EquipmentInfo equipmentInfo);

    int updateEquipment(EquipmentInfo equipmentInfo);

    int updateEquipmentPassTimes(EquipmentInfo equipmentInfo);

}
