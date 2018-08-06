package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICcontractAO;
import com.cdkj.gchf.bo.ICcontractBO;
import com.cdkj.gchf.bo.IEmployBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Ccontract;
import com.cdkj.gchf.domain.Employ;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631400Req;
import com.cdkj.gchf.dto.req.XN631402Req;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.exception.BizException;

@Service
public class CcontractAOImpl implements ICcontractAO {

    @Autowired
    private ICcontractBO ccontractBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IEmployBO employBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addCcontract(XN631400Req req) {
        Employ employ = employBO.getEmploy(req.getEmployCode());

        Project project = projectBO.getProject(employ.getProjectCode());
        if (EProjectStatus.End.getCode().equals(project.getStatus())) {
            throw new BizException("xn00000", "项目已经结束");
        }
        if (!EProjectStatus.Building.getCode().equals(project.getStatus())) {
            throw new BizException("xn00000", "该项目还未通过审核");
        }

        return ccontractBO.saveCcontract(employ.getCode(),
            employ.getStaffCode(), employ.getStaffName(),
            employ.getStaffMobile(), employ.getProjectCode(),
            employ.getProjectName(), req.getContentPic(), req.getUpdater(),
            req.getRemark());
    }

    @Override
    public void editCcontract(XN631402Req req) {
        ccontractBO.refreshCcontract(req.getCode(), req.getContentPic(),
            req.getUpdater(), req.getRemark());
    }

    @Override
    public void dropCcontract(String code) {
        ccontractBO.dropCcontract(code);
    }

    @Override
    public Paginable<Ccontract> queryCcontractPage(int start, int limit,
            Ccontract condition) {
        Paginable<Ccontract> page = new Page<Ccontract>();

        page = ccontractBO.getPaginable(start, limit, condition);
        for (Ccontract ccontract : page.getList()) {
            initCcontract(ccontract);
        }

        return page;
    }

    @Override
    public List<Ccontract> queryCcontractList(Ccontract condition) {
        List<Ccontract> list = ccontractBO.queryCcontractList(condition);
        for (Ccontract ccontract : list) {
            initCcontract(ccontract);
        }
        return list;
    }

    @Override
    public Ccontract getCcontract(String code) {
        Ccontract data = ccontractBO.getCcontract(code);
        initCcontract(data);
        return data;
    }

    private void initCcontract(Ccontract data) {
        data.setUpdateName(getName(data.getUpdater()));
    }

    private String getName(String userId) {
        User user = userBO.getUserName(userId);
        String name = null;
        if (user != null) {
            name = EUser.ADMIN.getCode();
            if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
                name = user.getRealName();
            }
        }
        return name;
    }
}
