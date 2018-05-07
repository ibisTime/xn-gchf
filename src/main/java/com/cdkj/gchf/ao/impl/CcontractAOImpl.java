package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.ICcontractAO;
import com.cdkj.gchf.bo.ICcontractBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.IUserBO;
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
        Paginable<Ccontract> page = ccontractBO.getPaginable(start, limit,
            condition);
        List<Ccontract> list = page.getList();
        String updateName = null;
        for (Ccontract ccontract : list) {
            Staff staff = staffBO.getStaff(ccontract.getStaffCode());
            ccontract.setStaff(staff);
            updateName = getName(ccontract.getUpdater());
            ccontract.setUpdateName(updateName);
        }
        page.setList(list);

        return page;
    }

    @Override
    public List<Ccontract> queryCcontractList(Ccontract condition) {
        List<Ccontract> list = ccontractBO.queryCcontractList(condition);
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
        String name = EUser.ADMIN.getCode();
        if (!EUser.ADMIN.getCode().equals(user.getLoginName())) {
            name = user.getRealName();
        }
        return name;

    }
}
