package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.domain.ProjectWorker;

//CHECK ��鲢��ע�� 
public interface IEquipmentWorkerBO extends IPaginableBO<EquipmentWorker> {

    public boolean isEquipmentWorkerExist(String code);

    public String saveEquipmentWorker(EquipmentWorker data);

    public int removeEquipmentWorker(String code);

    public int refreshEquipmentWorker(EquipmentWorker data);

    public List<EquipmentWorker> queryEquipmentWorkerList(
            EquipmentWorker condition);

    public EquipmentWorker getEquipmentWorker(String code);

    void saveEquipmentWorker(EquipmentInfo equipmentInfo,
            ProjectWorker projectWorker);

}
