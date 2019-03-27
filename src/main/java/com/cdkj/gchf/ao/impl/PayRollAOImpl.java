package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.google.gson.JsonObject;

@Service
public class PayRollAOImpl implements IPayRollAO {

    @Autowired
    private IPayRollBO payRollBO;

    @Autowired
    private IPayRollDetailBO payRollDetailBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Transactional
    @Override
    public String addPayRoll(XN631770Req data) {
        String code = null;
        PayRoll payRoll = new PayRoll();
        BeanUtils.copyProperties(data, payRoll);
        code = OrderNoGenerater.generate(EGeneratePrefix.PayRoll.getCode());
        payRoll.setCode(code);
        payRollBO.savePayRoll(data);
        payRollDetailBO.savePayRollDetail(data.getPayRollCode(),
            data.getDetailList());
        return code;
    }

    @Override
    public int editPayRoll(XN631772Req req) {
        PayRoll payRoll = new PayRoll();
        BeanUtils.copyProperties(req, payRoll);
        String corpCode = req.getCorpCode();
        List<XN631770ReqDetail> detailList = req.getDetailList();
        if (detailList != null && detailList.size() != 0) {
            for (XN631770ReqDetail xn631770ReqDetail : detailList) {
                PayRollDetail payRollDetail = new PayRollDetail();
                payRollDetail.setPayRollCode(corpCode);
                BeanUtils.copyProperties(xn631770ReqDetail, payRollDetail);
                payRollDetailBO.updatePayRollDetail(payRollDetail);
            }
        }
        return payRollBO.refreshPayRoll(payRoll);
    }

    @Override
    public int dropPayRoll(String code) {
        PayRoll payRoll = new PayRoll();
        payRoll.setCode(code);
        List<PayRoll> queryPayRollList = payRollBO.queryPayRollList(payRoll);
        String payRollCode = queryPayRollList.get(0).getPayRollCode();
        payRollDetailBO.deletePayRollDetail(payRollCode);
        return payRollBO.removePayRoll(code);
    }

    @Override
    public void uploadPayRoll(XN631920Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631920", "该项目未配置，无法上传");
        }

        payRollBO.doUpload(req, projectConfig);
    }

    @Override
    public Paginable<PayRollDetail> queryPayRoll(XN631921Req req) {
        ProjectConfig projectConfig = projectConfigBO
            .getProjectConfigByProject(req.getProjectCode());

        if (null == projectConfig) {
            throw new BizException("XN631921", "该项目未配置，无法查询");
        }

        return payRollBO.doQuery(req, projectConfig);
    }

    @Override
    public Paginable<PayRoll> queryPayRollPage(int start, int limit,
            PayRoll condition) {
        Paginable<PayRoll> page = payRollBO.getPaginable(start, limit,
            condition);

        List<PayRoll> payRollList = page.getList();
        if (CollectionUtils.isNotEmpty(payRollList)) {
            for (PayRoll payRoll : payRollList) {
                payRoll.setPayRollDetailList(payRollDetailBO
                    .queryListByPayRollDetail(payRoll.getCode()));
            }
        }

        return page;
    }

    @Override
    public List<PayRoll> queryPayRollList(PayRoll condition) {
        return payRollBO.queryPayRollList(condition);
    }

    public void uploadPayRollList(String userId, List<String> codeList) {
        User user = userBO.getBriefUser(userId);
        for (String code : codeList) {

            PayRollDetail payRollDetail = payRollDetailBO
                .getPayRollDetail(code);
            String payRollCode = payRollDetail.getPayRollCode();
            List<PayRollDetail> payRollDetailByPayRollCode = payRollDetailBO
                .getPayRollDetailByPayRollCode(payRollCode);

            // 拿到payroll数据

            for (PayRollDetail tempPayRollDetail : payRollDetailByPayRollCode) {
                PayRoll conditionPayRoll = new PayRoll();
                conditionPayRoll.setCode(tempPayRollDetail.getPayRollCode());
                PayRoll payRoll = payRollBO
                    .getPayRollByCondition(conditionPayRoll);
                if (payRoll == null) {
                    throw new BizException("XN631774", "工资单不存在");
                }
                TeamMaster tempteamMaster = new TeamMaster();
                tempteamMaster.setCode(null);
                tempteamMaster.setTeamSysNo(payRoll.getTeamSysNo());
                TeamMaster teamMaster = teamMasterBO
                    .getTeamMasterByCondition(tempteamMaster);
                System.out.println(teamMaster);

                ProjectConfig projectConfig = projectConfigBO
                    .getProjectConfigByLocal(payRoll.getProjectCode());
                if (projectConfig == null) {
                    throw new BizException("XN631774", "项目未部署");
                }

                // 拿到工资详情code
                JsonObject uploadRequestJsontoPlantform = payRollDetailBO
                    .getUploadRequestJsontoPlantform(payRoll, teamMaster,
                        projectConfig, tempPayRollDetail);
                System.out
                    .println("===" + uploadRequestJsontoPlantform.toString());
                String resString = GovConnecter.getGovData("Payroll.Add",
                    uploadRequestJsontoPlantform.toString(),
                    projectConfig.getProjectCode(), projectConfig.getSecret());

                String log = operateBO.saveOperateLog(
                    EOperateLogRefType.PayRoll.getCode(),
                    uploadRequestJsontoPlantform.toString(),
                    EOperateLogOperate.UploadPayRoll.getValue(), user, null);

                AsyncQueueHolder.addSerial(resString, projectConfig,
                    "payRollDetailBO", code,
                    EUploadStatus.UPLOAD_UNEDITABLE.getValue(), log);
            }
        }

    }

    @Override
    public PayRoll getPayRoll(String code) {
        return payRollBO.getPayRoll(code);
    }

    @Override
    public void refreshPayRollCodeByLocal(String code, String payRollCode) {
        PayRoll payRoll = new PayRoll();
        payRoll.setCode(code);
        payRoll.setPayRollCode(payRollCode);
        payRollBO.refreshPayRoll(payRoll);
    }

}
