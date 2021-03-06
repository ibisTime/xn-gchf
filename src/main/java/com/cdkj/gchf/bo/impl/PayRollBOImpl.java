package com.cdkj.gchf.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.cdkj.gchf.domain.CorpBasicinfo;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.PayRollDetail;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.TeamMaster;
import com.cdkj.gchf.dto.req.XN631770Req;
import com.cdkj.gchf.dto.req.XN631772Req;
import com.cdkj.gchf.dto.req.XN631910Req;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631920ReqDateil;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.enums.EDeleteStatus;
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
    public String savePayRoll(XN631770Req data, CorpBasicinfo corpBasicInfo) {
        PayRoll payRoll = new PayRoll();

        BeanUtils.copyProperties(data, payRoll);
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.PayRoll.getCode());
        payRoll.setCode(code);
        if (StringUtils.isNotBlank(data.getPayMonth())) {
            Date payMonth = DateUtil.strToDate(data.getPayMonth(), "yyyy-MM");
            payRoll.setPayMonth(payMonth);
        }
        payRoll.setCorpName(corpBasicInfo.getCorpName());
        payRoll.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        payRollDAO.insert(payRoll);
        return code;
    }

    @Override
    public String savePayRoll(String corpCode, String projectCode,
                              String corpName, String teamMasterNo, String payMonth) {
        PayRoll payRollcondition = new PayRoll();
        payRollcondition.setCorpCode(corpCode);
        payRollcondition.setCorpName(corpName);
        payRollcondition.setTeamSysNo(teamMasterNo);
        Date toDate = DateUtil.strToDate(payMonth, "yyyy-MM");
        payRollcondition.setProjectCode(projectCode);
        payRollcondition.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        payRollcondition.setPayMonth(toDate);
        String code = OrderNoGenerater
                .generate(EGeneratePrefix.PayRoll.getCode());
        payRollcondition.setCode(code);
        payRollDAO.insert(payRollcondition);
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

                        if (StringUtils.isNotBlank(reqDateil.getDays())) {
                            payRollDetail
                                .setDays(Integer.parseInt(reqDateil.getDays()));
                        }

                        if (StringUtils.isNotBlank(reqDateil.getWorkHours())) {
                            payRollDetail.setWorkHours(
                                new BigDecimal(reqDateil.getWorkHours()));
                        }

                        payRollDetail.setIdcardNumber(
                            AesUtils.decrypt(reqDateil.getIdCardNumber(),
                                projectConfig.getSecret()));

                        payRollDetail.setPayRollBankCardNumber(AesUtils.decrypt(
                            reqDateil.getPayRollBankCardNumber(),
                            projectConfig.getSecret()));

                        payRollDetail.setPayBankCardNumber(AesUtils.decrypt(
                            reqDateil.getPayRollBankCardNumber(),
                            projectConfig.getSecret()));

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
    public List<PayRoll> queryPayRollList(String projectCode,
                                          String teamMasterNo, String corpCode) {
        PayRoll payRollCondition = new PayRoll();
        payRollCondition.setCorpCode(corpCode);
        payRollCondition.setTeamSysNo(teamMasterNo);
        payRollCondition.setProjectCode(projectCode);
        return payRollDAO.selectList(payRollCondition);
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
    public List<PayRoll> getPayRollList(String payRollCode) {
        PayRoll payRoll = new PayRoll();
        payRoll.setPayRollCode(payRollCode);
        payRoll.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        return payRollDAO.selectList(payRoll);
    }

    @Override
    public PayRoll getPayRollByCondition(PayRoll condition) {
        return payRollDAO.select(condition);
    }

    @Override
    public String savePayRoll(PayRoll payRoll) {
        String code = null;
        code = OrderNoGenerater.generate(EGeneratePrefix.PayRoll.getCode());
        payRoll.setCode(code);
        payRoll.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        payRollDAO.insert(payRoll);
        return code;
    }

    @Override
    public int updatePayRollDetail(XN631772Req req) {
        PayRoll condition = new PayRoll();
        condition.setCode(req.getCode());
        PayRoll payRoll = payRollDAO.select(condition);
        BeanUtils.copyProperties(req, payRoll);
        return payRollDAO.update(payRoll);
    }

    @Override
    public void refreshPayRollCodeByLocal(String code, String payRollCode) {
        PayRoll payRoll = new PayRoll();
        payRoll.setCode(code);
        payRoll.setPayRollCode(payRollCode);
        payRollDAO.updatePayRollCode(payRoll);
    }

    @Override
    public PayRoll getPayRollByCorpCodeAndTeamSysNoAndProjectCode(
            String corpCode, String teamMasterSysNo, String projectCode,
            String payMonth) {
        PayRoll payRoll = new PayRoll();
        payRoll.setTeamSysNo(teamMasterSysNo);
        payRoll.setCorpCode(corpCode);
        payRoll.setProjectCode(projectCode);
        Date payM = DateUtil.strToDate(payMonth, "yyyy-MM");
        payRoll.setPayMonth(payM);
        if (payM == null) {
            throw new BizException("XN000000", "发放工资参数异常,请联系管理员");
        }
        return payRollDAO.select(payRoll);
    }

    @Override
    public PayRoll getPayRollByTeamNo(String teamNo) {
        PayRoll payRoll = new PayRoll();
        payRoll.setTeamSysNo(teamNo);
        return payRollDAO.select(payRoll);
    }

    @Override
    public int updatePayRollDeleteStatus(String projectCode,
            String teamMasterNo, String corpCode) {
        PayRoll payRoll = new PayRoll();
        payRoll.setCorpCode(corpCode);
        payRoll.setProjectCode(projectCode);
        payRoll.setTeamSysNo(teamMasterNo);
        payRoll.setDeleteStatus(EDeleteStatus.DELETED.getCode());
        return payRollDAO.updatePayRollDeleteStatus(payRoll);
    }

    @Override
    public int updatePayRollDeleteStatus(String payRollCode) {
        PayRoll payRoll = new PayRoll();
        payRoll.setCode(payRollCode);
        payRoll.setDeleteStatus(EDeleteStatus.NORMAL.getCode());
        return payRollDAO.updatePayRollDeleteStatus(payRoll);
    }

}
