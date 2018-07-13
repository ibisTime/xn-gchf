package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IBankCardDAO;
import com.cdkj.gchf.dao.IStaffDAO;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.exception.BizException;

@Component
public class StaffBOImpl extends PaginableBOImpl<Staff> implements IStaffBO {
    @Autowired
    private IStaffDAO staffDAO;

    @Autowired
    private IBankCardDAO bankCardDAO;

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
    public Staff getStaffBrief(String code) {
        Staff data = null;
        if (StringUtils.isNotBlank(code)) {
            Staff condition = new Staff();
            condition.setCode(code);
            data = staffDAO.selectBrief(condition);
            if (data == null) {
                throw new BizException("xn0000", "员工不存在");
            }
            BankCard bankCard = new BankCard();
            bankCard.setStaffCode(code);
            data.setBankCard(bankCardDAO.select(bankCard));
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
    public Staff getStaffByIdNo(String idNo) {
        Staff data = null;
        if (StringUtils.isNotBlank(idNo)) {
            Staff condition = new Staff();
            condition.setIdNo(idNo);
            data = staffDAO.select(condition);
        }
        return data;
    }
}
