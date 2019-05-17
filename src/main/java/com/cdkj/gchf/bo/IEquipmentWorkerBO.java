package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631830Req;

//CHECK ��鲢��ע�� 
public interface IEquipmentWorkerBO extends IPaginableBO<EquipmentWorker> {

    public boolean isEquipmentWorkerExist(String code);

    public String saveEquipmentWorker(EquipmentWorker data);

    /**
     * 保存设备人员
     */
    void batchSaveEquipmentWorker(List<ProjectWorker> projectWorkers,
                                  EquipmentInfo equipmentInfo, XN631830Req req);

    public int removeEquipmentWorker(String code);

    public int refreshEquipmentWorker(EquipmentWorker data);

    public List<EquipmentWorker> queryEquipmentWorkerList(
            EquipmentWorker condition);

    public EquipmentWorker getEquipmentWorker(String code);

    /**
     * 根据设备序列号查询设备人员
     */
    List<EquipmentWorker> getEquipmentWorkerList(String deviceKey);

    String saveEquipmentWorker(XN631830Req req, EquipmentInfo equipmentInfo,
                               ProjectWorker projectWorker);

}
