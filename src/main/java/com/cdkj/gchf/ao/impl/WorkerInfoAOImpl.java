package com.cdkj.gchf.ao.impl;

import com.cdkj.gchf.ao.IWorkerInfoAO;
import com.cdkj.gchf.bo.*;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.IdCardChecker;
import com.cdkj.gchf.common.StringUtil;
import com.cdkj.gchf.domain.*;
import com.cdkj.gchf.dto.req.*;
import com.cdkj.gchf.enums.*;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.humanfaces.Device;
import com.cdkj.gchf.humanfaces.DeviceWorker;
import com.cdkj.gchf.humanfaces.WorkerPicture;
import com.cdkj.gchf.humanfaces.enums.EAttendancePicUploadStatus;
import com.cdkj.gchf.humanfaces.enums.EPicResponse;
import com.cdkj.gchf.humanfaces.enums.EWorkerUploadStatus;
import com.cdkj.gchf.humanfaces.res.DeviceWorkerPicRes;
import com.cdkj.gchf.humanfaces.res.DeviceWorkerRes;
import com.cdkj.gchf.zqzn.ZqznInfoBack;
import com.cdkj.gchf.zqzn.ZqznInfoFront;
import com.cdkj.gchf.zqzn.ZqznUtil;
import java.awt.Font;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author old3
 */
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
    private DeviceWorker deviceWorker;

    @Autowired
    private Device device;

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IBankCardBankBO bankCardBankBO;

    @Override
    @Transactional
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
            checkIsExist(req.getCode(), req.getIdCardNumber());

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
        // 身份证重复校验
        if (workerInfoBO
                .getWorkerInfoByIdCardNumber(req.getIdCardNumber()) != null) {
            throw new BizException("XN0000", "身份证号已存在,请修改");
        }
        String workerCode = workerInfoBO.saveWorkerInfo(req);

        return workerCode;
    }

    @Override
    public void refreshAttendancePicture(String code, String attendancePicture,
            String userId) {
        workerInfoBO.refreshAttendancePic(code, attendancePicture, null, null);
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(code);
        // 人员已经上传过 -> 没有考勤照片 -> 添加考勤照片
        // ↓→ 有考勤照片 -> 删除后添加考勤照片
        String workerGuid = workerInfo.getWorkerGuid();
        if (StringUtils.isNotBlank(workerGuid)) {
            // // 人员已存在, 删除旧照片 , 添加新的 (base64 方式)
            workerPicture.picDelCloud(workerInfo.getWorkerAttendancePicGuid(),
                    workerGuid);

            DeviceWorkerPicRes picRegisterToCloud = workerPicture
                    .picRegisterToCloud(workerGuid, attendancePicture, "1", null,
                            null);
            if (picRegisterToCloud.getCode()
                    .equals(EPicResponse.TIANJIACHENG.getCode())) {
                // 照片添加成功
                workerInfoBO.refreshAttendancePic(code, attendancePicture,
                        EWorkerUploadStatus.SUCCESS.getCode(),
                        EAttendancePicUploadStatus.SUCCESS.getCode());
                workerInfoBO.updateWorkerInfoAttendance(workerInfo.getCode(),
                        workerGuid, picRegisterToCloud.getData().getGuid());
            } else {
                workerInfoBO.refreshAttendancePic(code, attendancePicture,
                        EWorkerUploadStatus.SUCCESS.getCode(),
                        EAttendancePicUploadStatus.FAIL.getCode());
                throw new BizException("XN631794",
                        "人脸录入云端失败:" + picRegisterToCloud.getMsg());

            }
        } else {
            // 添加人员到云端
            DeviceWorkerRes cloudWorkerAdd = deviceWorker.cloudWorkerAdd(
                    workerInfo.getName(), workerInfo.getCode(),
                    workerInfo.getCellPhone(), null, null);
            // 添加照片信息到云端
            DeviceWorkerPicRes picRegisterToCloud = workerPicture
                    .picRegisterToCloud(cloudWorkerAdd.getData().getGuid(),
                            attendancePicture, "1", null, null);
            if (picRegisterToCloud.getCode()
                    .equals(EPicResponse.TIANJIACHENG.getCode())) {
                // 添加成功
                // 更新本地人员 和图片guid
                workerInfoBO.updateWorkerInfoAttendance(workerInfo.getCode(),
                        cloudWorkerAdd.getData().getGuid(),
                        picRegisterToCloud.getData().getGuid());
                workerInfoBO.refreshAttendancePic(workerInfo.getCode(),
                        attendancePicture, EWorkerUploadStatus.SUCCESS.getCode(),
                        EAttendancePicUploadStatus.SUCCESS.getCode());
            } else {
                workerInfoBO.updateWorkerInfoAttendance(workerInfo.getCode(),
                        cloudWorkerAdd.getData().getGuid(), null);
                workerInfoBO.refreshAttendancePic(workerInfo.getCode(),
                        attendancePicture, EWorkerUploadStatus.SUCCESS.getCode(),
                        EAttendancePicUploadStatus.FAIL.getCode());
            }

        }
        // 同步下设备
        List<EquipmentInfo> queryEquipmentList = equipmentInfoBO
                .queryEquipmentList(
                        userBO.getBriefUser(userId).getOrganizationCode());
        for (EquipmentInfo equipmentInfo : queryEquipmentList) {
            device.updateCloudDevice(equipmentInfo.getDeviceKey());
        }
    }

    @Override
    public void refreshHandIdCardImage(String code, String handIdCardImage) {
        workerInfoBO.refreshHandIdCardImage(code, handIdCardImage);
    }

    @Override
    public Paginable<WorkerInfo> queryWorkerInfoPage(String userId, int start,
            int limit, WorkerInfo condition) {
        User user = userBO.getBriefUser(userId);
        if (user.getType().equals(EUserKind.Owner.getCode())) {
            condition.setBusinessIdCardNumber(condition.getIdCardNumber());
            condition.setIdCardNumber(null);
        }

        long totalCount = workerInfoBO.getTotalCount(condition);

        Paginable<WorkerInfo> page = new Page<WorkerInfo>(start, limit,
                totalCount);

        List<WorkerInfo> dataList = workerInfoBO.queryStaffListBrief(condition,
                page.getStart(), page.getPageSize());

        page.setList(dataList);

        return page;

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
    public List<BankCardInfo> selectWorkerInfoByWorkerCode(String workerCode) {
        List<ProjectWorker> projectWorkers = projectWorkerBO
                .selectProjectWorkerByWorkerCode(workerCode);
        List<BankCardInfo> bankCardInfoList = new ArrayList<>();
        for (ProjectWorker temp : projectWorkers) {
            List<BankCardInfo> bankCardByByssinessCode = bankCardBankBO
                    .getBankCardByByssinessCode(EBankCardBussinessType.USER.getCode(),
                            temp.getCode());
            bankCardInfoList.addAll(bankCardByByssinessCode);
        }
        return bankCardInfoList;
    }

    /**
     * <p>Title: addWorkerInfoIdCardInfo</p>
     * <p>Description: 添加身份证考勤照片</p>
     */
    @Override
    public int addWorkerInfoIdCardInfo(XN631791Req req) {
        // 考勤相关
        WorkerInfo workerInfo = workerInfoBO.getWorkerInfo(req.getCode());
        if (StringUtils.isNotBlank(workerInfo.getWorkerGuid())) {
            // 已上传人员
            String workerGuid = workerInfo.getWorkerGuid();
            DeviceWorkerPicRes picRegisterToCloud = null;
            if (StringUtils
                    .isNotBlank(workerInfo.getWorkerAttendancePicGuid())) {
                // 删除重新添加
                workerPicture.picDelCloud(
                        workerInfo.getWorkerAttendancePicGuid(), workerGuid);

            }
            // 添加照片
            picRegisterToCloud = workerPicture.picRegisterToCloud(workerGuid,
                    req.getAttendancePicture(), "1", null, null);
            workerInfoBO.updateWorkerInfoAttendance(req.getCode(), workerGuid,
                    picRegisterToCloud.getData().getGuid());
            workerInfoBO.refreshAttendancePic(workerInfo.getCode(),
                    req.getAttendancePicture(),
                    EAttendancePicUploadStatus.SUCCESS.getCode(),
                    EAttendancePicUploadStatus.SUCCESS.getCode());

        } else {
            // 未上传
            DeviceWorkerRes cloudWorkerAdd = deviceWorker.cloudWorkerAdd(
                    workerInfo.getName(), workerInfo.getCode(),
                    workerInfo.getCellPhone(), null, null);
            DeviceWorkerPicRes picRegisterToCloud = workerPicture
                    .picRegisterToCloud(cloudWorkerAdd.getData().getGuid(),
                            req.getAttendancePicture(), "1", null, null);
            workerInfoBO.updateWorkerInfoAttendance(req.getCode(),
                    cloudWorkerAdd.getData().getGuid(),
                    picRegisterToCloud.getData().getGuid());
            workerInfoBO.refreshAttendancePic(workerInfo.getCode(),
                    req.getAttendancePicture(),
                    EAttendancePicUploadStatus.SUCCESS.getCode(),
                    EAttendancePicUploadStatus.SUCCESS.getCode());
        }
        // 同步设备
        List<EquipmentInfo> queryEquipmentList = equipmentInfoBO
                .queryEquipmentList(
                        userBO.getBriefUser(req.getUserId()).getOrganizationCode());
        for (EquipmentInfo equipmentInfo : queryEquipmentList) {
            device.updateCloudDevice(equipmentInfo.getDeviceKey());
        }
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
    @Transactional
    public String addOcrWorkerInfo(XN631795Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        Project project = projectBO.getProject(user.getOrganizationCode());

        if (project.getTotalOcrCount() - project.getUsedOcrCount() < 1) {
            throw new BizException("XN631794", "身份证识别次数已用完，请联系服务商");
        }

        ZqznInfoFront front = ZqznUtil.getOcrFrontInfo(req.getPositiveImage());
        ZqznInfoBack back = ZqznUtil.getOcrBackInfo(req.getNegativeImage());

        projectBO.refreshUsedOcrCount(project.getCode(),
                project.getUsedOcrCount() + 1);
        if (StringUtils.isNotBlank(req.getCode())) {
            workerInfoBO.updateWorkerInfoIdcardImageH5(req.getCode(), front.getFaceImg(),
                    req.getPositiveImage(),
                    req.getNegativeImage());
            return req.getCode();
        }
        projectWorkerBO.getProjectWorker(user.getOrganizationCode(), front.getIdNo());

        WorkerInfo workerInfoByIdCardNumber = workerInfoBO
                .getWorkerInfoByIdCardNumber(front.getIdNo());

        if (workerInfoByIdCardNumber != null) {
            XN631791Req rs = new XN631791Req();
            rs.setCode(workerInfoByIdCardNumber.getCode());
            rs.setHandIdCardImageUrl(front.getFaceImg());
            rs.setPositiveIdCardImageUrl(req.getPositiveImage());
            rs.setNegativeIdCardImageUrl(req.getNegativeImage());
            workerInfoBO.refreshWorkerInfo(rs);
            return workerInfoByIdCardNumber.getCode();
        }

        return workerInfoBO.saveWorkerInfo(req, front, back);
    }

    @Override
    public String refreshWorkerInfoH5(XN631797Req req) {
        return workerInfoBO.refreshWorkerInfo(req);
    }

    @Override
    public void readdWorkerInfo(XN631793Req req) {

        checkIsExist(req.getCode(), req.getIdCardNumber());

        User user = userBO.getBriefUser(req.getUserId());

        workerInfoBO.refreshWorkerInfo(req);

        projectWorkerBO.refreshWorkerIdCardNumber(req.getCode(),
                req.getIdCardNumber(), req.getName());

        operateLogBO.saveOperateLog(EOperateLogRefType.WorkerInfo.getCode(),
                req.getCode(), "重新建档人员实名制信息", user, null);
    }

    private void checkIsExist(String code, String idCardNumber) {
        WorkerInfo workerInfo = workerInfoBO.getBriefWorkerInfo(code);

        if (!idCardNumber.equals(workerInfo.getIdCardNumber())) {
            WorkerInfo workerInfoByIdCardNumber = workerInfoBO
                    .getWorkerInfoByIdCardNumber(idCardNumber);
            if (null != workerInfoByIdCardNumber) {
                throw new BizException("XN0000",
                        "项目中已存在身份证号为" + idCardNumber + "的人员信息");
            }
        }
    }

}
