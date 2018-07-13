package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.ICompanyBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IStaffDAO;
import com.cdkj.gchf.domain.Company;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.exception.BizException;

@Component
public class StaffBOImpl extends PaginableBOImpl<Staff> implements IStaffBO {
    @Autowired
    private IStaffDAO staffDAO;

    @Autowired
    private ICompanyBO companyBO;

    public void saveStaff(Staff data) {
        staffDAO.insert(data);
    }

    @Override
    public void refreshStaff(Staff data) {
        staffDAO.update(data);
    }

    @Override
    public void refreshStaffInfo(Staff data) {
        staffDAO.updateStaffInfo(data);
    }

    @Override
    public void refreshFeat(Staff data, String pict1, String feat,
            String updater) {
        data.setPict1(pict1);
        data.setFeat(feat);
        data.setUpdater(updater);
        Date date = new Date();
        data.setUpdateDatetime(date);

        staffDAO.updateFeat(data);
    }

    @Override
    public void refreshIdPict(Staff data) {
        staffDAO.updateIdPict(data);
    }

    @Override
    public List<Staff> queryStaffList(Staff condition) {
        return staffDAO.selectList(condition);
    }

    @Override
    public Staff getStaff(String code) {
        Staff data = null;
        if (StringUtils.isNotBlank(code)) {
            Staff condition = new Staff();
            condition.setCode(code);
            data = staffDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "员工不存在");
            }
        }
        return data;
    }

    @Override
    public List<Staff> getStaffFeatList(List<String> staffCodeList) {
        Staff condition = new Staff();
        condition.setCodeList(staffCodeList);
        return staffDAO.selectList(condition);

    }

    @Override
    public Staff getStaff(String idNo, String companyCode) {
        Staff data = null;
        if (StringUtils.isNotBlank(idNo)
                && StringUtils.isNotBlank(companyCode)) {
            Staff condition = new Staff();
            condition.setIdNo(idNo);
            condition.setCompanyCode(companyCode);
            data = staffDAO.select(condition);
            initStaff(data);
        } else if (StringUtils.isNotBlank(idNo)
                && StringUtils.isBlank(companyCode)) {
            Staff condition = new Staff();
            condition.setIdNo(idNo);
            List<Staff> staffList = queryStaffList(condition);
            if (CollectionUtils.isNotEmpty(staffList)) {
                data = staffList.get(0);
                initStaff(data);
            }
        }
        return data;
    }

    private void initStaff(Staff data) {
        Company company = companyBO.getCompany(data.getCompanyCode());
        data.setCompanyName(company.getName());
    }
}
