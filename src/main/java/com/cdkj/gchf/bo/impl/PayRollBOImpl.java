package com.cdkj.gchf.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IPayRollDAO;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631920ReqDateil;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;

@Component
public class PayRollBOImpl extends PaginableBOImpl<PayRoll>
        implements IPayRollBO {

    @Autowired
    private IPayRollDAO payRollDAO;

    @Override
    public String savePayRoll(PayRoll data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.PayRoll.getCode());
            data.setCode(code);
            payRollDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removePayRoll(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            PayRoll data = new PayRoll();
            data.setCode(code);
            count = payRollDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshPayRoll(PayRoll data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = payRollDAO.update(data);
        }
        return count;
    }

    @Override
    public void doUpload(XN631920Req req, ProjectConfig projectConfig) {

        List<XN631920ReqDateil> dateilList = req.getDetailList();
        for (XN631920ReqDateil detail : dateilList) {
            detail.setIdCardNumber(AesUtils.encrypt(detail.getIdCardNumber(),
                projectConfig.getSecret()));

            detail.setPayRollBankCardNumber(AesUtils.encrypt(
                detail.getPayRollBankCardNumber(), projectConfig.getSecret()));

            detail.setPayBankCardNumber(AesUtils.encrypt(
                detail.getPayBankCardNumber(), projectConfig.getSecret()));

        }

        String data = JSONObject.toJSON(req).toString();

        GovConnecter.getGovData("Payroll.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());
    }

    @Override
    public Paginable<PayRollDetail> doQuery(XN631921Req req,
            ProjectConfig projectConfig) {

        String data = JSONObject.toJSON(req).toString();

        String queryString = GovConnecter.getGovData("Payroll.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();

        Paginable<PayRollDetail> page = GovUtil.parseGovPage(req.getPageIndex(),
            req.getPageSize(), queryString, replaceMap, PayRollDetail.class);

        return page;
    }

    @Override
    public List<PayRoll> queryPayRollList(PayRoll condition) {
        return payRollDAO.selectList(condition);
    }

    @Override
    public PayRoll getPayRoll(String code) {
        PayRoll data = null;
        if (StringUtils.isNotBlank(code)) {
            PayRoll condition = new PayRoll();
            condition.setCode(code);
            data = payRollDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "工资单编号不存在");
            }
        }
        return data;
    }

}
