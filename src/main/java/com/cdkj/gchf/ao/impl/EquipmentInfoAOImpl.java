package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IEquipmentInfoAO;
import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631820Req;
import com.cdkj.gchf.dto.req.XN631821Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.humanfaces.Device;
import com.cdkj.gchf.humanfaces.EEquipmentResponse;
import com.cdkj.gchf.humanfaces.res.DeviceQuery;
import com.cdkj.gchf.humanfaces.res.DeviceRes;
import com.cdkj.gchf.humanfaces.res.ResultMsg;

//CHECK ��鲢��ע�� 
@Service
public class EquipmentInfoAOImpl implements IEquipmentInfoAO {

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private Device device;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Override
    public String addEquipmentInfo(EquipmentInfo data) {
        return equipmentInfoBO.saveEquipmentInfo(data);
    }

    /**
     * 
     * <p>Title: addEquipmentInfo</p>   
     * <p>Description: 添加考勤设备</p>   
     */
    @Override
    public String addEquipmentInfo(XN631820Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        DeviceRes deviceCreation = device.deviceCreation(req.getDeviceKey(),
            req.getName(), req.getTag());
        Project project = projectBO.getProject(req.getProjectCode());
        if (project == null) {
            throw new BizException("XN631820", "请选择项目");
        }
        String code = null;
        if (deviceCreation.getCode()
            .equals(EEquipmentResponse.TIANJIACHENGGONG.getCode())) {
            // 成功
            // 同步数据到云端
            device.EquipmentUpdate(req.getDeviceKey(), req.getName(),
                req.getTag());
            DeviceQuery deviceQuery = device.deviceQuery(req.getDeviceKey());
            code = equipmentInfoBO.saveEquipmentInfo(req, project, deviceQuery);
        } else {
            EEquipmentResponse message = EEquipmentResponse
                .getMessage(deviceCreation.getCode());
            if (message != null) {
                throw new BizException("XN613820", message.getMsg());
            }
            throw new BizException("XN613820", deviceCreation.getCode());
        }
        operateLogBO.saveOperateLog(EOperateLogRefType.EquipmentInfo.getCode(),
            code, EOperateLogOperate.AddEquipment.getValue(), user,
            "添加设备:" + req.getName());
        return code;
    }

    @Override
    public int editEquipmentInfo(EquipmentInfo data) {
        if (!equipmentInfoBO.isEquipmentInfoExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return equipmentInfoBO.refreshEquipmentInfo(data);
    }

    @Override
    public int dropEquipmentInfo(String code) {
        if (!equipmentInfoBO.isEquipmentInfoExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return equipmentInfoBO.removeEquipmentInfo(code);
    }

    @Override
    public Paginable<EquipmentInfo> queryEquipmentInfoPage(int start, int limit,
            EquipmentInfo condition) {
        return equipmentInfoBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<EquipmentInfo> queryEquipmentInfoList(EquipmentInfo condition) {
        return equipmentInfoBO.queryEquipmentInfoList(condition);
    }

    @Override
    public EquipmentInfo getEquipmentInfo(String code) {
        return equipmentInfoBO.getEquipmentInfo(code);
    }

    @Override
    public void modifyEquipment(XN631821Req req) {
        EquipmentInfo equipmentInfo = equipmentInfoBO
            .getEquipmentInfo(req.getCode());
        device.EquipmentUpdate(equipmentInfo.getDeviceKey(), req.getName(),
            req.getTag());
        ResultMsg updateCloudDevice = device
            .updateCloudDevice(equipmentInfo.getDeviceKey());
        if (updateCloudDevice.getCode()
            .equals(EEquipmentResponse.TONGBUCHENGGONG.getCode())) {
            // 更新成功
            equipmentInfo.setName(req.getName());
            equipmentInfo.setTag(req.getTag());
            equipmentInfoBO.refreshEquipmentInfo(equipmentInfo);

        } else {
            throw new BizException("XN631821", updateCloudDevice.getMsg());
        }

    }

}
