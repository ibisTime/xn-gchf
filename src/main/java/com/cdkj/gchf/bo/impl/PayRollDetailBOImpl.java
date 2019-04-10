package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.impl.PayRollDetailDAOImpl;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631810Req;
import com.cdkj.gchf.dto.req.XN631812ReqData;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EIsNotType;
import com.cdkj.gchf.enums.EUploadStatus;
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

    @Override
    public void savePayRollDetail(String projectCode, String getPayMonth,
            List<XN631770ReqDetail> data) {

        for (XN631770ReqDetail xn631770ReqDetail : data) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.PayRollDetail.getCode());
            PayRollDetail payRollDetail = new PayRollDetail();
            BeanUtils.copyProperties(xn631770ReqDetail, payRollDetail);
            payRollDetail.setCode(code);
            if (StringUtils.isNotBlank(xn631770ReqDetail.getDays())) {
                payRollDetail
                    .setDays(Integer.parseInt(xn631770ReqDetail.getDays()));
            }
            payRollDetail
                .setWorkHours(new BigDecimal(xn631770ReqDetail.getWorkHours()));
            ProjectWorker workerByIdCardNumber = projectWorkerBO
                .getProjectWorkerByIdentity(xn631770ReqDetail.getIdcardType(),
                    xn631770ReqDetail.getIdCardNumber());
            if (workerByIdCardNumber == null) {
                throw new BizException("XN631770", "项目人员不存在");
            }
            payRollDetail.setWorkerName(workerByIdCardNumber.getWorkerName());
            payRollDetail
                .setIdcardNumber(workerByIdCardNumber.getIdcardNumber());
            payRollDetail.setIdcardType(workerByIdCardNumber.getIdcardType());
            payRollDetail.setBalanceDate(
                DateUtil.strToDate(xn631770ReqDetail.getBalanceDate(),
                    DateUtil.FRONT_DATE_FORMAT_STRING));
            payRollDetail.setActualAmount(
                new BigDecimal(xn631770ReqDetail.getActualAmount()));
            if (StringUtils.isNotBlank(xn631770ReqDetail.getBalanceDate())) {
                Date balanceDate = DateUtil.strToDate(
                    xn631770ReqDetail.getBalanceDate(),
                    DateUtil.FRONT_DATE_FORMAT_STRING);
                payRollDetail.setBalanceDate(balanceDate);
            }
            if (StringUtils.isNotBlank(xn631770ReqDetail.getIsBackPay())) {
                payRollDetail.setIsBackPay(
                    Integer.parseInt(xn631770ReqDetail.getIsBackPay()));
            }
            if (StringUtils.isNotBlank(xn631770ReqDetail.getTotalPayAmount())) {
                payRollDetail.setTotalPayAmount(
                    new BigDecimal(xn631770ReqDetail.getTotalPayAmount()));
            }
            payRollDetail.setPayRollCode(projectCode);
            payRollDetail.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
            payRollDetailDAO.insert(payRollDetail);
        }
    }

    @Override
    public int deletePayRollDetailByPayRollCode(String payRollcode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollcode);
        return payRollDetailDAO.delete(payRollDetail);
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
    public int deletePayRollDetail(String code) {
        PayRollDetail data = new PayRollDetail();
        data.setCode(code);
        return payRollDetailDAO.delete(data);
    }

    @Override
    public String savePayRollDetail(PayRollDetail payRollDetail) {
        String code = null;
        code = OrderNoGenerater
            .generate(EGeneratePrefix.PayRollDetail.getCode());
        payRollDetail.setCode(code);
        payRollDetailDAO.insert(payRollDetail);
        return code;
    }

    @Override
    public List<PayRollDetail> queryList(PayRollDetail condition) {
        return payRollDetailDAO.selectList(condition);
    }

    @Override
    public int updatePayRollDetail(XN631810Req data) {
        PayRollDetail condition = new PayRollDetail();
        condition.setCode(data.getCode());
        BeanUtils.copyProperties(data, condition);
        condition.setActualAmount(new BigDecimal(data.getActualAmount()));
        condition.setWorkHours(new BigDecimal(data.getWorkHours()));
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
        if (StringUtils.isNotBlank(data.getTotalPayAmount())) {
            condition.setDays(Integer.parseInt(data.getDays()));
        }
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
    public String savePayRollDetail(String payRollcode,
            XN631812ReqData xn631773ReqData) {
        String code = null;
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollcode);
        BeanUtils.copyProperties(xn631773ReqData, payRollDetail);
        if (StringUtils.isNotBlank(xn631773ReqData.getDays())) {
            payRollDetail.setDays(Integer.parseInt(xn631773ReqData.getDays()));
        }
        if (StringUtils.isNotBlank(xn631773ReqData.getWorkHours())) {
            payRollDetail
                .setWorkHours(new BigDecimal(xn631773ReqData.getWorkHours()));
        }
        if (StringUtils.isNotBlank(xn631773ReqData.getTotalPayAmount())) {
            payRollDetail.setTotalPayAmount(
                new BigDecimal(xn631773ReqData.getTotalPayAmount()));
        }
        if (StringUtils.isNotBlank(xn631773ReqData.getActualAmount())) {
            payRollDetail.setActualAmount(
                new BigDecimal(xn631773ReqData.getActualAmount()));
        }
        if (StringUtils.isBlank(xn631773ReqData.getIsBackPay())) {
            payRollDetail
                .setIsBackPay(Integer.parseInt(xn631773ReqData.getIsBackPay()));
            if (StringUtils.isNotBlank(xn631773ReqData.getBackPayMonth())) {
                payRollDetail.setBalanceDate(
                    DateUtil.strToDate(xn631773ReqData.getBackPayMonth(),
                        DateUtil.FRONT_DATE_FORMAT_STRING));
            }
        }
        payRollDetail.setIdcardType(xn631773ReqData.getIdCardType());
        payRollDetail.setIdcardNumber(xn631773ReqData.getIdCardNumber());
        ProjectWorker projectWorkerByIdentity = projectWorkerBO
            .getProjectWorkerByIdentity(xn631773ReqData.getIdCardType(),
                xn631773ReqData.getIdCardNumber());
        payRollDetail.setWorkerName(projectWorkerByIdentity.getWorkerName());

        if (StringUtils.isNotBlank(xn631773ReqData.getIsBackPay())) {
            if (xn631773ReqData.getIsBackPay()
                .equals(EIsNotType.IS.getCode())) {
                payRollDetail.setBalanceDate(
                    DateUtil.strToDate(xn631773ReqData.getBackPayMonth(),
                        DateUtil.FRONT_DATE_FORMAT_STRING));
            }
            payRollDetail
                .setIsBackPay(Integer.parseInt(xn631773ReqData.getIsBackPay()));
        }
        code = OrderNoGenerater
            .generate(EGeneratePrefix.PayRollDetail.getCode());
        payRollDetail.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        payRollDetail.setCode(code);
        payRollDetailDAO.insert(payRollDetail);
        return code;
    }

}
