package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICcontractBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.ICcontractDAO;
import com.cdkj.gchf.domain.Ccontract;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class CcontractBOImpl extends PaginableBOImpl<Ccontract>
        implements ICcontractBO {
    @Autowired
    private ICcontractDAO ccontractDAO;

    @Autowired
    private IStaffBO staffBO;

    @Autowired
    private IProjectBO projectBO;

    @Override
    public String saveCcontract(String staffCode, String projectCode,
            String contentPic, Date contractDatetime, String updater,
            String remark) {
        Staff staff = staffBO.getStaff(staffCode);
        Project project = projectBO.getProject(projectCode);
        Ccontract ccontract = new Ccontract();
        String ccontractCode = OrderNoGenerater
            .generate(EGeneratePrefix.Ccontract.getCode());
        ccontract.setCode(ccontractCode);
        ccontract.setProjectCode(projectCode);
        ccontract.setProjectName(project.getName());
        ccontract.setStaffCode(staffCode);
        ccontract.setStaffName(staff.getName());

        ccontract.setContentPic(contentPic);
        ccontract.setContractDatetime(contractDatetime);
        ccontract.setUpdater(updater);
        ccontract.setUpdateDatetime(new Date());
        ccontract.setRemark(remark);

        ccontractDAO.insert(ccontract);
        return ccontractCode;
    }

    @Override
    public void refreshCcontract(String code, String contentPic,
            Date contractDatetime, String updater, String remark) {
        Ccontract ccontract = new Ccontract();
        ccontract.setCode(code);
        ccontract.setContentPic(contentPic);
        ccontract.setContractDatetime(contractDatetime);
        ccontract.setUpdater(updater);
        ccontract.setUpdateDatetime(new Date());
        ccontract.setRemark(remark);
        ccontractDAO.update(ccontract);
    }

    @Override
    public List<Ccontract> queryCcontractList(Ccontract condition) {
        return ccontractDAO.selectList(condition);
    }

    @Override
    public Ccontract getCcontract(String code) {
        Ccontract data = null;
        if (StringUtils.isNotBlank(code)) {
            Ccontract condition = new Ccontract();
            condition.setCode(code);
            data = ccontractDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "合同信息不存在");
            }
        }
        return data;
    }

    @Override
    public Ccontract getCcontractByStaff(String staffCode) {
        Ccontract data = null;
        if (StringUtils.isNotBlank(staffCode)) {
            Ccontract condition = new Ccontract();
            condition.setStaffCode(staffCode);
            data = ccontractDAO.select(condition);
        }
        return data;
    }

    @Override
    public Ccontract isExist(String projectCode, String staffCode) {
        Ccontract condition = new Ccontract();
        condition.setProjectCode(projectCode);
        condition.setStaffCode(staffCode);
        return ccontractDAO.select(condition);

    }
}
