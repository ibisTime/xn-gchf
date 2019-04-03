package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerInfoDAO;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.WorkerInfo;
import com.cdkj.gchf.dto.req.XN631790Req;
import com.cdkj.gchf.dto.req.XN631791Req;
import com.cdkj.gchf.dto.req.XN631792Req;
import com.cdkj.gchf.dto.req.XN631793Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIsNotType;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;

@Component
public class WorkerInfoBOImpl extends PaginableBOImpl<WorkerInfo>
        implements IWorkerInfoBO {

    @Autowired
    private IWorkerInfoDAO workerInfoDAO;

    @Override
    public boolean isWorkerInfoExist(String code) {
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(code);
        if (workerInfoDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveWorkerInfo(XN631790Req req) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerInfo.getCode());
        WorkerInfo workerInfo = new WorkerInfo();
        BeanUtils.copyProperties(req, workerInfo);
        workerInfo.setBirthPlaceCode(req.getIdCardNumber().substring(0, 6));
        workerInfo.setName(req.getName());
        workerInfo.setNation(req.getNation());
        Date birthday = DateUtil.strToDate(req.getBirthday(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        workerInfo.setBirthday(birthday);

        workerInfo.setAddress(req.getAddress());
        Date startDate = DateUtil.strToDate(req.getStartDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        workerInfo.setStartDate(startDate);
        Date enpiryDate = DateUtil.strToDate(req.getExpiryDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        workerInfo.setExpiryDate(enpiryDate);

        workerInfo.setHeadImageUrl(req.getHeadImageUrl());
        workerInfo.setIdCardNumber(req.getIdCardNumber());
        workerInfo.setIdCardType(req.getIdCardType());
        workerInfo.setGender(Integer.parseInt(req.getGender()));
        workerInfo.setPoliticsType(req.getPoliticsType());
        workerInfo.setCultureLevelType(req.getCultureLevelType());

        if (req.getIsJoined() != null) {
            if (req.getJoinedTime() != null) {
                Date joinTime = DateUtil.strToDate(req.getJoinedTime(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                workerInfo.setJoinedTime(joinTime);
            }
            workerInfo.setIsJoined(Integer
                .parseInt(EIsNotType.getIsNotDictCode(req.getIsJoined())));
        }
        workerInfo.setSpecialty(req.getSpecialty());
        workerInfo.setCode(code);
        workerInfoDAO.insert(workerInfo);
        return code;
    }

    @Override
    public int removeWorkerInfo(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            WorkerInfo data = new WorkerInfo();
            data.setCode(code);
            count = workerInfoDAO.delete(data);
        }
        return count;
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

    @Override
    public WorkerInfo getWorkerInfo(String code) {
        WorkerInfo data = null;
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(code);
        data = workerInfoDAO.select(condition);
        if (data == null) {
            throw new BizException("XN631806", "信息不存在");
        }
        return data;
    }

    @Override
    public WorkerInfo getWorkerInfoByCondition(WorkerInfo workerInfo) {
        return workerInfoDAO.select(workerInfo);
    }

    @Override
    public WorkerInfo getWorkerInfoByCelephone(String phone) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setCellPhone(phone);
        return workerInfoDAO.select(workerInfo);
    }

    @Override
    public WorkerInfo getWorkerInfoByIdCardNumber(String idCardNumber) {
        WorkerInfo workerInfo = new WorkerInfo();
        workerInfo.setIdCardNumber(idCardNumber);
        return workerInfoDAO.select(workerInfo);
    }

    @Override
    public String saveWorkerInfo(WorkerInfo workerInfo) {
        String code = null;
        int insert = workerInfoDAO.insert(workerInfo);
        if (insert != 1) {
            throw new BizException("人员实名制基本信息保存失败");
        }
        code = OrderNoGenerater.generate(EGeneratePrefix.WorkerInfo.getValue());
        return code;
    }

    @Override
    public int refreshWorkerInfo(XN631791Req req) {
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(req.getCode());
        WorkerInfo select = workerInfoDAO.select(condition);
        if (select.getHandIdCardImageUrl()
            .equals(req.getHandIdCardImageUrl())) {
            throw new BizException("手持身份证照片已上传");
        }
        if (select.getPositiveIdCardImageUrl()
            .equals(req.getPositiveIdCardImageUrl())) {
            throw new BizException("正面照已上传");
        }
        if (select.getNegativeIdCardImageUrl()
            .equals(req.getNegativeIdCardImageUrl())) {
            throw new BizException("反面照已上传");
        }
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
    public int refreshWorkerInfo(XN631793Req req) {
        WorkerInfo condition = new WorkerInfo();
        condition.setCode(req.getCode());
        WorkerInfo select = workerInfoDAO.select(condition);
        select.setName(req.getName());
        select.setAddress(req.getAddress());
        Date birthDay = DateUtil.strToDate(req.getBirthday(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        select.setBirthday(birthDay);
        select.setCellPhone(req.getCellPhone());
        select.setEduLevel(req.getCultureLevelType());
        select.setGrantOrg(req.getGrantOrg());
        select.setGender(Integer.parseInt(req.getGender()));
        select.setHeadImageUrl(req.getHeadImageUrl());
        select.setIdCardNumber(req.getIdCardNumber());
        select.setIdCardType(req.getIdCardType());
        BeanUtils.copyProperties(req, select);
        if (StringUtils.isNotBlank(req.getIsJoined())) {
            select.setIsJoined(Integer.parseInt(req.getIsJoined()));
        }
        if (StringUtils.isNotBlank(req.getHasBadMedicalHistory())) {
            select.setHasBadMedicalHistory(
                Integer.parseInt(req.getHasBadMedicalHistory()));
        }
        if (StringUtils.isNotBlank(req.getSpecialty())) {
            select.setSpecialty(req.getSpecialty());
        }
        if (StringUtils.isNotBlank(req.getMaritalStatus())) {
            select.setMaritalStatus(req.getMaritalStatus());
        }
        if (StringUtils.isNotBlank(req.getJoinedTime())) {
            select.setJoinedTime(DateUtil.strToDate(req.getJoinedTime(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(req.getUrgentLinkMan())) {
            select.setUrgentLinkMan(req.getUrgentLinkMan());
        }
        if (StringUtils.isNotBlank(req.getUrgentLinkManPhone())) {
            select.setUrgentLinkManPhone(req.getUrgentLinkManPhone());
        }
        if (StringUtils.isNotBlank(req.getHandIdCardImageUrl())) {
            select.setHandIdCardImageUrl(req.getHandIdCardImageUrl());
        }
        return workerInfoDAO.updateWorkerInfo(select);
    }

}
