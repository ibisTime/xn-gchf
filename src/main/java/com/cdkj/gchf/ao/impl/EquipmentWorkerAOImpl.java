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
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.humanfaces.DeviceWorker;
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
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IUserBO userBO;

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

    @Override
    public void addEquipmentWorker(XN631830Req req) {
        EquipmentInfo equipmentInfo = equipmentInfoBO
                .getEquipmentInfo(req.getEquipmentCode());
        if (equipmentInfo == null) {
            throw new BizException("XN631830", "设备不存在");
        }

        // 校验人员照片信息
        checkWorkerPicValid(req);

        // 根据序列号查询设备人员 取消本地设备人员授权
        List<EquipmentWorker> queryEquipmentWorkerList = equipmentWorkerBO
                .getEquipmentWorkerList(equipmentInfo.getDeviceKey());

        for (EquipmentWorker temp : queryEquipmentWorkerList) {

            ProjectWorker projectWorker = projectWorkerBO
                    .getProjectWorker(temp.getWorkerCode());
            WorkerInfo workerInfo = workerInfoBO
                    .getWorkerInfo(projectWorker.getWorkerCode());

            if (workerInfo.getWorkerUploadStatus()
                    .equals(EWorkerUploadStatus.SUCCESS.getCode())) {
                // 取消授权
                deviceWorker.workerBatchElimination(workerInfo.getWorkerGuid(),
                        equipmentInfo.getDeviceKey());
                // 删除本地数据
                equipmentWorkerBO.removeEquipmentWorker(temp.getCode());
            } else {
                throw new BizException(
                        "工人【" + workerInfo.getName() + "】信息异常,请联系管理员");
            }

        }

        List<String> workerInfos = new ArrayList<>();
        List<ProjectWorker> projectWorkers = new ArrayList<>();
        // 查看云端人员授权情况
        for (String code : req.getWorkerList()) {

            ProjectWorker projectWorker = projectWorkerBO
                    .getProjectWorker(code);
            // 考勤照片未上传的不可授权
            WorkerInfo workerInfo = workerInfoBO
                    .getWorkerInfo(projectWorker.getWorkerCode());
            // 查看人员授权情况，这台机器已授权 取消授权。否则授权
            String workerAuthorizationQuery = deviceWorker
                    .workerAuthorizationQuery(workerInfo.getWorkerGuid());

            JSONObject parseObject = JSONObject
                    .parseObject(workerAuthorizationQuery);
            if (parseObject.getJSONArray("data").isEmpty()) {
                workerInfos.add(workerInfo.getWorkerGuid());
                projectWorkers.add(projectWorker);
            } else {
                // 已经授权过的 移除授权
                JSONArray jsonArray = parseObject.getJSONArray("data");
                List<String> euiqmentDevice = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject obj = (JSONObject) jsonArray.get(i);
                    String object = (String) obj.get("deviceKey");
                    euiqmentDevice.add(object);
                }
                if (euiqmentDevice.contains(equipmentInfo.getDeviceKey())) {
                    deviceWorker.workerBatchElimination(
                            workerInfo.getWorkerGuid(),
                            equipmentInfo.getDeviceKey());
                    // 删除本地数据
                    equipmentWorkerBO
                            .removeEquipmentWorker(projectWorker.getWorkerCode());

                }
                workerInfos.add(workerInfo.getWorkerGuid());
                projectWorkers.add(projectWorker);

            }

        }
        // 人员授权
        if (CollectionUtils.isNotEmpty(workerInfos)) {
            ResultMsg cloudWorkerAuthorizationEuipment = deviceWorker
                    .cloudWorkerAuthorizationEuipment(equipmentInfo.getDeviceKey(),
                            workerInfos, req.getStartTime(), req.getEndTime());
            if (cloudWorkerAuthorizationEuipment != null
                    && cloudWorkerAuthorizationEuipment.getCode().equals(
                    EEquipmentWorkerResponse.SHOUQUANCHENGONG.getCode())) {
                // 授权成功

                equipmentWorkerBO.batchSaveEquipmentWorker(projectWorkers,
                        equipmentInfo, req);
            }

        }

    }

    private void checkWorkerPicValid(XN631830Req req) {
        // 校验人员实名制考勤照片是否上传

        for (String code : req.getWorkerList()) {
            ProjectWorker projectWorker = projectWorkerBO
                    .getProjectWorker(code);
            // 考勤照片未上传的不可授权
            WorkerInfo workerInfo = workerInfoBO
                    .getWorkerInfo(projectWorker.getWorkerCode());

            if (StringUtils.isBlank(workerInfo.getWorkerPicUploadStatus())
                    || StringUtils
                    .isBlank(workerInfo.getWorkerUploadStatus())) {
                throw new BizException("XN631830", "项目人员"
                        + projectWorker.getWorkerName() + "】考勤照片不符合标准,请修改考勤照片");
            }
            if (workerInfo.getWorkerPicUploadStatus()
                    .equals(EAttendancePicUploadStatus.FAIL.getCode())
                    && workerInfo.getWorkerUploadStatus()
                    .equals(EWorkerUploadStatus.FAIL.getCode())) {
                throw new BizException("XN631830", "项目人员"
                        + projectWorker.getWorkerName() + "】考勤照片不符合标准,请修改考勤照片");
            }
        }
    }

}
