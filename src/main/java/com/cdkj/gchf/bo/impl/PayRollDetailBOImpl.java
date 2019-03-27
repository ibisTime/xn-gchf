package com.cdkj.gchf.bo.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.bo.IPayRollDetailBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.impl.PayRollDetailDAOImpl;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631770ReqDetail;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service(value = "payRollDetailBO")
public class PayRollDetailBOImpl extends PaginableBOImpl<PayRollDetail>
        implements IPayRollDetailBO {
    @Autowired
    private PayRollDetailDAOImpl payRollDetailDAO;

    @Override
    public void savePayRollDetail(String projectCode,
            List<XN631770ReqDetail> data) {

        for (XN631770ReqDetail xn631770ReqDetail : data) {
            String code = OrderNoGenerater
                .generate(EGeneratePrefix.PayRollDetail.getCode());
            PayRollDetail payRollDetail = new PayRollDetail();
            BeanUtils.copyProperties(xn631770ReqDetail, payRollDetail);
            payRollDetail.setCode(code);
            payRollDetail.setPayRollCode(projectCode);
            payRollDetailDAO.insert(payRollDetail);
        }
    }

    @Override
    public long getTotalCount(PayRollDetail condition) {
        return 0;
    }

    @Override
    public Paginable<PayRollDetail> getPaginable(int start,
            PayRollDetail condition) {
        return null;
    }

    @Override
    public Paginable<PayRollDetail> getPaginable(int start, int pageSize,
            PayRollDetail condition) {
        return null;
    }

    @Override
    public int deletePayRollDetail(String payRollcode) {
        PayRollDetail payRollDetail = new PayRollDetail();
        payRollDetail.setPayRollCode(payRollcode);
        return payRollDetailDAO.delete(payRollDetail);
    }

    @Override
    public int updatePayRollDetail(PayRollDetail data) {
        return 0;
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

}
