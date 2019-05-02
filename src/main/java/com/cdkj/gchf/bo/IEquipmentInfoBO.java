package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631820Req;
import com.cdkj.gchf.humanfaces.res.DeviceQuery;

//CHECK ��鲢��ע�� 
public interface IEquipmentInfoBO extends IPaginableBO<EquipmentInfo> {

    public boolean isEquipmentInfoExist(String code);

    public String saveEquipmentInfo(EquipmentInfo data);

    String saveEquipmentInfo(XN631820Req req, Project project,
            DeviceQuery query);

    public int removeEquipmentInfo(String code);

    public int refreshEquipmentInfo(EquipmentInfo data);

    public List<EquipmentInfo> queryEquipmentInfoList(EquipmentInfo condition);

    public EquipmentInfo getEquipmentInfo(String code);

}
