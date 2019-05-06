package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.IdCardChecker;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.dto.req.XN631793Req;
import com.cdkj.gchf.enums.ECultureLevelType;
import com.cdkj.gchf.enums.EGender;
import com.cdkj.gchf.enums.EIdCardType;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EPoliticsType;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.humanfaces.Device;
import com.cdkj.gchf.humanfaces.DeviceWorker;
import com.cdkj.gchf.humanfaces.EEquipmentWorkerResponse;
import com.cdkj.gchf.humanfaces.EPicResponse;
import com.cdkj.gchf.humanfaces.WorkerPicture;
import com.cdkj.gchf.humanfaces.res.DeviceWorkerPicRes;
import com.cdkj.gchf.humanfaces.res.DeviceWorkerRes;

@Service
public class WorkerInfoAOImpl implements IWorkerInfoAO {

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private WorkerPicture workerPicture;

    @Autowired
    private Device device;

    @Autowired
    private DeviceWorker deviceWorker;

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    @Override
    public String addWorkerInfo(XN631790Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        // 数据字典校验
        EPoliticsType.checkExists(req.getPoliticsType());
        ECultureLevelType.checkExists(req.getCultureLevelType());
        EIdCardType.checkExists(req.getIdCardType());
        req.setGender(EGender.checkExists(req.getGender()));

        IdCardChecker idCardChecker = new IdCardChecker(req.getIdCardNumber());
        if (!idCardChecker.validate()) {
            throw new BizException("XN631790", "身份证信息错误");
        }

        if (StringUtils.isNotBlank(req.getCode())) {
            // 重新建档
            WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(req.getCode());
            XN631793Req xn631791Req = new XN631793Req();
            xn631791Req.setCode(req.getCode());
            BeanUtils.copyProperties(req, xn631791Req);

            operateLogBO.saveOperateLog(EOperateLogRefType.WorkerInfo.getCode(),
                workerInfo.getCode(), "重新建档人员实名制信息", user, null);

            // 存在项目人员-更新 基本基本信息
            if (!req.getIdCardNumber().equals(workerInfo.getIdCardNumber())
                    || !req.getName().equals(workerInfo.getName())) {
                projectWorkerBO.refreshWorkerIdCardNumber(req.getCode(),
                    req.getIdCardNumber(), req.getName());
            }

            // 更新建档信息
            workerInfoBO.refreshWorkerInfo(xn631791Req);
            return req.getCode();
        }
        // 身份证信息已存在
        WorkerInfo infoByIdCardNumber = workerInfoBO
            .getWorkerInfoByIdCardNumber(req.getIdCardNumber());
        if (infoByIdCardNumber != null) {
            // 更新建档信息
            XN631793Req xn631791Req = new XN631793Req();
            BeanUtils.copyProperties(req, xn631791Req);
            xn631791Req.setCode(infoByIdCardNumber.getCode());
            workerInfoBO.refreshWorkerInfo(xn631791Req);
            return infoByIdCardNumber.getCode();
        }
        String workerCode = workerInfoBO.saveWorkerInfo(req);

        // if (StringUtils.isNotBlank(req.getProjectCode())) {
        // Project project = projectBO.getProject(req.getProjectCode());
        // projectWorkerBO.saveProjectWorker(workerCode, req.getName(),
        // req.getIdCardNumber(), project);
        // }

        return workerCode;
    }

    @Override
    public int dropWorkerInfo(String code) {
        if (!workerInfoBO.isWorkerInfoExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return workerInfoBO.removeWorkerInfo(code);
    }

    /**
     * 
     * <p>Title: refreshAttendancePicture</p>   
     * <p>Description: base64方式 上传考勤照片到云端  保存云端返回的图片URL到本地 
     * </p>   
     * @param code 人员编号
     * @param attendancePicture 考勤照片 (base64)
     */
    @Override
    public void refreshAttendancePicture(String code, String attendancePicture,
            String userId) {
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(code);
        if (StringUtils.isNotBlank(workerInfo.getAttendancePicture())) {
            // 人员已经上传过 -> 没有考勤照片 -> 添加考勤照片
            // ↓→ 有考勤照片 -> 删除后添加考勤照片
            String workerGuid = workerInfo.getWorkerGuid();
            if (StringUtils.isNotBlank(workerGuid)) {
                // // 人员已存在, 删除旧照片 , 添加新的 (base64 方式)
                workerPicture.picDelCloud(workerGuid,
                    workerInfo.getWorkerAttendancePicGuid());

                DeviceWorkerPicRes picRegisterToCloud = workerPicture
                    .picRegisterToCloud(workerGuid, attendancePicture, "1",
                        null, null);
                if (picRegisterToCloud.getCode()
                    .equals(EPicResponse.TIANJIACHENG.getCode())) {
                    workerInfoBO.refreshAttendancePic(code, attendancePicture);

                }
            } else {
                // 人员没上传过 -> 拿到项目下所有设备 ->添加人员到设备中 已存在就添加照片
                String organizationCode = userBO.getBriefUser(userId)
                    .getOrganizationCode();
                // 向所有的机器中录入该人员
                List<EquipmentInfo> equipmentList = equipmentInfoBO
                    .getEquipmentList(organizationCode);
                for (EquipmentInfo equipmentInfo : equipmentList) {
                    // 查询该设备是否有该人员 没有则录入到云端,更新本地数据
                    String cloudWorkerQuery = deviceWorker
                        .cloudWorkerQuery(workerGuid);
                    JSONObject jsonObject = JSONObject
                        .parseObject(cloudWorkerQuery);
                    if (jsonObject.getString("code").equals(
                        EEquipmentWorkerResponse.CHAXUNCHENGGONG.getCode())
                            && jsonObject.get("data") == null) {
                        // 这个设备没有这个人 录入人员 录入照片
                        DeviceWorkerRes cloudWorkerAdd = deviceWorker
                            .cloudWorkerAdd(equipmentInfo.getDeviceKey(),
                                workerInfo.getName(), workerInfo.getCode(),
                                workerInfo.getCellPhone(), null, null);
                        DeviceWorkerPicRes picRegisterToCloudUrl = workerPicture
                            .picRegisterToCloud(
                                cloudWorkerAdd.getData().getGuid(),
                                attendancePicture, null, null, null);
                        workerInfoBO.updateWorkerInfoAttendance(
                            workerInfo.getCode(),
                            cloudWorkerAdd.getData().getGuid(),
                            picRegisterToCloudUrl.getData().getGuid());
                        workerInfoBO.refreshAttendancePic(workerInfo.getCode(),
                            attendancePicture);
                    } else {
                        // 有这个人
                        String guid = jsonObject.getString("guid");
                        // 注册人员照片到机器
                        DeviceWorkerPicRes picRegisterToCloudUrl = workerPicture
                            .picRegisterToCloud(jsonObject.getString("guid"),
                                attendancePicture, null, null, null);
                        workerInfoBO.updateWorkerInfoAttendance(
                            workerInfo.getCode(), guid,
                            picRegisterToCloudUrl.getData().getGuid());
                        workerInfoBO.refreshAttendancePic(workerInfo.getCode(),
                            attendancePicture);
                    }
                }
            }

        }
        // workerInfoBO.refreshAttendancePic(code, attendancePicture);
    }

    @Override
    public Paginable<WorkerInfo> queryWorkerInfoPage(String userId, int start,
            int limit, WorkerInfo condition) {
        User user = userBO.getBriefUser(userId);
        if (user.getType().equals(EUserKind.Owner.getCode())) {
            condition.setBusinessIdCardNumber(condition.getIdCardNumber());
            condition.setIdCardNumber(null);
        }
        return workerInfoBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition) {
        return workerInfoBO.queryWorkerInfoList(condition);
    }

    @Override
    public WorkerInfo getWorkerInfo(String code) {
        return workerInfoBO.getWorkerInfo(code);
    }

    @Override
    public WorkerInfo getWorkerInfoByIdCardNumber(String idCardNumber) {
        return workerInfoBO.getWorkerInfoByIdCardNumber(idCardNumber);
    }

    @Override
    public int addWorkerInfoIdCardInfo(XN631791Req req) {
        return workerInfoBO.refreshWorkerInfo(req);
    }

    @Override
    public int addWorkerInfoContact(XN631792Req req) {
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(req.getCode());
        if (workerInfo.getCellPhone() == null
                || !workerInfo.getCellPhone().equals(req.getCellPhone())) {
            // 手机号改变 更新项目人员手机号码
            projectWorkerBO.refreshWorkerCelephone(workerInfo.getCode(),
                req.getCellPhone());
        }
        return workerInfoBO.refreshWorkerInfo(req);
    }

    @Override
    public void readdWorkerInfo(XN631793Req req) {
        User user = userBO.getBriefUser(req.getUserId());

        workerInfoBO.refreshWorkerInfo(req);
        projectWorkerBO.refreshWorkerIdCardNumber(req.getCode(),
            req.getIdCardNumber(), req.getName());

        operateLogBO.saveOperateLog(EOperateLogRefType.WorkerInfo.getCode(),
            req.getCode(), "重新建档人员实名制信息", user, null);
    }

}
