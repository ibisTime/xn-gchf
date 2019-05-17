package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631820Req;
import com.cdkj.gchf.humanfaces.res.DeviceInfo;
import com.cdkj.gchf.humanfaces.res.DeviceQuery;

//CHECK ��鲢��ע�� 
public interface IEquipmentInfoBO extends IPaginableBO<EquipmentInfo> {

    public boolean isEquipmentInfoExist(String code);

    /**
     * 添加设备
     */
    public String saveEquipmentInfo(EquipmentInfo data);

    /**
     * 添加设备
     */
    String saveEquipmentInfo(XN631820Req req, Project project,
                             DeviceQuery query);

    public int removeEquipmentInfo(String code);

    /**
     * 修改设备信息
     */
    public int refreshEquipmentInfo(EquipmentInfo data);

    /**
     * 修改准入时间
     */
    void updatePassTimes(String code, String passTimes);

    /**
     * 按添加查
     */
    public List<EquipmentInfo> queryEquipmentInfoList(EquipmentInfo condition);

    /**
     * 根据项目编号查询设备信息
     */
    List<EquipmentInfo> queryEquipmentList(String projectCode);

    public EquipmentInfo getEquipmentInfo(String code);

    EquipmentInfo refreshEquipment(String deviceKey, String projectCode,
                                   DeviceInfo info);

    /**
     * 根据序列号查询设备
     */
    EquipmentInfo getEquipmentInfoByKey(String deviceKey);

}
