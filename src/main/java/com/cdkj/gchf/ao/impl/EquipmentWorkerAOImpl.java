package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IEquipmentWorkerAO;
import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.IEquipmentWorkerBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.dto.req.XN631830Req;
import com.cdkj.gchf.enums.EProjectWorkerUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.humanfaces.DeviceWorker;
import com.cdkj.gchf.humanfaces.EEquipmentWorkerResponse;
import com.cdkj.gchf.humanfaces.WorkerPicture;
import com.cdkj.gchf.humanfaces.res.DeviceWorkerPicRes;
import com.cdkj.gchf.humanfaces.res.DeviceWorkerRes;
import com.cdkj.gchf.humanfaces.res.DeviceWorkerRes.DeviceWorkerInfo;
import com.cdkj.gchf.humanfaces.res.ResultMsg;

@Service
public class EquipmentWorkerAOImpl implements IEquipmentWorkerAO {

    @Autowired
    private IEquipmentWorkerBO equipmentWorkerBO;

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private DeviceWorker deviceWorker;

    @Autowired
    private WorkerPicture workerPicture;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Override
    public String addEquipmentWorker(EquipmentWorker data) {
        return equipmentWorkerBO.saveEquipmentWorker(data);
    }

    @Override
    public int editEquipmentWorker(EquipmentWorker data) {
        if (!equipmentWorkerBO.isEquipmentWorkerExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return equipmentWorkerBO.refreshEquipmentWorker(data);
    }

    @Override
    public int dropEquipmentWorker(String code) {
        if (!equipmentWorkerBO.isEquipmentWorkerExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return equipmentWorkerBO.removeEquipmentWorker(code);
    }

    @Override
    public Paginable<EquipmentWorker> queryEquipmentWorkerPage(int start,
            int limit, EquipmentWorker condition) {
        return equipmentWorkerBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<EquipmentWorker> queryEquipmentWorkerList(
            EquipmentWorker condition) {
        return equipmentWorkerBO.queryEquipmentWorkerList(condition);
    }

    @Override
    public EquipmentWorker getEquipmentWorker(String code) {
        return equipmentWorkerBO.getEquipmentWorker(code);
    }

    /**
     * <p>Title: addEquipmentWorker</p>   
     * <p>Description:添加设备人员 </p>   
     */
    @Override
    public void addEquipmentWorker(XN631830Req req) {
        EquipmentInfo equipmentInfo = equipmentInfoBO
            .getEquipmentInfo(req.getEquipmentCode());
        if (equipmentInfo == null) {
            throw new BizException("XN631830", "设备不存在");
        }
        List<String> workerInfos = new ArrayList<>();
        for (String code : req.getWorkerList()) {
            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(code);
            if (!projectWorker.getUploadStatus()
                .equals(EProjectWorkerUploadStatus.UPLOAD_UNUPDATE.getCode())
                    && !projectWorker.getUploadStatus().equals(
                        EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode())) {
                throw new BizException("XN631830",
                    "项目人员未上传" + projectWorker.getWorkerName());
            }
            // 人员录入
            DeviceWorkerRes worker = deviceWorker.cloudWorkerAdd(
                equipmentInfo.getDeviceKey(), projectWorker.getWorkerName(),
                projectWorker.getCode(), projectWorker.getWorkerMobile(),
                String.valueOf(projectWorker.getWorkRole()),
                projectWorker.getWorkType());
            // 添加成功
            if (worker.getCode()
                .equals(EEquipmentWorkerResponse.TIANJIACHENGGONG.getCode())) {

                // 回写guid到实名制信息中
                DeviceWorkerInfo device = worker.getData();
                com.cdkj.gchf.domain.WorkerInfo workerInfo = workerInfoBO
                    .getWorkerInfo(projectWorker.getWorkerCode());

                workerInfos.add(worker.getData().getGuid());

                // 人员照片信息录入
                DeviceWorkerPicRes picRegisterToCloudUrl = workerPicture
                    .picRegisterToCloudUrl(device.getGuid(),
                        workerInfo.getAttendancePicture(),
                        projectWorker.getWorkType(), null, null);
                workerInfoBO.updateWorkerInfoAttendance(workerInfo.getCode(),
                    worker.getData().getGuid(),
                    picRegisterToCloudUrl.getData().getGuid());

            } else {
                throw new BizException(worker.getMsg());
            }

        }
        // 设备授权人员照片
        ResultMsg cloudWorkerAuthorizationEuipment = deviceWorker
            .cloudWorkerAuthorizationEuipment(equipmentInfo.getDeviceKey(),
                workerInfos, req.getStartTime(), req.getEndTime());
        if (cloudWorkerAuthorizationEuipment.getCode()
            .equals(EEquipmentWorkerResponse.SHOUQUANCHENGONG.getCode())) {
            // 授权成功
        }
    }

}
