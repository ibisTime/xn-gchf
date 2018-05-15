package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IStaffDAO;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.exception.BizException;

@Component
public class StaffBOImpl extends PaginableBOImpl<Staff> implements IStaffBO {

    @Autowired
    private IStaffDAO staffDAO;

    public void saveStaff(Staff data) {
        staffDAO.insert(data);
    }

    @Override
    public void removeStaff(String code) {
    }

    @Override
    public void refreshStaff(Staff data) {
        staffDAO.update(data);
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
    public List<Staff> getStaffFeatList() {
        Staff condition = new Staff();
        return staffDAO.selectList(condition);

    }

    @Override
    public Staff getStaffByIdNo(String idNo) {
        Staff condition = new Staff();
        condition.setIdNo(idNo);
        List<Staff> list = staffDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;

    }
}
