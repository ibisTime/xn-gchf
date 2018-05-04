package com.cdkj.gchf.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.gchf.ao.IStaffAO;
import com.cdkj.gchf.bo.IBankCardBO;
import com.cdkj.gchf.bo.IStaffBO;
import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.domain.BankCard;
import com.cdkj.gchf.domain.Staff;
import com.cdkj.gchf.dto.req.XN631410Req;
import com.cdkj.gchf.dto.req.XN631412Req;
import com.cdkj.gchf.enums.EGeneratePrefix;

@Service
public class StaffAOImpl implements IStaffAO {

    @Autowired
    private IStaffBO staffBO;

    @Autowired
    private IBankCardBO bankCardBO;

    @Override
    public String addStaff(XN631410Req req) {
        Staff data = new Staff();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Staff.getCode());
        data.setCode(code);
        data.setIdType(req.getIdType());
        data.setIdNo(req.getIdNo());
        data.setMobile(req.getMobile());
        data.setName(req.getName());

        data.setPict1(req.getPict1());
        data.setPict2(req.getPict2());
        data.setPict3(req.getPict3());
        data.setPlace(req.getPlace());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        staffBO.saveStaff(data);
        // 添加工资卡
        bankCardBO.addBankCard(code, req.getName(), req.getBankCode(),
            req.getBankName(), req.getBankcardNumber(), req.getSubbranch(),
            req.getUpdater(), data.getUpdateDatetime(), req.getRemark());
        return code;
    }

    @Override
    public void editStaff(XN631412Req req) {
        Staff data = staffBO.getStaff(req.getCode());
        data.setIdType(req.getIdType());
        data.setIdNo(req.getIdNo());
        data.setMobile(req.getMobile());
        data.setName(req.getName());

        data.setPict1(req.getPict1());
        data.setPict2(req.getPict2());
        data.setPict3(req.getPict3());
        data.setPlace(req.getPlace());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        staffBO.refreshStaff(data);
    }

    @Override
    public void dropStaff(String code) {
        staffBO.removeStaff(code);
    }

    @Override
    public Paginable<Staff> queryStaffPage(int start, int limit,
            Staff condition) {
        return staffBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Staff> queryStaffList(Staff condition) {
        return staffBO.queryStaffList(condition);
    }

    @Override
    public Staff getStaff(String code) {
        Staff data = staffBO.getStaff(code);
        BankCard bankCard = bankCardBO.getBankCardByStaff(data.getCode());
        data.setBankCard(bankCard);
        return data;
    }
}
