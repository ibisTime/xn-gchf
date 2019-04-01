package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.bo.ICorpBasicinfoBO;
import com.cdkj.gchf.bo.IOperateLogBO;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectCorpInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerInfoBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631773Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.http.JsonUtils;
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
    private IProjectCorpInfoBO projectCorpInfoBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IWorkerInfoBO workerInfoBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Transactional
    @Override
    public String addPayRoll(XN631770Req data) {
        TeamMaster teamMaster = teamMasterBO.getTeamMaster(data.getTeamSysNo());
        if (teamMaster == null) {
            throw new BizException("XN631700", "所在班组不存在");
        }
        CorpBasicinfo basicinfoByCorp = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());
        if (basicinfoByCorp == null) {
            throw new BizException("XN631700", "企业信息不存在");
        }
        String code = payRollBO.savePayRoll(data);
        payRollDetailBO.savePayRollDetail(code, data.getDetailList());
        return code;
    }

    @Override
    public int editPayRoll(XN631772Req req) {
        PayRoll payRoll = payRollBO.getPayRoll(req.getCode());
        if (payRoll == null) {
            throw new BizException("XN631772", "工资单不存在");
        }
        return payRollDetailBO.updatePayRollDetail(req);
    }

    @Transactional
    @Override
    public int dropPayRoll(String code) {

        PayRoll payroll = payRollBO.getPayRoll(code);
        List<PayRollDetail> queryListByPayRollDetail = payRollDetailBO
            .queryListByPayRollDetail(payroll.getCode());
        // 不可删除的详情工资单主键集合
        List<String> uploadedNationPlantformCodes = new ArrayList<>();
        for (PayRollDetail detail : queryListByPayRollDetail) {
            // 删除工资单详情
            if (detail.getUploadStatus() != null & detail.getUploadStatus()
                .equals(EUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
                uploadedNationPlantformCodes.add(detail.getCode());
                continue;
            }
            payRollDetailBO.deletePayRollDetail(detail.getCode());
        }
        if (!CollectionUtils.isEmpty(uploadedNationPlantformCodes)) {
            throw new BizException("XN631771",
                JsonUtils.object2Json(uploadedNationPlantformCodes)
                        + "已上传不可删除");
        }

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
                TeamMaster tempteamMaster = new TeamMaster();
                tempteamMaster.setCode(null);
                tempteamMaster.setTeamSysNo(payRoll.getTeamSysNo());
                TeamMaster teamMaster = teamMasterBO
                    .getTeamMasterByCondition(tempteamMaster);

                ProjectConfig projectConfig = projectConfigBO
                    .getProjectConfigByLocal(payRoll.getProjectCode());
                if (projectConfig == null) {
                    throw new BizException("XN631774", "不存在已配置的项目，无法上传");
                }

                // 拿到工资详情code
                JsonObject uploadRequestJsontoPlantform = payRollDetailBO
                    .getUploadRequestJsontoPlantform(payRoll, teamMaster,
                        projectConfig, tempPayRollDetail);

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

    @Transactional
    @Override
    public void importPayRollCodeList(XN631773Req req) {
        User user = userBO.getBriefUser(req.getUserId());
        ProjectCorpInfo corpInfoByCorpCode = projectCorpInfoBO
            .getProjectCorpInfoByCorpCode(req.getProjectCode());
        if (corpInfoByCorpCode == null) {
            throw new BizException("XN631773", "参建单位不存在");
        }
        // String payMonth = null;
        // List<XN631773ReqData> dateList = req.getDateList();
        // for (XN631773ReqData xn631773ReqData : dateList) {
        // // 判断班组信息
        // // 工资单-工资单详情 可是月份在子参数里面怎么提到外面呢
        // TeamMaster condition = new TeamMaster();
        // condition.setCorpCode(corpInfoByCorpCode.getCorpCode());
        // condition.setTeamName(xn631773ReqData.getTeamName());
        // TeamMaster masterByCondition = teamMasterBO
        // .getTeamMasterByCondition(condition);
        //
        // if (masterByCondition == null) {
        // throw new BizException("XN631773", "班组信息不存在");
        // }
        //
        // WorkerInfo workerByIdCardNumber = projectWorkerBO
        // .getProjectWorkerByIdCardNumber(
        // xn631773ReqData.getIdCardNumber());
        //
        // if (payMonth != null
        // & payMonth.equals(xn631773ReqData.getPayMonth())) {
        // // 同一月份的工资单
        // PayRoll payRoll = new PayRoll();
        // payRoll.setProjectCode(req.getProjectCode());
        // payRoll.setCorpCode(corpInfoByCorpCode.getCorpCode());
        // payRoll.setCorpName(corpInfoByCorpCode.getCorpName());
        // payRoll.setTeamSysNo(masterByCondition.getCode());
        // PayRoll selectPayRoll = new PayRoll();
        // PayRoll payRollByCondition = payRollBO
        // .getPayRollByCondition(selectPayRoll);
        // if (payRollByCondition != null) {
        // // 存在工资单 新增工资单详情
        //
        // PayRollDetail payRollDetail = new PayRollDetail();
        // BeanUtils.copyProperties(xn631773ReqData, payRollDetail);
        // payRollDetail.setPayRollCode(payRollByCondition.getCode());
        // payRollDetail.setWorkerName(workerByIdCardNumber.getName());
        // payRollDetail
        // .setIdcardType(workerByIdCardNumber.getIdCardType());
        // payRollDetail.setDays(xn631773ReqData.getDays());
        // payRollDetail.setWorkHours(xn631773ReqData.getWorkHours());
        // if (payRollDetail.getIsBackPay() != null) {
        // payRollDetail
        // .setIsBackPay(xn631773ReqData.getIsBackPay());
        // }
        // payRollDetail
        // .setBalanceDate(xn631773ReqData.getBackPayMonth());
        // String code = payRollDetailBO
        // .savePayRollDetail(payRollDetail);
        // operateBO.saveOperateLog(
        // EOperateLogRefType.PayRollDetail.getCode(), code,
        // "导入工资单", user, null);
        // }
        // // 不存在工资单
        // String code = payRollBO.savePayRoll(payRoll);
        // payMonth = xn631773ReqData.getPayMonth();
        //
        // }
        // payMonth = xn631773ReqData.getPayMonth();
        // }

    }

}
