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
    public void refreshPict1(String code, String pict1, String feat,
            String updater) {

        Staff staff = new Staff();
        staff.setCode(code);
        staff.setPict1(pict1);
        staff.setFeat(feat);
        staff.setUpdater(updater);

        staff.setUpdateDatetime(new Date());
        staff.setPict1Status(EBoolean.YES.getCode());

        if ("NOFACE".equals(feat) || null == feat) {
            staff.setFeatStatus(EBoolean.NO.getCode());
        } else {
            staff.setFeatStatus(EBoolean.YES.getCode());
        }

        staffDAO.updatePict1(staff);
    }

    @Override
    public void refreshPicts(String code, String pict2, String pict3,
            String pict4, String updater) {

        Staff data = new Staff();
        data.setCode(code);
        data.setPict2(pict2);
        data.setPict3(pict3);
        data.setPict4(pict4);

        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());

        staffDAO.updatePicts(data);
    }

    @Override
    public void refreshContactInfo(String code, String mobile, String contact,
            String contactMobile, String updater, String remark) {

        Staff data = new Staff();
        data.setCode(code);
        data.setMobile(mobile);
        data.setContacts(contact);
        data.setContactsMobile(contactMobile);
        data.setUpdater(updater);

        data.setUpdateDatetime(new Date());
        data.setRemark(remark);

        staffDAO.updateContactInfo(data);
    }

    @Override
    public void refreshFeat(String code, String feat) {

        Staff data = new Staff();
        data.setCode(code);
        data.setFeat(feat);
        data.setFeatStatus(EBoolean.YES.getCode());
        staffDAO.updateFeat(data);

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

}
