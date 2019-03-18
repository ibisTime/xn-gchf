package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IPayRollAO;
import com.cdkj.gchf.bo.IPayRollBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.PayRoll;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.dto.req.XN631920Req;
import com.cdkj.gchf.dto.req.XN631921Req;
import com.cdkj.gchf.exception.BizException;

@Service
public class PayRollAOImpl implements IPayRollAO {

    @Autowired
    private IPayRollBO payRollBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Override
    public String addPayRoll(PayRoll data) {
        return payRollBO.savePayRoll(data);
    }

    @Override
    public int editPayRoll(PayRoll data) {
        return payRollBO.refreshPayRoll(data);
    }

    @Override
    public int dropPayRoll(String code) {
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
    public Paginable<PayRoll> queryPayRoll(XN631921Req req) {
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
        return payRollBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PayRoll> queryPayRollList(PayRoll condition) {
        return payRollBO.queryPayRollList(condition);
    }

    @Override
    public PayRoll getPayRoll(String code) {
        return payRollBO.getPayRoll(code);
    }

}
