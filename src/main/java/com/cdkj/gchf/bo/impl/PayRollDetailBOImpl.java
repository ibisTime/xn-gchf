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
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
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

    @Autowired
    private IBankCardBankBO bankCardBankBO;

    @Override
    public void savePayRollDetail(String payRollCode, String projectCode,
            String getPayMonth, List<XN631770ReqDetail> data) {

        for (XN631770ReqDetail detail : data) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.PayRollDetail.getCode());
            PayRollDetail payRollDetail = new PayRollDetail();
            payRollDetail.setPayRollCode(payRollCode);
            BeanUtils.copyProperties(detail, payRollDetail);
            payRollDetail.setCode(code);
            if (StringUtils.isNotBlank(detail.getDays())) {
                payRollDetail.setDays(Integer.parseInt(detail.getDays()));
            }

            if (StringUtils.isNotBlank(detail.getWorkHours())) {
                payRollDetail
                    .setWorkHours(new BigDecimal(detail.getWorkHours()));
            }
            String payRollBankCardNumber = detail.getPayRollBankCardNumber();
            BankCardInfo bankCardInfoByNum = bankCardBankBO
                .getBankCardInfoByNum(detail.getPayRollBankCardNumber());
            if (payRollBankCardNumber == null) {
                throw new BizException("XN631770", "项目银行卡号不能为空");
            }
            if (bankCardInfoByNum == null) {
                throw new BizException("XN631770",
                    "员工银行卡未绑定【" + payRollBankCardNumber + "】");
            }
            String workerCode = bankCardInfoByNum.getBusinessSysNo();
            ProjectWorker workerByBankCard = projectWorkerBO
                .getProjectWorker(workerCode);
            // List<ProjectWorker> projectWorkers = projectWorkerBO
            // .queryProjectWorkerList(projectCode, detail.getIdCardNumber());
            // if (CollectionUtils.isEmpty(projectWorkers)) {
            // throw new BizException("XN631770",
            // "项目人员【" + detail.getIdCardNumber() + "】不存在");
            // }
            // ProjectWorker projectWorker = projectWorkers.get(0);
            if (null == workerByBankCard) {
                throw new BizException("XN631733", "项目人员银行卡信息未绑定【"
                        + bankCardInfoByNum.getBusinessSysNo() + "】");
            }
            payRollDetail.setWorkerName(workerByBankCard.getWorkerName());
            payRollDetail.setIdcardNumber(workerByBankCard.getIdcardNumber());
            payRollDetail.setIdcardType("01");

            Date toDate;
            try {
                toDate = DateUtil.strToDate(detail.getBalanceDate(),
                    "yyyy-MM-dd");

            } catch (Exception e) {
                Date strToDate = DateUtil.strToDate(detail.getBalanceDate(),
                    "yyyy/mm/dd");
                String format = new SimpleDateFormat("yyyy-MM-dd")
                    .format(strToDate);
                toDate = DateUtil.strToDate(format, "yyyy-MM-dd");
            }
            payRollDetail.setBalanceDate(toDate);

            payRollDetail
                .setActualAmount(new BigDecimal(detail.getActualAmount()));
            if (StringUtils.isNotBlank(detail.getIsBackPay())) {
                payRollDetail
                    .setIsBackPay(Integer.parseInt(detail.getIsBackPay()));
            }
            payRollDetail
                .setTotalPayAmount(new BigDecimal(detail.getTotalPayAmount()));
            payRollDetail.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
            payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

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
    public int updatePayRollDetailDeleteStatus(String code, String status) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setCode(code);
        payRollDetail.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        return payRollDetailDAO.updatePayRollDetailDeleteStatus(payRollDetail);
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
        payRollDetail.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
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
        if (StringUtils.isNotBlank(data.getDays())) {
            condition.setDays(Integer.parseInt(data.getDays()));
        }
        condition.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        condition.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
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
    public String savePayRollDetail(ProjectWorker projectWorker,
            String payRollcode, XN631812ReqData data) {
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

        payRollDetail.setBalanceDate(DateUtil.strToDate(data.getBalanceDate(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        payRollDetail.setIdcardType("01");
        payRollDetail.setWorkerName(projectWorker.getWorkerName());
        payRollDetail.setIdcardNumber(projectWorker.getIdcardNumber());

        code = OrderNoGenerater
            .generate(EGeneratePrefix.PayRollDetail.getCode());
        payRollDetail.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
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
        payRollDetail.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        return payRollDetailDAO.updatePayRollDetailDeleteStatus(payRollDetail);
    }

    @Override
    public int FakeDeletePayRollDetailByPayRollCode(String payRollCode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollCode);
        payRollDetail.setUploadStatus(EUploadStatus.TO_UPLOAD.getCode());
        payRollDetail.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        return payRollDetailDAO.updatePayRollDetailDeleteStatus(payRollDetail);
    }

}
