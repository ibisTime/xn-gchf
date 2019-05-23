package com.cdkj.gchf.bo.impl;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.api.impl.XN631693ReqData;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerInfoDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.*;
import com.cdkj.gchf.enums.EGender;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIsNotType;
import com.cdkj.gchf.enums.EWorkerAttendanceUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.zqzn.ZqznInfoBack;
import com.cdkj.gchf.zqzn.ZqznInfoFront;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WorkerInfoBOImpl extends PaginableBOImpl<WorkerInfo>
        implements IWorkerInfoBO {

    @Autowired
    private IWorkerInfoDAO workerInfoDAO;

    @Override
    public String saveWorkerInfo(XN631790Req req) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerInfo.getCode());
        WorkerInfo workerInfo = new WorkerInfo();
        BeanUtils.copyProperties(req, workerInfo);
        workerInfo.setBirthPlaceCode(req.getIdCardNumber().substring(0, 6));
        Date birthday = DateUtil.strToDate(req.getBirthday(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        workerInfo.setBirthday(birthday);
        Date startDate = DateUtil.strToDate(req.getStartDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        workerInfo.setStartDate(startDate);
        Date enpiryDate = DateUtil.strToDate(req.getExpiryDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        workerInfo.setExpiryDate(enpiryDate);
        if (StringUtils.isNotBlank(req.getHasBadMedicalHistory())) {
            int hasBadMedical = Integer.parseInt(req.getHasBadMedicalHistory());
            workerInfo.setHasBadMedicalHistory(hasBadMedical);
        }
        workerInfo.setSpecialty(req.getSpecialty());
        workerInfo.setGender(Integer.parseInt(req.getGender()));
        workerInfo.setPoliticsType(req.getPoliticsType());
        workerInfo.setCultureLevelType(req.getCultureLevelType());
        workerInfo.setCreateDatetime(new Date(System.currentTimeMillis()));
        if (StringUtils.isNotBlank(req.getIsJoined())) {
            if (StringUtils.isNotBlank(req.getJoinedTime())) {
                EIsNotType.checkExists(req.getIsJoined());
                workerInfo.setIsJoined(Integer.parseInt(req.getIsJoined()));
                Date joinTime = DateUtil.strToDate(req.getJoinedTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                workerInfo.setJoinedTime(joinTime);
            }
        }
        workerInfo.setWorkerPicUploadStatus(EWorkerAttendanceUploadStatus.TO_UPLOAD.getCode());
        workerInfo.setCode(code);
        workerInfoDAO.insert(workerInfo);
        return code;
    }

    @Override
    public String saveWorkerInfo(XN631795Req req, ZqznInfoFront front,
            ZqznInfoBack back) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerInfo.getCode());
        WorkerInfo workerInfo = new WorkerInfo();

        workerInfo.setCode(code);
        workerInfo.setIdCardNumber(front.getIdNo());
        workerInfo.setIdCardType("01");
        workerInfo.setAddress(front.getAddress());

        workerInfo.setGender(
            Integer.parseInt(EGender.checkExists(front.getGender())));
        workerInfo.setName(front.getName());

        workerInfo.setBirthday(
            DateUtil.strToDate(front.getBirth(), DateUtil.DATA_TIME_PATTERN_9));
        workerInfo
            .setHeadImageUrl("data:image/jpeg;base64," + front.getFaceImg());
        workerInfo.setGrantOrg(back.getIssuedBy());
        workerInfo.setNation(front.getRace());

        workerInfo.setPositiveIdCardImageUrl(req.getPositiveImage());
        workerInfo.setNegativeIdCardImageUrl(req.getNegativeImage());

        String[] validDate = back.getValidDate().split("-");
        workerInfo.setStartDate(
            DateUtil.strToDate(validDate[0], DateUtil.DATA_TIME_PATTERN_9));
        workerInfo.setExpiryDate(
            DateUtil.strToDate(validDate[1], DateUtil.DATA_TIME_PATTERN_9));

        workerInfoDAO.insert(workerInfo);
        return code;
    }

    @Override
    public Paginable<WorkerInfo> doQuery(String idCardNumber,
            ProjectConfig projectConfig) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setIdCardType("01");
        workerInfo.setIdCardNumber(
            AesUtils.encrypt(idCardNumber, projectConfig.getSecret()));

        String data = JSONObject.toJSON(workerInfo).toString();

        String queryString = GovConnecter.getGovData("Worker.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<WorkerInfo> page = GovUtil.parseGovPage(0, 1, queryString,
            replaceMap, WorkerInfo.class);

        return page;
    }

    @Override
    public List<WorkerInfo> queryWorkerInfoList(WorkerInfo condition) {
        return workerInfoDAO.selectList(condition);
    }

    // 回写实名制考勤相关信息
    @Override
    public void updateWorkerInfoAttendance(String code, String workerGuid,
            String picGuid) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setCode(code);
        workerInfo.setWorkerGuid(workerGuid);
        workerInfo.setWorkerAttendancePicGuid(picGuid);
        workerInfoDAO.updateWorkerInfoAttendance(workerInfo);
    }

    @Override
    public WorkerInfo getWorkerInfo(String code) {
        WorkerInfo data = null;
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(code);
        data = workerInfoDAO.select(condition);
        if (data == null) {
            throw new BizException("XN631806", "人员实名制信息不存在");
        }
        return data;
    }

    @Override
    public WorkerInfo getBriefWorkerInfo(String code) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setCode(code);
        return workerInfoDAO.selectBriefWorkerInfo(workerInfo);
    }

    @Override
    public WorkerInfo getWorkerInfoByIdCardNumber(String idCardNumber) {
        WorkerInfo workerInfo = new WorkerInfo();
        // workerInfo.setIdCardNumber(idCardNumber);
        workerInfo.setBusinessIdCardNumber(idCardNumber);
        return workerInfoDAO.select(workerInfo);
    }

    @Override
    public List<WorkerInfo> queryStaffListBrief(WorkerInfo condition, int start,
            int count) {
        return workerInfoDAO.selectBrifeList(condition, start, count);
    }

    @Override
    public long queryTotalCount(WorkerInfo condition) {
        return workerInfoDAO.selectTotalCount(condition);
    }

    @Override
    public int refreshWorkerInfo(XN631791Req req) {
        WorkerInfo condition = new WorkerInfo();
        BeanUtils.copyProperties(req, condition);
        return workerInfoDAO.updateWorkerInfoAboutIdcard(condition);
    }

    @Override
    public int refreshWorkerInfo(XN631792Req req) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setCode(req.getCode());
        BeanUtils.copyProperties(req, workerInfo);
        return workerInfoDAO.updateWorkerInfoAboutPhone(workerInfo);
    }

    @Override
    public void refreshHandIdCardImage(String code, String handIdCardImage) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setCode(code);
        workerInfo.setHandIdCardImageUrl(handIdCardImage);
        workerInfoDAO.updateWorkerInfoHandIdCardImage(workerInfo);
    }

    @Override
    public void refreshAttendancePic(String code, String attendancePicture,
            String workerUploadStatus, String attendancePicUploadStatus) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setCode(code);
        workerInfo.setAttendancePicture(attendancePicture);
        // 更新状态
        workerInfo.setWorkerUploadStatus(workerUploadStatus);
        workerInfo.setWorkerPicUploadStatus(attendancePicUploadStatus);
        workerInfoDAO.updateWorkerInfoAttendancePic(workerInfo);
    }

    @Override
    public int refreshWorkerInfo(XN631793Req req) {
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(req.getCode());
        WorkerInfo workerInfo = workerInfoDAO.select(condition);
        Date birthDay = DateUtil.strToDate(req.getBirthday(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        workerInfo.setBirthday(birthDay);
        workerInfo
            .setGender(Integer.parseInt(EGender.checkExists(req.getGender())));
        BeanUtils.copyProperties(req, workerInfo);
        if (StringUtils.isNotBlank(req.getIsJoined())) {
            workerInfo.setIsJoined(Integer.parseInt(req.getIsJoined()));
        }
        if (StringUtils.isNotBlank(req.getHasBadMedicalHistory())) {
            workerInfo.setHasBadMedicalHistory(
                Integer.parseInt(req.getHasBadMedicalHistory()));
        }
        if (StringUtils.isNotBlank(req.getIsJoined())) {
            if (req.getIsJoined().equals(EIsNotType.IS.getCode())
                    && StringUtils.isNotBlank(req.getJoinedTime())) {
                workerInfo.setJoinedTime(DateUtil.strToDate(req.getJoinedTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING));
            } else {
                workerInfo.setIsJoined(0);
                workerInfo.setJoinedTime(null);

            }
        }

        if (StringUtils.isNotBlank(req.getStartDate())) {
            workerInfo.setStartDate(DateUtil.strToDate(req.getStartDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(req.getExpiryDate())) {
            workerInfo.setExpiryDate(DateUtil.strToDate(req.getExpiryDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(req.getPoliticsType())) {
            workerInfo.setPoliticsType(req.getPoliticsType());
        }
        workerInfo.setCreateDatetime(new Date(System.currentTimeMillis()));

        return workerInfoDAO.updateWorkerInfo(workerInfo);
    }

    @Override
    public String saveWorkerInfo(WorkerInfo workerInfo) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerInfo.getCode());
        workerInfo.setCode(code);
        workerInfoDAO.insert(workerInfo);
        return code;
    }

    @Override
    public String saveWorkerInfoByImport(XN631693ReqData data) {
        WorkerInfo workerInfo = new WorkerInfo();
        String code = null;
        // 插入基本信息到人员实名信息表
        BeanUtils.copyProperties(data, workerInfo);
        Date tempdate;
        try {
            tempdate = new SimpleDateFormat("yyyyMMdd")
                .parse(data.getIdCardNumber().substring(6, 14));
            String birthday = new SimpleDateFormat("yyyy-MM-dd")
                .format(tempdate);
            workerInfo.setBirthday(DateUtil.strToDate(birthday,
                DateUtil.FRONT_DATE_FORMAT_STRING));
            workerInfo.setGender(
                Integer.parseInt(data.getIdCardNumber().substring(16, 17))
                        % 2 == 0 ? 1 : 0);
            workerInfo.setName(data.getWorkerName());
            workerInfo
                .setBirthPlaceCode(data.getIdCardNumber().substring(0, 6));
            workerInfo.setWorkerType(data.getWorkType());
            code = OrderNoGenerater
                .generate(EGeneratePrefix.WorkerInfo.getCode());
            workerInfo.setCode(code);
            if (StringUtils.isNotBlank(data.getStartDate())) {
                Date strToDate = DateUtil.strToDate(data.getStartDate(),
                    "yyyy-MM-dd");
                //
                workerInfo.setStartDate(strToDate);
            }
            if (StringUtils.isNotBlank(data.getExpiryDate())) {
                Date toDate = DateUtil.strToDate(data.getStartDate(),
                    "yyyy-MM-dd");
                workerInfo.setExpiryDate(toDate);
            }
            workerInfo.setCreateDatetime(new Date(System.currentTimeMillis()));
            workerInfo.setIdCardType("01");
            workerInfoDAO.insert(workerInfo);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return code;
    }

    @Override
    public String refreshWorkerInfo(XN631797Req req) {
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(req.getCode());
        WorkerInfo workerInfo = workerInfoDAO.selectBriefWorkerInfo(condition);
        if (workerInfo != null) {
            BeanUtils.copyProperties(req, condition);
            workerInfo.setCreateDatetime(new Date(System.currentTimeMillis()));
            workerInfoDAO.updateWorkerInfo(workerInfo);
            return condition.getCode();
        }
        return null;
    }

}
