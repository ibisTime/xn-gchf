package com.cdkj.gchf.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IStaffLogAO;
import com.cdkj.gchf.bo.IStaffLogBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.StaffLog;

@Service
public class StaffLogAOImpl implements IStaffLogAO {

    @Autowired
    private IStaffLogBO staffLogBO;

    @Override
    public String addStaffLog(StaffLog data) {
        return null;
    }

    @Override
    public Paginable<StaffLog> queryStaffLogPage(int start, int limit,
            StaffLog condition) {
        return staffLogBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<StaffLog> queryStaffLogList(StaffLog condition) {
        return staffLogBO.queryStaffLogList(condition);
    }

    @Override
    public StaffLog getStaffLog(String code) {
        return staffLogBO.getStaffLog(code);
    }
}
