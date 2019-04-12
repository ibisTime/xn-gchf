package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectCorpInfo;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631812Req;
import com.cdkj.gchf.dto.req.XN631812ReqData;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.AsyncQueueHolder;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.http.JsonUtils;
import com.google.gson.JsonArray;
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
    private IOperateLogBO operateLogBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

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
        payRollDetailBO.savePayRollDetail(data.getTeamSysNo(),
            data.getProjectCode(), code, data.getPayMonth(),
            data.getDetailList());

        User briefUser = userBO.getBriefUser(data.getUserId());
        operateLogBO.saveOperateLog(EOperateLogRefType.PayRoll.getCode(), code,
            EOperateLogRefType.PayRoll.getValue(), briefUser, "新增工资单" + code);

        return code;
    }

    @Override
    public int editPayRoll(XN631772Req req) {
        PayRoll payRoll = payRollBO.getPayRoll(req.getCode());
        if (payRoll == null) {
            throw new BizException("XN631772", "工资单不存在");
        }
        return payRollBO.updatePayRollDetail(req);
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

    /**
     * 
     * <p>Title: uploadPayRollList</p>   
     * <p>Description: 上传工资单详情 </p>   
     */
    public void uploadPayRollList(String userId, List<String> codeList) {
        User user = userBO.getBriefUser(userId);

        for (String code : codeList) {
            PayRollDetail payRollDetail = payRollDetailBO
                .getPayRollDetail(code);
            String payRollCode = payRollDetail.getPayRollCode();
            PayRoll payRoll = payRollBO.getPayRoll(payRollCode);
            ProjectConfig projectConfigByLocal = projectConfigBO
                .getProjectConfigByLocal(payRoll.getProjectCode());
            if (projectConfigByLocal == null) {
                continue;
            }
            String json = getRequestJsonToPlantform(payRoll, payRollDetail,
                projectConfigByLocal);
            String resString = GovConnecter.getGovData("Payroll.Add", json,
                projectConfigByLocal.getProjectCode(),
                projectConfigByLocal.getSecret());
            String log = operateLogBO.saveOperateLog(
                EOperateLogRefType.PayRollDetail.getCode(), code,
                EOperateLogOperate.UploadPayRoll.getValue(), user, null);

            AsyncQueueHolder.addSerial(resString, projectConfigByLocal,
                "payRollDetailBO", code,
                EUploadStatus.UPLOAD_UNEDITABLE.getCode(), log);
        }

    }

    /**
     * 
     * @Description: 获取请求国家平台的Json
     * @param: @param payRoll
     * @param: @param payRollDetail
     * @param: @return      
     * @return: String      
     * @throws
     */
    private String getRequestJsonToPlantform(PayRoll payRoll,
            PayRollDetail payRollDetail, ProjectConfig projectconfig) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        ProjectConfig projectConfigByLocal = projectConfigBO
            .getProjectConfigByLocal(payRoll.getProjectCode());
        // projectCode
        jsonObject.addProperty("projectCode",
            projectConfigByLocal.getProjectCode());
        // corpCode corpName
        ProjectCorpInfo projectCorpInfo = projectCorpInfoBO.getProjectCorpInfo(
            projectconfig.getLocalProjectCode(), payRoll.getCorpCode());
        projectCorpInfo.getCorpCode();
        jsonObject.addProperty("corpCode", projectCorpInfo.getCorpCode());
        jsonObject.addProperty("corpName", projectCorpInfo.getCorpName());
        // teamMasterNo
        TeamMaster teamMaster = teamMasterBO
            .getTeamMaster(payRoll.getTeamSysNo());
        if (StringUtils.isBlank(teamMaster.getTeamSysNo())) {
            throw new BizException("班组信息未上传");
        }
        jsonObject.addProperty("teamSysNo", teamMaster.getTeamSysNo());
        // PayMonth
        String payMonth = DateUtil.dateToStr(payRoll.getPayMonth(), "yyyy-MM");
        jsonObject.addProperty("PayMonth", payMonth);
        // payRoll data
        JsonObject payRollData = new JsonObject();
        BeanUtils.copyProperties(payRollDetail, payRollData);
        payRollData.addProperty("payBankName", payRollDetail.getPayBankName());
        payRollData.addProperty("totalPayAmount",
            payRollDetail.getTotalPayAmount());
        payRollData.addProperty("actualAmount",
            payRollDetail.getActualAmount());
        payRollData.addProperty("isBackPay", payRollDetail.getIsBackPay());
        if (payRollDetail.getBalanceDate() != null) {
            String balanceDate = DateUtil.dateToStr(
                payRollDetail.getBalanceDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            payRollData.addProperty("balanceDate", balanceDate);
        }
        payRollData.addProperty("idCardType", payRollDetail.getIdcardType());
        payRollData.addProperty("idCardNumber", AesUtils.encrypt(
            payRollDetail.getIdcardNumber(), projectconfig.getSecret()));
        payRollData.addProperty("payBankCode", payRollDetail.getPayBankCode());
        payRollData.addProperty("payRollBankCode",
            payRollDetail.getPayRollBankCode());
        payRollData.addProperty("payRollBankName",
            payRollDetail.getPayRollBankName());
        payRollData.addProperty("payRollBankCardNumber",
            AesUtils.encrypt(payRollDetail.getPayRollBankCardNumber(),
                projectconfig.getSecret()));
        payRollData.addProperty("payBankCardNumber", AesUtils.encrypt(
            payRollDetail.getPayBankCardNumber(), projectconfig.getSecret()));
        jsonArray.add(payRollData);
        jsonObject.add("detailList", jsonArray);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();

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
    public void importPayRollCodeList(XN631812Req req) {
        User user = userBO.getBriefUser(req.getUpdater());
        List<String> errorCode = new ArrayList<>();
        ProjectConfig configByProject = projectConfigBO
            .getProjectConfigByLocal(req.getProjectCode());

        if (configByProject == null) {
            throw new BizException("XN631773", "项目未部署");
        }
        // excel字段中有工资单信息和明细信息 两张表操作
        List<XN631812ReqData> dateList = req.getDateList();
        for (XN631812ReqData xn631773ReqData : dateList) {

            TeamMaster teamMasterByCondition = teamMasterBO
                .getTeamMasterByProject(req.getProjectCode(),
                    xn631773ReqData.getTeamName(),
                    xn631773ReqData.getCorpCode());

            if (teamMasterByCondition == null) {
                errorCode.add("班组信息不存在" + xn631773ReqData.getTeamName());
                continue;
            }
            // 不存在相关工资单时相关联的工资单
            String payRollCode = null;
            PayRoll payRoll = payRollBO
                .getPayRollByCorpCodeAndTeamSysNoAndProjectCode(
                    xn631773ReqData.getCorpCode(),
                    teamMasterByCondition.getCode(), req.getProjectCode(),
                    xn631773ReqData.getPayMonth());
            if (payRoll == null) {

                PayRoll payRollcondition = new PayRoll();
                payRollcondition.setCorpCode(xn631773ReqData.getCorpCode());
                payRollcondition.setTeamSysNo(teamMasterByCondition.getCode());
                payRollcondition.setProjectCode(req.getProjectCode());
                payRollcondition.setPayMonth(
                    DateUtil.strToDate(xn631773ReqData.getPayMonth(),
                        DateUtil.FRONT_DATE_FORMAT_STRING));
                String savePayRollCode = payRollBO
                    .savePayRoll(payRollcondition);
                payRollCode = savePayRollCode;

            }
            payRollCode = (payRollCode == null ? payRoll.getCode()
                    : payRollCode);
            String payRollDetailCode = payRollDetailBO
                .savePayRollDetail(payRollCode, xn631773ReqData);

            operateLogBO.saveOperateLog(
                EOperateLogRefType.PayRollDetail.getCode(), payRollDetailCode,
                EOperateLogOperate.ImportPayRollDetail.getValue(), user, null);
        }
        if (CollectionUtils.isNotEmpty(errorCode)) {
            throw new BizException("XN631812" + errorCode.toString());
        }
    }

}
