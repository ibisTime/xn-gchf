package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IEquipmentInfoDAO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631820Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.humanfaces.res.DeviceInfo;
import com.cdkj.gchf.humanfaces.res.DeviceQuery;

//CHECK ��鲢��ע�� 
@Component
public class EquipmentInfoBOImpl extends PaginableBOImpl<EquipmentInfo>
        implements IEquipmentInfoBO {

    @Autowired
    private IEquipmentInfoDAO EquipmentInfoDAO;

    @Override
    public boolean isEquipmentInfoExist(String code) {
        EquipmentInfo condition = new EquipmentInfo();
        condition.setCode(code);
        if (EquipmentInfoDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveEquipmentInfo(EquipmentInfo data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.EquipmentInfo.getCode());
            data.setCode(code);
            EquipmentInfoDAO.insert(data);
        }
        return code;
    }

    @Override
    public String saveEquipmentInfo(XN631820Req req, Project project,
            DeviceQuery query) {
        String code = null;
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        code = OrderNoGenerater
            .generate(EGeneratePrefix.EquipmentInfo.getCode());
        // req
        equipmentInfo.setCode(code);
        equipmentInfo.setDeviceKey(req.getDeviceKey());
        equipmentInfo.setProjectCode(req.getProjectCode());
        equipmentInfo.setProjectName(project.getName());
        equipmentInfo.setName(req.getName());
        equipmentInfo.setTag(req.getTag());
        // res
        DeviceInfo data = query.getData();
        equipmentInfo.setCreateTime(new Date(data.getCreateTime()));
        equipmentInfo.setNeedUpgrade(data.getNeedUpgrade() ? 1 : 0);
        equipmentInfo.setNeedUpgradeApp(data.getNeedUpgradeApp() ? 1 : 0);
        equipmentInfo.setRegNum(data.getRegNum());
        equipmentInfo.setSceneGuid(data.getSceneGuid());
        equipmentInfo.setSystemVersionNo(data.getSystemVersionNo());
        equipmentInfo.setState(data.getState());
        equipmentInfo.setTagEncreapt(data.getTag());
        equipmentInfo.setStatus(data.getStatus());
        equipmentInfo.setcId(data.getCid());
        equipmentInfo.setClientId(data.getClientId());
        equipmentInfo.setExpired(data.getExpired() ? 1 : 0);
        EquipmentInfoDAO.insert(equipmentInfo);
        return code;
    }

    @Override
    public int removeEquipmentInfo(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            EquipmentInfo data = new EquipmentInfo();
            data.setCode(code);
            count = EquipmentInfoDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshEquipmentInfo(EquipmentInfo data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = EquipmentInfoDAO.update(data);
        }
        return count;
    }

    @Override
    public List<EquipmentInfo> queryEquipmentInfoList(EquipmentInfo condition) {
        return EquipmentInfoDAO.selectList(condition);
    }

    @Override
    public EquipmentInfo getEquipmentInfo(String code) {
        EquipmentInfo data = null;
        if (StringUtils.isNotBlank(code)) {
            EquipmentInfo condition = new EquipmentInfo();
            condition.setCode(code);
            data = EquipmentInfoDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
