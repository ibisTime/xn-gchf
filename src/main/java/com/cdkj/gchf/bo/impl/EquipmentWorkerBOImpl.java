package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IEquipmentWorkerBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IEquipmentWorkerDAO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631830Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class EquipmentWorkerBOImpl extends PaginableBOImpl<EquipmentWorker>
        implements IEquipmentWorkerBO {

    @Autowired
    private IEquipmentWorkerDAO EquipmentWorkerDAO;

    @Override
    public boolean isEquipmentWorkerExist(String code) {
        EquipmentWorker condition = new EquipmentWorker();
        condition.setCode(code);
        if (EquipmentWorkerDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveEquipmentWorker(EquipmentWorker data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.EquipmentWorker.getCode());
            data.setCode(code);
            EquipmentWorkerDAO.insert(data);
        }
        return code;
    }

    @Override
    public String saveEquipmentWorker(XN631830Req req,
            EquipmentInfo equipmentInfo, ProjectWorker projectWorker) {
        EquipmentWorker equipmentWorker = new EquipmentWorker();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.EquipmentWorker.getCode());
        equipmentWorker.setCode(code);
        equipmentWorker.setDeviceKey(equipmentInfo.getDeviceKey());
        equipmentWorker.setDeviceCode(equipmentInfo.getCode());
        equipmentWorker.setDeviceName(equipmentInfo.getName());
        equipmentWorker.setWorkerCode(projectWorker.getCode());
        equipmentWorker.setWorkerName(projectWorker.getWorkerName());
        equipmentWorker.setTeamCode(projectWorker.getTeamSysNo());
        equipmentWorker.setTeamName(projectWorker.getTeamName());
        equipmentWorker.setIdCardNumber(projectWorker.getIdcardNumber());

        if (StringUtils.isBlank(req.getStartTime())
                && StringUtils.isBlank(req.getEndTime())) {
            equipmentWorker.setPassTimes("00:00:00,23:59:59");
        } else {
            equipmentWorker
                .setPassTimes(req.getStartTime() + "," + req.getEndTime());
        }

        equipmentWorker.setCreateTime(
            DateUtil.dateToStr(new Date(System.currentTimeMillis()),
                DateUtil.DATA_TIME_PATTERN_1));
        EquipmentWorkerDAO.insert(equipmentWorker);
        return code;
    }

    @Override
    public int removeEquipmentWorker(String code) {
        int count = 0;
        EquipmentWorker data = new EquipmentWorker();
        data.setCode(code);
        count = EquipmentWorkerDAO.delete(data);
        return count;
    }

    @Override
    public int refreshEquipmentWorker(EquipmentWorker data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = EquipmentWorkerDAO.update(data);
        }
        return count;
    }

    @Override
    public List<EquipmentWorker> queryEquipmentWorkerList(
            EquipmentWorker condition) {
        return EquipmentWorkerDAO.selectList(condition);
    }

    @Override
    public EquipmentWorker getEquipmentWorker(String code) {
        EquipmentWorker data = null;
        if (StringUtils.isNotBlank(code)) {
            EquipmentWorker condition = new EquipmentWorker();
            condition.setCode(code);
            data = EquipmentWorkerDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }

}
