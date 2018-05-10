package com.cdkj.gchf.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.Ccontract;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.domain.User;
import com.cdkj.gchf.dto.req.XN631400Req;
import com.cdkj.gchf.dto.req.XN631402Req;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.enums.EUser;
import com.cdkj.gchf.enums.EUserKind;

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
        Ccontract data = new Ccontract();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Ccontract.getCode());
        data.setCode(code);
        data.setProjectCode(req.getProjectCode());
        Project project = projectBO.getProject(req.getProjectCode());
        data.setCompanyCode(project.getCompanyCode());
        data.setCompanyName(project.getCompanyName());

        data.setProjectName(project.getName());
        data.setStaffCode(req.getStaffCode());
        Staff staff = staffBO.getStaff(req.getStaffCode());
        data.setStaffMobile(staff.getMobile());

        data.setContentPic(req.getContentPic());
        data.setContractDatetime(DateUtil.strToDate(req.getContractDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        ccontractBO.saveCcontract(data);
        return code;
    }

    @Override
    public void editCcontract(XN631402Req req) {
        Ccontract data = ccontractBO.getCcontract(req.getCode());
        data.setContentPic(req.getContentPic());
        data.setContractDatetime(DateUtil.strToDate(req.getContractDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        ccontractBO.refreshCcontract(data);
    }

    @Override
    public void dropCcontract(String code) {
    }

    @Override
    public Paginable<Ccontract> queryCcontractPage(int start, int limit,
            Ccontract condition) {
        List<Ccontract> list = new ArrayList<Ccontract>();
        Paginable<Ccontract> page = new Page<Ccontract>();
        if (EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (StringUtils.isBlank(condition.getCompanyCode())) {
                page.setList(list);
                return page;
            }
        }
        page = ccontractBO.getPaginable(start, limit, condition);
        String updateName = null;
        for (Ccontract ccontract : page.getList()) {
            Staff staff = staffBO.getStaff(ccontract.getStaffCode());
            ccontract.setStaff(staff);
            updateName = getName(ccontract.getUpdater());
            ccontract.setUpdateName(updateName);
        }

        return page;
    }

    @Override
    public List<Ccontract> queryCcontractList(Ccontract condition) {
        List<Ccontract> list = null;
        if (EUserKind.Owner.getCode().equals(condition.getKind())) {
            if (StringUtils.isBlank(condition.getCompanyCode())) {
                return list;
            }
        }
        list = ccontractBO.queryCcontractList(condition);
        String updateName = null;
        for (Ccontract ccontract : list) {
            Staff staff = staffBO.getStaff(ccontract.getStaffCode());
            ccontract.setStaff(staff);
            updateName = getName(ccontract.getUpdater());
            ccontract.setUpdateName(updateName);
        }
        return list;
    }

    @Override
    public Ccontract getCcontract(String code) {
        Ccontract data = ccontractBO.getCcontract(code);
        Staff staff = staffBO.getStaff(data.getStaffCode());
        data.setStaff(staff);
        String updateName = getName(data.getUpdater());
        data.setUpdateName(updateName);
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
