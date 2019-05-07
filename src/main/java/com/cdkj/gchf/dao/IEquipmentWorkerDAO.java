package com.cdkj.gchf.dao;

import com.cdkj.gchf.dao.base.IBaseDAO;
import com.cdkj.gchf.domain.EquipmentWorker;

//dao层 
public interface IEquipmentWorkerDAO extends IBaseDAO<EquipmentWorker> {
    String NAMESPACE = IEquipmentWorkerDAO.class.getName().concat(".");

    int update(EquipmentWorker equipmentWorker);

}
