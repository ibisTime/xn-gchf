package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICcontractAO;
import com.cdkj.gchf.bo.ICcontractBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.base.Page;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.domain.Ccontract;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631400Req;
import com.cdkj.gchf.dto.req.XN631402Req;
import com.cdkj.gchf.enums.EProjectStatus;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;
import com.cdkj.gchf.exception.BizException;

@Service
public class CcontractAOImpl implements ICcontractAO {

    @Autowired
    private ICcontractBO ccontractBO;

    @Autowired
    private IProjectBO projectBO;

    @Autowired
    private IStaffBO staffBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String addCcontract(XN631400Req req) {
        Project project = projectBO.getProject(req.getProjectCode());
        if (EProjectStatus.End.getCode().equals(project.getStatus())) {
            throw new BizException("xn00000", "项目已经结束");
        }
        if (!EProjectStatus.Building.getCode().equals(project.getStatus())) {
            throw new BizException("xn00000", "该项目还未通过审核");
        }
        if (null != ccontractBO.getCcontractByStaff(req.getStaffCode())) {
            throw new BizException("XN000", "员工已存在合同，请勿重复添加!");
        }

        Date contractDatetime = DateUtil.strToDate(req.getContractDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        return ccontractBO.saveCcontract(req.getStaffCode(),
            req.getProjectCode(), req.getContentPic(), contractDatetime,
            req.getUpdater(), req.getRemark());
    }

    @Override
    public void editCcontract(XN631402Req req) {
        Date contractDatetime = DateUtil.strToDate(req.getContractDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING);
        ccontractBO.refreshCcontract(req.getCode(), req.getContentPic(),
            contractDatetime, req.getUpdater(), req.getRemark());
    }

    @Override
    public void dropCcontract(String code) {
    }

    @Override
    public Paginable<Ccontract> queryCcontractPage(int start, int limit,
            Ccontract condition) {
        List<Ccontract> list = new ArrayList<Ccontract>();
        Paginable<Ccontract> page = new Page<Ccontract>();
        if (EUserKind.Supervise.getCode().equals(condition.getKind())
                || EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                page.setList(list);
                return page;
            }
        }

        page = ccontractBO.getPaginable(start, limit, condition);
        for (Ccontract ccontract : page.getList()) {
            Staff staff = staffBO.getStaff(ccontract.getStaffCode());
            if (StringUtils.isNotBlank(staff.getMobile())) {
                ccontract.setStaffMobile(staff.getMobile());
            }
            ccontract.setUpdateName(getName(ccontract.getUpdater()));
        }

        return page;
    }

    @Override
    public List<Ccontract> queryCcontractList(Ccontract condition) {
        List<Ccontract> list = null;
        if (EUserKind.Supervise.getCode().equals(condition.getKind())
                || EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (CollectionUtils.isEmpty(condition.getProjectCodeList())) {
                return list;
            }
        }

        list = ccontractBO.queryCcontractList(condition);
        for (Ccontract ccontract : list) {
            Staff staff = staffBO.getStaff(ccontract.getStaffCode());
            ccontract.setStaffMobile(staff.getMobile());
            ccontract.setUpdateName(getName(ccontract.getUpdater()));
        }
        return list;
    }

    @Override
    public Ccontract getCcontract(String code) {
        Ccontract data = ccontractBO.getCcontract(code);
        Staff staff = staffBO.getStaff(data.getStaffCode());
        data.setStaffMobile(staff.getMobile());
        data.setUpdateName(getName(data.getUpdater()));
        return data;
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
