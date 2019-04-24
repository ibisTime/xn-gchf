package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.ICorpBasicinfoAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631250Req;
import com.cdkj.gchf.dto.req.XN631251Req;
import com.cdkj.gchf.dto.req.XN631900Req;
import com.cdkj.gchf.dto.req.XN631901Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;

@Service
public class CorpBasicinfoAOImpl implements ICorpBasicinfoAO {

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addCorpBasicinfo(XN631250Req req) {

        String userId = req.getUserId();
        User briefUser = userBO.getBriefUser(userId);
        // if (briefUser.getType().equals(EUserKind.Owner.getCode())) {
        // CorpBasicinfo corpBasicInfoByCorpProject = corpBasicinfoBO
        // .getCorpBasicInfoByCorpProject(briefUser.getOrganizationCode(),
        // req.getCorpCode());
        //
        // if (corpBasicInfoByCorpProject == null) {
        // CorpBasicinfo corpBasicinfoByCorp = corpBasicinfoBO
        // .getCorpBasicinfoByCorp(req.getCorpCode());
        // corpBasicinfoByCorp
        // .setProjectCode(briefUser.getOrganizationCode());
        // String code = OrderNoGenerater
        // .generate(EGeneratePrefix.CorpBasicinfo.getCode());
        // corpBasicinfoByCorp.setCode(code);
        // Project project = projectBO
        // .getProject(briefUser.getOrganizationCode());
        // corpBasicinfoByCorp.setProjectName(project.getName());
        // corpBasicinfoBO.insertCorpBasicinfo(corpBasicinfoByCorp);
        // return code;
        // } else {
        // throw new BizException("XN631250", "企业基本信息已存在，请勿重复添加");
        // }
        //
        // }
        if (null != corpBasicinfoBO.getCorpBasicinfoByCorp(req.getCorpCode())) {
            throw new BizException("XN631250", "企业基本信息已存在，请勿重复添加");
        }

        String code = corpBasicinfoBO.saveCorpBasicinfo(req);

        return code;
    }

    @Override
    public void editCorpBasicinfo(XN631251Req req) {
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO
            .getCorpBasicinfo(req.getCode());

        if (EUploadStatus.UPLOAD_UNEDITABLE.getCode()
            .equals(corpBasicinfo.getUploadStatus())) {
            throw new BizException("XN631251", "当前状态下不可修改");
        }

        if (!req.getCorpCode().equals(corpBasicinfo.getCorpCode())
                && null != corpBasicinfoBO
                    .getCorpBasicinfoByCorp(req.getCorpCode())) {
            throw new BizException("XN631250", "统一社会信用代码已存在，请勿重复添加");
        }

        corpBasicinfoBO.refreshCorpBasicinfo(req);
    }

    @Override
    public int dropCorpBasicinfo(String code) {
        CorpBasicinfo corpBasicinfo = corpBasicinfoBO.getCorpBasicinfo(code);

        if (EUploadStatus.UPLOAD_UNEDITABLE.getCode()
            .equals(corpBasicinfo.getUploadStatus())) {
            throw new BizException("XN631250", "当前状态下不可删除");
        }

        return corpBasicinfoBO.removeCorpBasicinfo(code);
    }

    @Override
    public void uploadCorpBasicinfo(List<String> codeList, String userId) {

        User operator = userBO.getBriefUser(userId);

        ProjectConfig defaultProjectConfig = projectConfigBO
            .getDefaultProjectConfig();
        if (null == defaultProjectConfig) {
            throw new BizException("XN631253", "不存在已配置的项目，无法上传");
        }

        for (String codeReq : codeList) {
            CorpBasicinfo corpBasicinfo = corpBasicinfoBO
                .getCorpBasicinfo(codeReq);

            if (EUploadStatus.UPLOAD_UNEDITABLE.getCode()
                .equals(corpBasicinfo.getUploadStatus()))
                continue;

            corpBasicinfo.setLegalManIdcardNumber(
                AesUtils.encrypt(corpBasicinfo.getLegalManIdcardNumber(),
                    defaultProjectConfig.getSecret()));
            System.out.println(corpBasicinfo.getLegalManIdcardNumber());
            // 上传企业信息
            String resString = GovConnecter.getGovData("Corp.Upload",
                JSONObject
                    .toJSONStringWithDateFormat(corpBasicinfo, "yyyy-MM-dd")
                    .toString(),
                defaultProjectConfig.getProjectCode(),
                defaultProjectConfig.getSecret());

            // 添加操作日志
            String logCode = operateLogBO.saveOperateLog(
                EOperateLogRefType.CorpBasicinfo.getCode(),
                corpBasicinfo.getCode(),
                EOperateLogOperate.UploadCorpBasicinfo.getValue(), operator,
                null);

            // 添加到上传状态更新队列
            AsyncQueueHolder.addSerial(resString, defaultProjectConfig,
                "corpBasicinfoBO", corpBasicinfo.getCode(),
                EUploadStatus.UPLOAD_UNEDITABLE.getCode(), logCode);

        }
    }

    @Override
    public void uploadCorpBasicinfo(XN631900Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631900", "该项目未配置，无法上传");
        }

        corpBasicinfoBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<CorpBasicinfo> queryCorpBasicinfo(XN631901Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631900", "该项目未配置，无法查询");
        }

        return corpBasicinfoBO.doQuery(req, projectConfig);
    }

    @Override
    public Paginable<CorpBasicinfo> queryCorpBasicinfoPage(String userId,
            int start, int limit, CorpBasicinfo condition) {
        // User briefUser = userBO.getBriefUser(userId);
        // if (briefUser != null) {
        // if (briefUser.getType().equals(EUserKind.Owner.getCode())) {
        // // 业主端
        // String organizationCode = briefUser.getOrganizationCode();
        // condition.setProjectCode(organizationCode);
        // condition.setGroupBycorpcode(null);
        // }
        //
        // }

        return corpBasicinfoBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CorpBasicinfo> queryCorpBasicinfoList(CorpBasicinfo condition) {
        return corpBasicinfoBO.queryCorpBasicinfoList(condition);
    }

    @Override
    public CorpBasicinfo getCorpBasicinfo(String code) {
        return corpBasicinfoBO.getCorpBasicinfo(code);
    }

    // @Override
    // public void bindingProject(XN631254Req req) {
    // CorpBasicinfo corpBasicinfo = corpBasicinfoBO
    // .getCorpBasicinfo(req.getCode());
    // List<String> projectCode = req.getProjectCodeList();
    // for (String projectcode : projectCode) {
    // if (corpBasicinfo.getProjectCode() != null
    // && corpBasicinfo.getProjectCode().equals(projectcode)) {
    // continue;
    // }
    // CorpBasicinfo copyCorpBasicinfo = corpBasicinfo;
    // copyCorpBasicinfo.setCode(OrderNoGenerater
    // .generate(EGeneratePrefix.CorpBasicinfo.getCode()));
    // copyCorpBasicinfo.setProjectCode(projectcode);
    // Project project = projectBO.getProject(projectcode);
    // copyCorpBasicinfo.setProjectName(project.getName());
    // corpBasicinfoBO.insertCorpBasicinfo(copyCorpBasicinfo);
    // }
    // }
}
