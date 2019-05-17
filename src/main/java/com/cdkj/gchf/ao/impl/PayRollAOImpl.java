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
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631812Req;
import com.cdkj.gchf.dto.req.XN631812ReqData;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.enums.EIsNotType;
import com.cdkj.gchf.enums.EOperateLogOperate;
import com.cdkj.gchf.enums.EOperateLogRefType;
import com.cdkj.gchf.enums.EPayRollUploadStatus;
import com.cdkj.gchf.enums.EProjectWorkerUploadStatus;
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
    private IUserBO userBO;

    @Autowired
    private IOperateLogBO operateLogBO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Autowired
    private ICorpBasicinfoBO corpBasicinfoBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Transactional
    @Override
    public String addPayRoll(XN631770Req data) {

        if (projectBO.getProject(data.getProjectCode()) == null) {
            throw new BizException("XN631700", "请选择项目");
        }
        if (teamMasterBO.getTeamMaster(data.getTeamSysNo()) == null) {
            throw new BizException("XN631700", "请选择班组");
        }
        CorpBasicinfo basicinfoByCorp = corpBasicinfoBO
            .getCorpBasicinfoByCorp(data.getCorpCode());
        if (basicinfoByCorp == null) {
            throw new BizException("XN631700", "企业信息不存在");
        }
        // 保存工资单信息
        String code = payRollBO.savePayRoll(data, basicinfoByCorp);
        // 保存明细信息
        payRollDetailBO.savePayRollDetail(code, data.getTeamSysNo(),
                data.getProjectCode(), data.getPayMonth(), data.getDetailList());

        User briefUser = userBO.getBriefUser(data.getUserId());
        operateLogBO.saveOperateLog(EOperateLogRefType.PayRoll.getCode(), code,
            EOperateLogRefType.PayRoll.getValue(), briefUser, "新增工资单" + code);

        return code;
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
                    .equals(EPayRollUploadStatus.UPLOAD_UNEDITABLE.getCode())) {
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
    public int editPayRoll(XN631772Req req) {
        PayRoll payRoll = payRollBO.getPayRoll(req.getCode());
        if (payRoll == null) {
            throw new BizException("XN631772", "工资单不存在");
        }
        return payRollBO.updatePayRollDetail(req);
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
        Project project = projectBO.getProject(req.getProjectCode());

        if (project == null) {
            throw new BizException("XN631773", "项目不存在");
        }
        // excel字段中有工资单信息和明细信息 两张表操作
        List<XN631812ReqData> dateList = req.getDateList();
        for (XN631812ReqData data : dateList) {

            TeamMaster teamMasterByCondition = teamMasterBO
                    .getTeamMasterByProject(req.getProjectCode(),
                            data.getCorpCode(), data.getTeamName());

            if (teamMasterByCondition == null) {
                throw new BizException("XN631812",
                        "班组信息不存在【" + data.getTeamName() + "】");
            }

            List<ProjectWorker> workerList = projectWorkerBO.getProjectWorkers(
                    data.getCorpCode(), data.getTeamName(), data.getWorkerName());
            if (CollectionUtils.isEmpty(workerList)) {
                continue;
            }
            ProjectWorker projectWorker = projectWorkerBO
                    .getProjectWorker(workerList.get(0).getCode());
            // 不存在相关工资单时相关联的工资单
            String payRollCode = null;
            PayRoll payRoll = payRollBO
                    .getPayRollByCorpCodeAndTeamSysNoAndProjectCode(
                            data.getCorpCode(), teamMasterByCondition.getCode(),
                            req.getProjectCode(), data.getPayMonth());
            if (payRoll == null) {

                String savePayRollCode = payRollBO.savePayRoll(
                        data.getCorpCode(), req.getProjectCode(),
                        data.getCorpName(), teamMasterByCondition.getCode(),
                        data.getPayMonth());

                payRollCode = savePayRollCode;

            }
            payRollCode = (payRollCode == null ? payRoll.getCode()
                    : payRollCode);

            String payRollDetailCode = payRollDetailBO
                    .savePayRollDetail(projectWorker, payRollCode, data);

            operateLogBO.saveOperateLog(
                    EOperateLogRefType.PayRollDetail.getCode(), payRollDetailCode,
                    EOperateLogOperate.ImportPayRollDetail.getValue(), user, null);
        }
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
                operateLogBO.saveOperateLog(
                    EOperateLogRefType.PayRollDetail.getCode(), code, "项目未配置",
                    user, null);
                continue;
            }
            // 校验项目人员是否上传
            ProjectWorker projectWorker = projectWorkerBO
                    .getProjectWorkerByIdentity(payRoll.getTeamSysNo(),
                            payRollDetail.getIdcardNumber());
            if (!projectWorker.getUploadStatus()
                    .equals(EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode())
                    && !projectWorker.getUploadStatus().equals(
                    EProjectWorkerUploadStatus.UPLOAD_UNUPDATE.getCode())) {
                // 不是已上传的人员
                throw new BizException("XN00000",
                        "项目人员未上传【 " + projectWorker.getWorkerName() + " 】");

            }
            // 获取上传JSON
            String json = getRequestJsonToPlantform(payRoll, payRollDetail,
                projectConfigByLocal);
            // 更新上传状态
            payRollDetailBO.refreshUploadStatus(code,
                    EPayRollUploadStatus.UPLOADING.getCode());

            String resString;
            try {
                resString = GovConnecter.getGovData("Payroll.Add", json,
                        projectConfigByLocal.getProjectCode(),
                        projectConfigByLocal.getSecret());
            } catch (BizException e) {
                e.printStackTrace();
                payRollDetailBO.refreshUploadStatus(code,
                        EPayRollUploadStatus.UPLOAD_FAIL.getCode());
                throw e;
            }

            String log = operateLogBO.saveOperateLog(
                EOperateLogRefType.PayRollDetail.getCode(), code,
                EOperateLogOperate.UploadPayRoll.getValue(), user, null);

            AsyncQueueHolder.addSerial(resString, projectConfigByLocal,
                "payRollDetailBO", code,
                    EPayRollUploadStatus.UPLOAD_UNEDITABLE.getCode(), log, userId);
        }

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
     * 获取请求国家平台的Json
     */
    private String getRequestJsonToPlantform(PayRoll payRoll,
            PayRollDetail payRollDetail, ProjectConfig projectconfig) {
        if (StringUtils.isBlank(payRollDetail.getPayRollBankCode())) {
            throw new BizException("XN00000", "导入信息不全,请修改后上传");
        }
        if (StringUtils.isBlank(payRollDetail.getPayBankName())) {
            throw new BizException("XN00000", "导入信息不全,请修改后上传");
        }
        if (StringUtils.isBlank(payRollDetail.getPayRollBankName())) {
            throw new BizException("XN00000", "导入信息不全,请修改后上传");
        }
        if (StringUtils.isBlank(payRollDetail.getPayBankCode())) {
            throw new BizException("XN00000", "导入信息不全,请修改后上传");
        }
        if (StringUtils.isBlank(payRollDetail.getPayBankName())) {
            throw new BizException("XN00000", "导入信息不全,请修改后上传");
        }
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        ProjectConfig projectConfigByLocal = projectConfigBO
            .getProjectConfigByLocal(payRoll.getProjectCode());
        // projectCode
        jsonObject.addProperty("projectCode",
            projectConfigByLocal.getProjectCode());
        // corpCode corpName
        CorpBasicinfo corpBasicinfoByCorp = corpBasicinfoBO
            .getCorpBasicinfoByCorp(payRoll.getCorpCode());
        jsonObject.addProperty("corpCode", corpBasicinfoByCorp.getCorpCode());
        jsonObject.addProperty("corpName", corpBasicinfoByCorp.getCorpName());
        // teamMasterNo
        TeamMaster teamMaster = teamMasterBO
            .getTeamMaster(payRoll.getTeamSysNo());
        if (StringUtils.isBlank(teamMaster.getTeamSysNo())) {
            throw new BizException("XN631813", "班组信息未上传");
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
        if (payRollDetail.getIsBackPay().intValue() == Integer
                .parseInt(EIsNotType.IS.getCode())) {
            // 补发

        }
        if (payRollDetail.getBalanceDate() != null) {
            String balanceDate = DateUtil.dateToStr(
                payRollDetail.getBalanceDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING);
            payRollData.addProperty("balanceDate", balanceDate);
        }
        if (payRollDetail.getWorkHours() != null) {
            payRollData.addProperty("balanceDate",
                    payRollDetail.getWorkHours());
        }
        if (payRollDetail.getDays() != null) {
            payRollData.addProperty("days", payRollDetail.getDays());
        }
        payRollData.addProperty("idCardType", payRollDetail.getIdcardType());
        payRollData.addProperty("idCardNumber", AesUtils.encrypt(
            payRollDetail.getIdcardNumber(), projectconfig.getSecret()));
        payRollData.addProperty("payBankCardNumber", AesUtils.encrypt(
            payRollDetail.getPayBankCardNumber(), projectconfig.getSecret()));
        payRollData.addProperty("payBankCode", payRollDetail.getPayBankCode());
        payRollData.addProperty("payRollBankCardNumber",
            AesUtils.encrypt(payRollDetail.getPayRollBankCardNumber(),
                projectconfig.getSecret()));
        payRollData.addProperty("payRollBankName",
            payRollDetail.getPayBankName());
        payRollData.addProperty("payRollBankCode",
            payRollDetail.getPayRollBankCode());

        jsonArray.add(payRollData);
        jsonObject.add("detailList", jsonArray);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();

    }

    @Override
    public PayRoll getPayRoll(String code) {
        return payRollBO.getPayRoll(code);
    }

}
