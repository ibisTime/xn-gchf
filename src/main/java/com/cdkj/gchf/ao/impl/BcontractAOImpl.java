package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IBcontractAO;
import com.cdkj.gchf.bo.IBcontractBO;
import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Bcontract;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.dto.req.XN631370Req;
import com.cdkj.gchf.dto.req.XN631372Req;

@Service
public class BcontractAOImpl implements IBcontractAO {

    @Autowired
    private IBcontractBO bcontractBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public String addBcontract(XN631370Req req) {
        Bcontract data = new Bcontract();
        Company company = companyBO.getCompany(req.getCompanyCode());
        data.setCompanyCode(req.getCompanyCode());
        data.setCompanyName(company.getName());
        data.setProjectCode(req.getProjectCode());
        data.setProjectName(
            projectBO.getProject(req.getProjectCode()).getName());
        data.setBname(req.getBname());

        data.setBmobile(req.getBmobile());
        data.setPict1(req.getPict1());
        data.setPict2(req.getPict2());
        data.setPict3(req.getPict3());
        data.setContentPic(req.getContentPic());

        data.setContractDatetime(DateUtil.strToDate(req.getContractDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        return bcontractBO.saveBcontract(data);
    }

    @Override
    public int editBcontract(XN631372Req req) {
        Bcontract data = bcontractBO.getBcontract(req.getCode());
        data.setBname(req.getBname());
        data.setBmobile(req.getBmobile());
        data.setContentPic(req.getContentPic());
        data.setContractDatetime(DateUtil.strToDate(req.getContractDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));

        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        return bcontractBO.refreshBcontract(data);
    }

    @Override
    public Paginable<Bcontract> queryBcontractPage(int start, int limit,
            Bcontract condition) {
        return bcontractBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Bcontract> queryBcontractList(Bcontract condition) {
        return bcontractBO.queryBcontractList(condition);
    }

    @Override
    public Bcontract getBcontract(String code) {
        return bcontractBO.getBcontract(code);
    }
}
