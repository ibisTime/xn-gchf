package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.bo.IBankCardBankBO;
import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.StringUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.impl.PayRollDetailDAOImpl;
import com.cdkj.gchf.domain.BankCardInfo;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631810Req;
import com.cdkj.gchf.dto.req.XN631812ReqData;
import com.cdkj.gchf.enums.EBankCardCodeType;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIsNotType;
import com.cdkj.gchf.enums.EPayRollUploadStatus;
import com.cdkj.gchf.exception.BizException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service(value = "payRollDetailBO")
public class PayRollDetailBOImpl extends PaginableBOImpl<PayRollDetail>
        implements IPayRollDetailBO {

    @Autowired
    private PayRollDetailDAOImpl payRollDetailDAO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IBankCardBankBO bankCardBankBO;

    @Override
    public String savePayRollDetail(PayRollDetail payRollDetail) {
        String code = null;
        code = OrderNoGenerater
                .generate(EGeneratePrefix.PayRollDetail.getCode());
        payRollDetail.setCode(code);
        payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        payRollDetailDAO.insert(payRollDetail);
        return code;
    }

    @Override
    public void savePayRollDetail(String payRollCode, String teamSysNo,
            String projectCode, String getPayMonth,
            List<XN631770ReqDetail> data) {

        for (XN631770ReqDetail detail : data) {
            PayRollDetail payRollDetail = new PayRollDetail();
            if (!StringUtil.isNumber(detail.getActualAmount())) {
                throw new BizException("XN631770", "实发金额不是数字类型");
            }
            if (!StringUtil.isNumber(detail.getTotalPayAmount())) {
                throw new BizException("XN631770", "实发金额不是数字类型");
            }

            ProjectWorker projectWorker = projectWorkerBO
                    .getProjectWorker(detail.getWorkerCode());

            // 校验银行卡信息
            //参建单位的银行卡
            BankCardInfo workerBankCard = bankCardBankBO
                    .getBankCardInfo(detail.getWorkerBankCard());

            BankCardInfo corpBankCard = bankCardBankBO.getBankCardInfo(detail.getCorpBankCard());

            if (workerBankCard == null || corpBankCard == null) {
                throw new BizException("xn000000", "所选银行卡不存在");
            }

            // 插入数据
            BeanUtils.copyProperties(detail, payRollDetail);
            // 银行卡信息
            payRollDetail
                    .setPayRollBankCardNumber(workerBankCard.getBankNumber());
            payRollDetail.setPayRollBankCode(workerBankCard.getBankCode());
            payRollDetail.setPayRollBankName(workerBankCard.getBankName());

            payRollDetail.setPayBankCode(corpBankCard.getBankCode());
            payRollDetail.setPayBankName(
                    EBankCardCodeType.getBankCardType(corpBankCard.getBankCode()).getValue());
            payRollDetail.setPayBankCardNumber(corpBankCard.getBankNumber());

            // 员工信息
            payRollDetail.setWorkerName(projectWorker.getWorkerName());
            payRollDetail.setIdcardNumber(projectWorker.getIdcardNumber());
            payRollDetail.setIdcardType("01");
            payRollDetail.setPayRollCode(payRollCode);
            payRollDetail.setCode(OrderNoGenerater
                    .generate(EGeneratePrefix.PayRollDetail.getCode()));
            if (StringUtils.isNotBlank(detail.getDays())) {
                payRollDetail.setDays(Integer.parseInt(detail.getDays()));
            }
            if (StringUtils.isNotBlank(detail.getWorkHours())) {
                payRollDetail
                        .setWorkHours(new BigDecimal(detail.getWorkHours()));
            }
            payRollDetail
                    .setActualAmount(new BigDecimal(detail.getActualAmount()));
            if (StringUtils.isNotBlank(detail.getIsBackPay())) {

                if (detail.getIsBackPay().equals(EIsNotType.IS.getCode())) {
                    payRollDetail.setBackPayMonth(DateUtil
                            .strToDate(detail.getBackPayMonth(), "yyyy-MM"));
                }
                payRollDetail
                        .setIsBackPay(Integer.parseInt(detail.getIsBackPay()));
            }
            payRollDetail
                    .setTotalPayAmount(new BigDecimal(detail.getTotalPayAmount()));
            Date toDate = DateUtil.strToDate(detail.getBalanceDate(),
                    "yyyy-MM-dd");
            payRollDetail.setBalanceDate(toDate);
            payRollDetail.setUploadStatus(EPayRollUploadStatus.TO_UPLOAD.getCode());
            payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

            payRollDetailDAO.insert(payRollDetail);

            //回写工资信息到项目人员
            if (StringUtils.isNotBlank(detail.getBackPayMonth())) {
                projectWorkerBO.refreshLastPayRoll(projectWorker.getCode(),
                        detail.getBackPayMonth(),
                        detail.getTotalPayAmount(), detail.getActualAmount());
            } else {
                projectWorkerBO.refreshLastPayRoll(projectWorker.getCode(),
                        getPayMonth,
                        detail.getTotalPayAmount(), detail.getActualAmount());
            }


        }
    }

    @Override
    public String savePayRollDetail(ProjectWorker projectWorker,
            String payRollcode, XN631812ReqData data, BankCardInfo bankCardInfo) {
        String code = null;
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollcode);
        BeanUtils.copyProperties(data, payRollDetail);
        if (StringUtils.isNotBlank(data.getDays())) {
            payRollDetail.setDays(Integer.parseInt(data.getDays()));
        }
        if (StringUtils.isNotBlank(data.getWorkHours())) {
            payRollDetail.setWorkHours(new BigDecimal(data.getWorkHours()));
        }
        if (StringUtils.isNotBlank(data.getTotalPayAmount())) {
            payRollDetail
                    .setTotalPayAmount(new BigDecimal(data.getTotalPayAmount()));
        }
        if (StringUtils.isNotBlank(data.getActualAmount())) {
            payRollDetail
                    .setActualAmount(new BigDecimal(data.getActualAmount()));
        }
        if (StringUtils.isNotBlank(data.getIsBackPay())) {
            payRollDetail.setIsBackPay(Integer.parseInt(data.getIsBackPay()));

        }
        if (StringUtils.isNotBlank(data.getDays())) {
            payRollDetail.setDays(Integer.parseInt(data.getDays()));
        }
        if (StringUtils.isNotBlank(data.getBackPayMonth())) {
            payRollDetail.setBackPayMonth(
                    DateUtil.strToDate(data.getBackPayMonth(), "yyyy-MM"));

        }

        payRollDetail.setBalanceDate(DateUtil.strToDate(data.getBalanceDate(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        payRollDetail.setIdcardType("01");
        payRollDetail.setWorkerName(projectWorker.getWorkerName());
        payRollDetail.setIdcardNumber(projectWorker.getIdcardNumber());
        if (StringUtils.isNotBlank(data.getPayRollBankCode())) {
            payRollDetail.setPayRollBankCode(data.getPayBankCode());
            payRollDetail.setPayRollBankName(EBankCardCodeType
                    .getBankCardType(data.getPayBankCode()).getValue());

        }
        payRollDetail.setPayRollBankCode(bankCardInfo.getBankCode());
        payRollDetail.setPayRollBankCardNumber(bankCardInfo.getBankNumber());
        payRollDetail
                .setPayRollBankName(EBankCardCodeType.getDictVaule(bankCardInfo.getBankCode()));

        code = OrderNoGenerater
                .generate(EGeneratePrefix.PayRollDetail.getCode());
        payRollDetail.setUploadStatus(EPayRollUploadStatus.TO_UPLOAD.getCode());
        payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        payRollDetail.setCode(code);
        payRollDetailDAO.insert(payRollDetail);
        return code;
    }

    @Override
    public int fakeDeletePayRollDetail(String idCardType, String idCardNumber,
            String projectCode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setProjectCode(projectCode);
        payRollDetail.setIdcardNumber(idCardNumber);
        payRollDetail.setIdcardType("01");
        payRollDetail.setUploadStatus(EPayRollUploadStatus.TO_UPLOAD.getCode());
        return payRollDetailDAO.updatePayRollDetailDeleteStatus(payRollDetail);
    }

    @Override
    public int fakeDeletePayRollDetailByPayRollCode(String payRollCode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollCode);
        payRollDetail.setUploadStatus(EPayRollUploadStatus.TO_UPLOAD.getCode());
        payRollDetail.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        return payRollDetailDAO.updatePayRollDetailDeleteStatus(payRollDetail);
    }

    @Override
    public int deletePayRollDetail(String code) {
        PayRollDetail data = new PayRollDetail();
        data.setCode(code);
        return payRollDetailDAO.delete(data);
    }

    @Override
    public int updatePayRollDetailDeleteStatus(String code, String status) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setCode(code);
        payRollDetail.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        return payRollDetailDAO.updatePayRollDetailDeleteStatus(payRollDetail);
    }

    @Override
    public int updatePayRollDetail(XN631810Req data) {
        PayRollDetail condition = new PayRollDetail();
        condition.setCode(data.getCode());
        BeanUtils.copyProperties(data, condition);
        if (StringUtils.isNotBlank(data.getActualAmount())) {
            condition.setActualAmount(new BigDecimal(data.getActualAmount()));
        }
        if (StringUtils.isNotEmpty(data.getWorkHours())) {
            condition.setWorkHours(new BigDecimal(data.getWorkHours()));
        }
        if (StringUtils.isNotBlank(data.getBalanceDate())) {
            Date balanceDate = DateUtil.strToDate(data.getBalanceDate(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
            condition.setBalanceDate(balanceDate);
        }
        if (StringUtils.isNotBlank(data.getIsBackPay())) {
            condition.setIsBackPay(Integer.parseInt(data.getIsBackPay()));
        }
        if (StringUtils.isNotBlank(data.getTotalPayAmount())) {
            condition
                    .setTotalPayAmount(new BigDecimal(data.getTotalPayAmount()));
        }
        if (StringUtils.isNotBlank(data.getBackPayMonth())) {
            condition.setBackPayMonth(
                    DateUtil.strToDate(data.getBackPayMonth(), "yyyy-MM"));
        }
        if (StringUtils.isNotBlank(data.getDays())) {
            condition.setDays(Integer.parseInt(data.getDays()));
        }
        condition.setPayRollBankName(
                EBankCardCodeType.getBankCardType(data.getPayRollBankCode()).getValue());
        condition.setPayBankName(
                EBankCardCodeType.getBankCardType(data.getPayBankCode()).getValue());
        condition.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        condition.setUploadStatus(EPayRollUploadStatus.TO_UPLOAD.getCode());
        return payRollDetailDAO.update(condition);
    }

    @Override
    public void refreshUploadStatus(String code, String uploadStatus) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setCode(code);
        payRollDetail.setUploadStatus(uploadStatus);
        payRollDetailDAO.updateStatus(payRollDetail);
    }

    @Override
    public int updatePayRollDetail(XN631772Req req) {

        PayRollDetail condition = new PayRollDetail();
        condition.setPayRollCode(req.getCode());
        PayRollDetail payRollDetail = payRollDetailDAO.select(condition);
        BeanUtils.copyProperties(req, payRollDetail);
        return payRollDetailDAO.update(payRollDetail);
    }

    @Override
    public List<PayRollDetail> queryListByPayRollDetail(String payRollCode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollCode);
        return payRollDetailDAO.selectList(payRollDetail);
    }

    @Override
    public JsonObject getUploadRequestJsontoPlantform(PayRoll payRollData,
            TeamMaster teamMasterData, ProjectConfig projectConfigData,
            PayRollDetail payRollDetailData) {
        System.out.println(projectConfigData.getSecret());
        System.out.println(projectConfigData.getProjectCode());
        System.out.println(payRollDetailData.getPayBankCardNumber());
        JsonObject requestJson = new JsonObject();
        JsonObject childJson = new JsonObject();
        childJson.addProperty("idCardType", payRollDetailData.getIdcardType());
        childJson.addProperty("idCardNumber",
                AesUtils.encrypt(payRollDetailData.getIdcardNumber(),
                        projectConfigData.getSecret()));
        childJson.addProperty("days", payRollDetailData.getDays());
        childJson.addProperty("workHours", payRollDetailData.getWorkHours());
        childJson.addProperty("payRollBankCardNumber",
                AesUtils.encrypt(payRollDetailData.getPayBankCardNumber(),
                        projectConfigData.getSecret()));
        childJson.addProperty("payRollBankCode",
                payRollDetailData.getPayBankCode());
        childJson.addProperty("payRollBankName",
                payRollDetailData.getPayRollBankName());
        childJson.addProperty("payBankCardNumber",

                projectConfigData.getSecret());
        childJson.addProperty("payRollBankCode",
                payRollDetailData.getPayBankCode());
        childJson.addProperty("payRollBankName",
                payRollDetailData.getPayRollBankName());
        childJson.addProperty("payBankCardNumber",
                AesUtils.encrypt(payRollDetailData.getPayBankCardNumber(),
                        projectConfigData.getSecret()));

        childJson.addProperty("payBankCode",
                payRollDetailData.getPayBankCode());
        childJson.addProperty("payBankName",
                payRollDetailData.getPayBankName());
        childJson.addProperty("totalPayAmount",
                payRollDetailData.getTotalPayAmount());
        childJson.addProperty("actualAmount",
                payRollDetailData.getActualAmount());
        childJson.addProperty("isBackPay", payRollDetailData.getIsBackPay());
        childJson.addProperty("balanceDate", new SimpleDateFormat("yyyy-MM-dd")
                .format(payRollDetailData.getBalanceDate()));
        if (payRollDetailData.getIsBackPay().equals("1")) {
            childJson.addProperty("backPayMonth",
                    new SimpleDateFormat("yyyy-MM-dd")
                            .format(payRollDetailData.getBackPayMonth()));
        }

        childJson.addProperty("thirdPayRollCode",
                payRollDetailData.getThirdPayRollCode());
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(childJson);
        requestJson.addProperty("projectCode",
                projectConfigData.getProjectCode());
        requestJson.addProperty("corpCode", payRollData.getCorpCode());
        requestJson.addProperty("corpName", payRollData.getCorpName());
        requestJson.addProperty("teamSysNo", teamMasterData.getTeamSysNo());
        requestJson.addProperty("payMonth",
                new SimpleDateFormat("yyyy-MM").format(payRollData.getPayMonth()));
        requestJson.add("detailList", jsonArray);
        return requestJson;
    }

    @Override
    public PayRollDetail getPayRollDetail(String code) {
        PayRollDetail condition = new PayRollDetail();
        condition.setCode(code);
        return payRollDetailDAO.select(condition);
    }

    @Override
    public List<PayRollDetail> getPayRollDetailByPayRollCode(
            String payRollCode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollCode);
        return payRollDetailDAO.selectList(payRollDetail);
    }

    @Override
    public long selectCountByWorkerCode(String workerCode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setWorkerCode(workerCode);
        payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        return payRollDetailDAO.selectTotalCount(payRollDetail);
    }

    @Override
    public PayRollDetail getLastPayRollData(String workerCode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setWorkerCode(workerCode);
        payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        PayRollDetail data = payRollDetailDAO.selectByWorkerCode(payRollDetail);
        return data;
    }

    @Override
    public List<PayRollDetail> queryList(PayRollDetail condition) {
        return payRollDetailDAO.selectList(condition);
    }


}
