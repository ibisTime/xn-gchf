package com.cdkj.gchf.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.ITeamMasterBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.AesUtils;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IPayRollDAO;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631920ReqDateil;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.gov.GovConnecter;
import com.cdkj.gchf.gov.GovUtil;
import com.cdkj.gchf.gov.SerialHandler;

@Component
public class PayRollBOImpl extends PaginableBOImpl<PayRoll>
        implements IPayRollBO {

    @Autowired
    private IPayRollDAO payRollDAO;

    @Autowired
    private ITeamMasterBO teamMasterBO;

    @Override
    public String savePayRoll(XN631770Req data) {
        PayRoll payRoll = new PayRoll();

        BeanUtils.copyProperties(data, payRoll);
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.PayRoll.getCode());
        payRoll.setCode(code);
        payRoll.setPayMonth(data.getPayMonth());
        payRollDAO.insert(payRoll);
        return code;
    }

    @Override
    public int removePayRoll(String code) {

        PayRoll data = new PayRoll();
        data.setCode(code);
        return payRollDAO.delete(data);
    }

    @Override
    public int refreshPayRoll(PayRoll data) {
        int count = 0;
        count = payRollDAO.update(data);
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

        String resString = GovConnecter.getGovData("Payroll.Add", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        SerialHandler.handle(resString, projectConfig);
    }

    @Override
    public Paginable<PayRollDetail> doQuery(XN631921Req req,
            ProjectConfig projectConfig) {

        String data = JSONObject.toJSON(req).toString();

        String queryString = GovConnecter.getGovData("Payroll.Query", data,
            projectConfig.getProjectCode(), projectConfig.getSecret());

        Map<String, String> replaceMap = new HashMap<>();
        replaceMap.put("payMonth", "payMonthString");

        Paginable<PayRoll> page = GovUtil.parseGovPage(req.getPageIndex(),
            req.getPageSize(), queryString, replaceMap, PayRoll.class);

        Paginable<PayRollDetail> detailPage = new Page<PayRollDetail>(
            page.getPageNo(), page.getPageSize(), page.getTotalCount());
        List<PayRoll> payRollList = page.getList();
        List<PayRollDetail> payRollDetailList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(payRollList)) {
            for (PayRoll payRoll : payRollList) {
                if (CollectionUtils.isNotEmpty(payRoll.getDetailList())) {
                    for (XN631920ReqDateil reqDateil : payRoll
                        .getDetailList()) {
                        PayRollDetail payRollDetail = new PayRollDetail();
                        BeanUtils.copyProperties(reqDateil, payRollDetail);

                        payRollDetail.setCorpName(payRoll.getCorpName());
                        payRollDetail.setBalanceDate(
                            DateUtil.strToDate(reqDateil.getBalanceDate(),
                                DateUtil.FRONT_DATE_FORMAT_STRING));

                        XN631910Req teamReq = new XN631910Req(
                            Integer.parseInt(payRoll.getTeamSysNo()),
                            payRoll.getProjectCode());
                        teamReq.setPageIndex(0);
                        teamReq.setPageSize(1);

                        Paginable<TeamMaster> teamPage = teamMasterBO
                            .doQuery(teamReq, projectConfig);

                        if (null != teamPage && CollectionUtils
                            .isNotEmpty(teamPage.getList())) {
                            TeamMaster teamMaster = teamPage.getList().get(0);
                            payRollDetail.setTeamName(teamMaster.getTeamName());
                            payRollDetail
                                .setProjectName(projectConfig.getProjectName());
                            payRollDetail
                                .setProjectCode(projectConfig.getProjectCode());
                            payRollDetail.setCorpCode(payRoll.getCorpCode());
                        }

                        payRollDetailList.add(payRollDetail);
                    }
                }

            }
        }
        detailPage.setList(payRollDetailList);

        return detailPage;
    }

    @Override
    public List<PayRoll> queryPayRollList(PayRoll condition) {
        return payRollDAO.selectList(condition);
    }

    @Override
    public PayRoll getPayRoll(String code) {
        PayRoll data = null;
        PayRoll condition = new PayRoll();
        condition.setCode(code);
        data = payRollDAO.select(condition);
        if (data == null) {
            throw new BizException("xn0000", "工资单编号不存在");
        }
        return data;
    }

    @Override
    public PayRoll getPayRollByCondition(PayRoll condition) {
        return payRollDAO.select(condition);
    }

}
