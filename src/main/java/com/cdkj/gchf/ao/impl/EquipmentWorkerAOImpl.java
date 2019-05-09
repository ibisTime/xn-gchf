package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IEquipmentWorkerAO;
import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.IEquipmentWorkerBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.EquipmentWorker;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631830Req;
import com.cdkj.gchf.enums.EProjectWorkerUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.humanfaces.Device;
import com.cdkj.gchf.humanfaces.DeviceWorker;
import com.cdkj.gchf.humanfaces.WorkerPicture;
import com.cdkj.gchf.humanfaces.enums.EAttendancePicUploadStatus;
import com.cdkj.gchf.humanfaces.enums.EEquipmentWorkerResponse;
import com.cdkj.gchf.humanfaces.enums.EWorkerUploadStatus;
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

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IUserBO userBO;

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
        if (StringUtils.isNotBlank(condition.getUserId())) {
            String userId = condition.getUserId();
            User briefUser = userBO.getBriefUser(userId);
            condition.setProjectCode(briefUser.getOrganizationCode());
        }

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
     * <p>Description:添加设备人员 -> 单设备-多人员的情况</p>   
     */
    @Override
    public void addEquipmentWorker(XN631830Req req) {
        EquipmentInfo equipmentInfo = equipmentInfoBO
            .getEquipmentInfo(req.getEquipmentCode());
        if (equipmentInfo == null) {
            throw new BizException("XN631830", "设备不存在");
        }

        EquipmentWorker equipmentWorker = new EquipmentWorker();
        equipmentWorker.setDeviceKey(equipmentInfo.getDeviceKey());
        List<EquipmentWorker> queryEquipmentWorkerList = equipmentWorkerBO
            .queryEquipmentWorkerList(equipmentWorker);
        for (EquipmentWorker temp : queryEquipmentWorkerList) {
            // 取消旧的所有人员授权

            ProjectWorker projectWorker = projectWorkerBO
                .getProjectWorker(temp.getWorkerCode());
            WorkerInfo workerInfo = workerInfoBO
                .getWorkerInfo(projectWorker.getWorkerCode());
            if (workerInfo.getWorkerUploadStatus()
                .equals(EWorkerUploadStatus.SUCCESS.getCode())) {
                // 取消授权
                deviceWorker.workerBatchElimination(workerInfo.getWorkerGuid(),
                    equipmentInfo.getDeviceKey());
            }

            // 删除本地数据
            equipmentWorkerBO.removeEquipmentWorker(temp.getCode());
        }

        List<String> workerInfos = new ArrayList<>();
        List<ProjectWorker> projectWorkers = new ArrayList<>();
        // 校验
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
            // 考勤照片未上传的不可授权
            WorkerInfo workerInfo = workerInfoBO
                .getWorkerInfo(projectWorker.getWorkerCode());
            if ((workerInfo.getWorkerPicUploadStatus() == null
                    && workerInfo.getWorkerUploadStatus() == null)
                    || (workerInfo.getWorkerPicUploadStatus()
                        .equals(EAttendancePicUploadStatus.FAIL.getCode())
                            && workerInfo.getWorkerUploadStatus()
                                .equals(EWorkerUploadStatus.FAIL.getCode()))) {
                throw new BizException("XN631830", "项目人员"
                        + projectWorker.getWorkerName() + "】考勤照片不符合标准,请修改考勤照片");
            }
            // 查看是否授权过 已在其他机器添加过照片并授权的 不管
            String workerAuthorizationQuery = deviceWorker
                .workerAuthorizationQuery(workerInfo.getWorkerGuid());
            JSONObject parseObject = JSONObject
                .parseObject(workerAuthorizationQuery);
            if (parseObject.getJSONArray("data").isEmpty()) {
                workerInfos.add(workerInfo.getWorkerGuid());
                projectWorkers.add(projectWorker);
            } else {
                // 已经授权过的 移除授权重新授权
                JSONArray jsonArray = parseObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject obj = (JSONObject) jsonArray.get(i);
                    String object = (String) obj.get("deviceKey");
                    if (object.equals(equipmentInfo.getDeviceKey())) {
                        // 此设备已授权

                        deviceWorker.workerBatchElimination(
                            workerInfo.getWorkerGuid(),
                            equipmentInfo.getDeviceKey());
                        // 删除本地数据
                        equipmentWorkerBO.removeEquipmentWorker(
                            projectWorker.getWorkerCode());
                        workerInfos.add(workerInfo.getWorkerGuid());
                        projectWorkers.add(projectWorker);
                    }
                }
            }

        }
        if (CollectionUtils.isNotEmpty(workerInfos)) {
            // 设备授权人员照片
            ResultMsg cloudWorkerAuthorizationEuipment = deviceWorker
                .cloudWorkerAuthorizationEuipment(equipmentInfo.getDeviceKey(),
                    workerInfos, req.getStartTime(), req.getEndTime());
            if (cloudWorkerAuthorizationEuipment.getCode()
                .equals(EEquipmentWorkerResponse.SHOUQUANCHENGONG.getCode())) {
                // 授权成功

                for (ProjectWorker projectWorker : projectWorkers) {

                    equipmentWorkerBO.saveEquipmentWorker(req, equipmentInfo,
                        projectWorker);
                }
            }
            new Device().updateCloudDevice(equipmentInfo.getDeviceKey());
        }

    }

}
