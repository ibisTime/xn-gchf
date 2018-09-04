package com.cdkj.gchf.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.dao.IStaffDAO;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.enums.EBoolean;
import com.cdkj.gchf.exception.BizException;

@Component
public class StaffBOImpl extends PaginableBOImpl<Staff> implements IStaffBO {
    @Autowired
    private IStaffDAO staffDAO;

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
    public void refreshFeat(String code, String pict1, String feat,
            String updater) {

        Staff staff = new Staff();
        staff.setCode(code);
        staff.setPict1(pict1);
        staff.setFeat(feat);
        staff.setUpdater(updater);

        staff.setUpdateDatetime(new Date());
        staff.setPict1Status(EBoolean.YES.getCode());
        staff.setFeatStatus(EBoolean.YES.getCode());

        staffDAO.updateFeat(staff);
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
    public List<Staff> queryStaffListBrief(Staff condition, int start,
            int count) {
        return staffDAO.selectListBrief(condition, start, count);
    }

    @Override
    public long queryTotalCount(Staff condition) {
        return staffDAO.selectTotalCount(condition);
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
    public List<Staff> queryStaffPicList(Staff condition, int start,
            int count) {
        return staffDAO.selectStaffPic(condition, start, count);
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

    @Override
    public Staff getStaffByKeyword1(String keyword1) {
        Staff data = null;
        if (StringUtils.isNotBlank(keyword1)) {
            Staff condition = new Staff();
            condition.setKeyword1(keyword1);
            data = staffDAO.select(condition);
        }
        return data;
    }
}
