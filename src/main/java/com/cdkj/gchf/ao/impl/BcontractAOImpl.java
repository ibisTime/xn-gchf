package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IBcontractAO;
import com.cdkj.gchf.bo.IBcontractBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.common.PhoneUtil;
import com.cdkj.gchf.domain.Bcontract;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.dto.req.XN631370Req;
import com.cdkj.gchf.dto.req.XN631372Req;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;

@Service
public class BcontractAOImpl implements IBcontractAO {

    @Autowired
    private IBcontractBO bcontractBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String addBcontract(XN631370Req req) {
        PhoneUtil.checkMobile(req.getBmobile());

        Bcontract data = new Bcontract();
        Project project = projectBO.getProject(req.getProjectCode());
        // 项目是否通过审核
        if (EProjectStatus.End.getCode().equals(project.getStatus())) {
            throw new BizException("xn00000", "该项目已经结束");
        }
        if (!EProjectStatus.Building.getCode().equals(project.getStatus())) {
            throw new BizException("xn00000", "该项目还未通过审核");
        }

        data.setProjectCode(project.getCode());
        data.setCompanyCode(project.getCompanyCode());

        data.setBname(req.getBname());
        data.setBmobile(req.getBmobile());
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
        PhoneUtil.checkMobile(req.getBmobile());
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
        Paginable<Bcontract> page = new Page<Bcontract>();
        List<Bcontract> list = new ArrayList<Bcontract>();
        // 业主端、监管端是否有项目
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }
        }
        if (EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (StringUtils.isBlank(condition.getCompanyCode())) {
                page.setList(list);
                return page;
            }
        }

        // 补全信息
        page = bcontractBO.getPaginable(start, limit, condition);
        Project project = null;
        for (Bcontract bcontract : page.getList()) {
            project = projectBO.getProject(bcontract.getProjectCode());
            bcontract.setProjectName(project.getName());
        }
        return page;
    }

    @Override
    public List<Bcontract> queryBcontractList(Bcontract condition) {
        List<Bcontract> list = new ArrayList<Bcontract>();
        // 业主端、监管端是否有项目
        if (EUserKind.Supervise.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }
        if (EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (StringUtils.isBlank(condition.getCompanyCode())) {
                return list;
            }
        }

        // 补全信息
        list = bcontractBO.queryBcontractList(condition);
        Project project = null;
        for (Bcontract bcontract : list) {
            project = projectBO.getProject(bcontract.getProjectCode());
            bcontract.setProjectName(project.getName());
        }
        return list;
    }

    @Override
    public Bcontract getBcontract(String code) {
        Bcontract data = bcontractBO.getBcontract(code);
        Project project = projectBO.getProject(data.getProjectCode());
        data.setProjectName(project.getName());
        return data;
    }
}
