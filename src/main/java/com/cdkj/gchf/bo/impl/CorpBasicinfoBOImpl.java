package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ICorpBasicinfoDAO;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;
import com.cdkj.gchf.dto.req.XN631900Req;
import com.cdkj.gchf.dto.req.XN631901Req;
import com.cdkj.gchf.enums.ECorpBasicUploadStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;

@Component
public class CorpBasicinfoBOImpl extends PaginableBOImpl<CorpBasicinfo>
        implements ICorpBasicinfoBO {

    @Autowired
    private ICorpBasicinfoDAO corpBasicinfoDAO;

    @Override
    public String saveCorpBasicinfo(XN631250Req req) {
        CorpBasicinfo corpBasicinfo = new CorpBasicinfo();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CorpBasicinfo.getCode());

        // User briefUser = userBO.getBriefUser(req.getUserId());
        // if (briefUser.getType().equals(EUserKind.Owner.getCode())) {
        // // 项目端添加添加项目编号和名称
        // Project project = projectBO
        // .getProject(briefUser.getOrganizationCode());
        // corpBasicinfo.setProjectCode(briefUser.getOrganizationCode());
        // corpBasicinfo.setProjectName(project.getName());
        // }

        BeanUtils.copyProperties(req, corpBasicinfo);
        corpBasicinfo.setCode(code);
        if (StringUtils.isNotBlank(req.getRegCapital())) {
            corpBasicinfo.setRegCapital(new BigDecimal(req.getRegCapital()));
        }
        if (StringUtils.isNotBlank(req.getFactRegCapital())) {
            corpBasicinfo
                .setFactRegCapital(new BigDecimal(req.getFactRegCapital()));
        }
        if (StringUtils.isNotBlank(req.getRegisterDate())) {
            corpBasicinfo.setRegisterDate(DateUtil.strToDate(
                req.getRegisterDate(), DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(req.getEstablishDate())) {
            corpBasicinfo.setEstablishDate(DateUtil.strToDate(
                req.getEstablishDate(), DateUtil.FRONT_DATE_FORMAT_STRING));
        }

        corpBasicinfo
            .setUploadStatus(ECorpBasicUploadStatus.TO_UPLOAD.getCode());

        corpBasicinfoDAO.insert(corpBasicinfo);

        return code;
    }

    @Override
    public String insertCorpBasicinfo(CorpBasicinfo corpBasicinfo) {
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CorpBasicinfo.getCode());
        corpBasicinfo.setCode(code);
        corpBasicinfoDAO.insert(corpBasicinfo);
        return code;
    }

    @Override
    public int removeCorpBasicinfo(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            CorpBasicinfo data = new CorpBasicinfo();
            data.setCode(code);
            count = corpBasicinfoDAO.delete(data);
        }
        return count;
    }

    @Override
    public void refreshCorpBasicinfo(XN631251Req data) {

        CorpBasicinfo corpBasicinfo = new CorpBasicinfo();
        BeanUtils.copyProperties(data, corpBasicinfo);

        if (StringUtils.isNotBlank(data.getRegCapital())) {
            corpBasicinfo.setRegCapital(new BigDecimal(data.getRegCapital()));
        }
        if (StringUtils.isNotBlank(data.getFactRegCapital())) {
            corpBasicinfo
                .setFactRegCapital(new BigDecimal(data.getFactRegCapital()));
        }
        if (StringUtils.isNotBlank(data.getRegisterDate())) {
            corpBasicinfo.setRegisterDate(DateUtil.strToDate(
                data.getRegisterDate(), DateUtil.FRONT_DATE_FORMAT_STRING));
        }
        if (StringUtils.isNotBlank(data.getEstablishDate())) {
            corpBasicinfo.setEstablishDate(DateUtil.strToDate(
                data.getEstablishDate(), DateUtil.FRONT_DATE_FORMAT_STRING));
        }

        corpBasicinfoDAO.update(corpBasicinfo);
    }

    @Override
    public void refreshUploadStatus(String code, String uploadStatus) {
        CorpBasicinfo corpBasicinfo = new CorpBasicinfo();

        corpBasicinfo.setCode(code);
        corpBasicinfo.setUploadStatus(uploadStatus);

        corpBasicinfoDAO.updateUploadStatus(corpBasicinfo);
    }

    @Override
    public void doUpload(XN631900Req req, ProjectConfig projectConfig) {

        if (StringUtils.isNotBlank(req.getLegalManIdcardNumber())) {
            req.setLegalManIdcardNumber(AesUtils.encrypt(
                req.getLegalManIdcardNumber(), projectConfig.getSecret()));
        }

        CorpBasicinfo corpBasicinfo = new CorpBasicinfo();
        BeanUtils.copyProperties(req, corpBasicinfo);

        String data = JSONObject.toJSON(corpBasicinfo).toString();

        GovConnecter.getGovData("Corp.Upload", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public Paginable<CorpBasicinfo> doQuery(XN631901Req req,
            ProjectConfig projectConfig) {
        CorpBasicinfo corpBasicinfo = new CorpBasicinfo();
        BeanUtils.copyProperties(req, corpBasicinfo);

        String data = JSONObject.toJSON(corpBasicinfo).toString();

        String queryString = GovConnecter.getGovData("Corp.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();
        replaceMap.put("capitalCurrencyType", "capitalCurrencyTypeName");

        Paginable<CorpBasicinfo> page = GovUtil.parseGovPage(req.getPageIndex(),
            req.getPageSize(), queryString, replaceMap, CorpBasicinfo.class);

        return page;

    }

    @Override
    public List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition) {
        return corpBasicinfoDAO.selectList(condition);
    }

    @Override
    public CorpBasicinfo getCorpBasicinfo(String code) {
        CorpBasicinfo data = null;
        if (StringUtils.isNotBlank(code)) {
            CorpBasicinfo condition = new CorpBasicinfo();
            condition.setCode(code);
            data = corpBasicinfoDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "企业基本信息编号不存在");
            }
        }
        return data;
    }

    @Override
    public CorpBasicinfo getCorpBasicinfoByCorp(String corpCode) {
        CorpBasicinfo condition = new CorpBasicinfo();
        condition.setCorpCode(corpCode);
        List<CorpBasicinfo> selectList = corpBasicinfoDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(selectList)) {
            return selectList.get(0);
        }
        return corpBasicinfoDAO.select(condition);
    }

}
